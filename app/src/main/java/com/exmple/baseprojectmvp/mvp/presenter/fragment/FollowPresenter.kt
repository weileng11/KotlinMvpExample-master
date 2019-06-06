package com.exmple.baseprojectmvp.mvp.presenter.fragment

import android.util.Log
import com.exmple.baseprojectmvp.mvp.contract.fragment.ICategoryContract
import com.exmple.baseprojectmvp.mvp.contract.fragment.IFollowContract
import com.exmple.baseprojectmvp.mvp.model.fragment.CategoryModel
import com.exmple.baseprojectmvp.mvp.model.fragment.FollowModel
import com.exmple.corelib.http.retrofit.NetCallBack
import com.exmple.corelib.mSubscribe
import com.exmple.corelib.mvp.BasePresenterKt
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.presenter.fragment
 * @description:
 * @date: 2019/6/6
 * @time:  10:14
 */
class FollowPresenter : BasePresenterKt<IFollowContract.View>(), IFollowContract.Presenter {

    override var mModel: IFollowContract.Model? = FollowModel()

    private var nextPageUrl:String?=null

    override fun requestFollowList() {
        mModel?.requestFollowList()?.mSubscribe (mView,mModel,"正在获取数据中...",object : NetCallBack<HomeBean.Issue> {
            override fun success(rspBean: HomeBean.Issue) {
                nextPageUrl = rspBean.nextPageUrl

                mView?.setFollowInfo(rspBean)
            }

            override fun backFail(errStr: String) {
                mView?.showError(errStr)
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
            mModel?.loadMoreData(it)?.mSubscribe (mView,mModel,"正在获取数据中...",object : NetCallBack<HomeBean.Issue> {
                override fun success(rspBean: HomeBean.Issue) {

                    nextPageUrl = rspBean.nextPageUrl
                    mView?.setFollowInfo(rspBean)
                }

                override fun backFail(errStr: String) {
                    mView?.showError(errStr)
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