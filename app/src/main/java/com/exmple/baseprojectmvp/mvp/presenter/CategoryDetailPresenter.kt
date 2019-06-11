package com.exmple.baseprojectmvp.mvp.presenter

import com.exmple.baseprojectmvp.mvp.contract.ICategoryDetailContract
import com.exmple.baseprojectmvp.mvp.contract.ISearchContract
import com.exmple.baseprojectmvp.mvp.model.CategoryDetailModel
import com.exmple.baseprojectmvp.mvp.model.SearchModel
import com.exmple.corelib.http.retrofit.NetCallBack
import com.exmple.corelib.mSubscribe
import com.exmple.corelib.mvp.BasePresenterKt
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.presenter
 * @description:
 * @date: 2019/6/11
 * @time:  10:32
 */
class CategoryDetailPresenter: BasePresenterKt<ICategoryDetailContract.View>(), ICategoryDetailContract.Presenter {

    override var mModel: ICategoryDetailContract.Model? = CategoryDetailModel()

    private var nextPageUrl:String?=null

    override fun getCategoryDetailList(id: Long) {
        mModel?.getCategoryDetailList(id)?.mSubscribe(
                mView,
                mModel,
                "正在获取数据...",
                object : NetCallBack<HomeBean.Issue> {
                    override fun backFail(errStr: String) {
                        mView?.showError(errStr)
                    }

                    override fun success(issue: HomeBean.Issue) {
                        nextPageUrl = issue.nextPageUrl
                        mView?.setCateDetailList(issue.itemList)
                    }

                    override fun fail(t: String?) {
                        if (t != null) {
                            mView?.showError(t)
                        }
                    }
                })
    }

    override fun loadMoreData() {
        nextPageUrl?.let {
            mModel?.loadMoreData(it)?.mSubscribe(
                mView,
                mModel,
                "正在获取数据...",
                object : NetCallBack<HomeBean.Issue> {
                    override fun backFail(errStr: String) {
                        mView?.showError(errStr)
                    }

                    override fun success(issue: HomeBean.Issue) {
                        nextPageUrl = issue.nextPageUrl
                        mView?.setCateDetailList(issue.itemList)
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