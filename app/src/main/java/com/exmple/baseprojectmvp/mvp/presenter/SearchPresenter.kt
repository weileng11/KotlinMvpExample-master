package com.exmple.baseprojectmvp.mvp.presenter

import com.exmple.baseprojectmvp.mvp.contract.ISearchContract
import com.exmple.baseprojectmvp.mvp.model.SearchModel
import com.exmple.corelib.http.retrofit.NetCallBack
import com.exmple.corelib.mSubscribe
import com.exmple.corelib.mvp.BasePresenterKt
import com.exmple.corelib.scheduler.SchedulerUtils
import com.exmple.corelib.showToastBottom
import com.exmple.corelib.utils.NetworkUtils
import com.google.gson.JsonParseException
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.ArrayList

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.presenter
 * @description:
 * @date: 2019/6/10
 * @time:  11:19
 */
class SearchPresenter: BasePresenterKt<ISearchContract.View>(), ISearchContract.Presenter {

    private var nextPageUrl: String? = null


    override var mModel: ISearchContract.Model? = SearchModel()

    /**
     * 获取热门关键词
     */
    override fun requestHotWordData() {
        mModel?.requestHotWordData()?.compose(SchedulerUtils.ioToMain())!!
                .subscribe(object : Observer<ArrayList<String>> {
                    override fun onNext(rspBean: ArrayList<String>) {
                        mView?.setHotWordData(rspBean)
                    }
                    override fun onComplete() {
                        mView?.dismissLoading()
                    }

                    override fun onSubscribe(d: Disposable) {
                        mModel?.addDisposable(d)
                        mView?.showLoading("请求中...")
                        if (!NetworkUtils.isConnected()) {
                            showToastBottom("连接失败,请检查网络状况!")
                            onComplete()
                        }
                    }
                    override fun onError(e: Throwable) {
                        mView?.dismissLoading()
                        if (e is SocketTimeoutException || e is ConnectException) {
                            showToastBottom("连接失败,请检查网络状况!")
                        } else if (e is JsonParseException) {
                            showToastBottom("数据解析失败")
                        } else {
                            showToastBottom("请求失败")
                        }
                    }
                })
    }

    /**
     * 查询关键词
     */
    override fun querySearchData(words: String) {
        mModel?.getSearchResult(words)?.mSubscribe(
                mView,
                mModel,
                "正在获取数据...",
                object : NetCallBack<HomeBean.Issue> {
                    override fun backFail(errStr: String) {
                        mView?.showError(errStr)
                    }

                    override fun success(issue: HomeBean.Issue) {
                        if (issue.count > 0 && issue.itemList.size > 0) {
                            nextPageUrl = issue.nextPageUrl
                            mView?.setSearchResult(issue)
                        } else
                            mView?.setEmptyView()
                    }

                    override fun fail(t: String?) {
                        if (t != null) {
                            mView?.showError(t)
                        }
                    }
                })
    }

    /**
     * 加载更多数据
     */
    override fun loadMoreData() {
        this!!.nextPageUrl?.let {
            mModel?.loadMoreData(it)?.mSubscribe(
                mView,
                mModel,
                "",
                object : NetCallBack<HomeBean.Issue> {
                    override fun backFail(errStr: String) {
                        mView?.showError(errStr)
                    }

                    override fun success(issue: HomeBean.Issue) {
                        nextPageUrl = issue.nextPageUrl
                        mView?.setSearchResult(issue)
                    }

                    override fun fail(t: String?) {
                        if (t != null) {
                            mView?.showError(t)
                        }
                    }
                })
        }
    }
}