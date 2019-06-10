package com.exmple.baseprojectmvp.mvp.contract.fragment

import com.exmple.corelib.mvp.IModel
import com.exmple.corelib.mvp.IPresenter
import com.exmple.corelib.mvp.IView
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import io.reactivex.Observable

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.contract.fragment
 * @description:
 * @date: 2019/6/6
 * @time:  10:15
 */
interface IFollowContract {

    interface View : IView<Presenter> {
        /**
         * 设置关注信息数据
         */
        fun setFollowInfo(issue: HomeBean.Issue)

        /**
         * 显示错误信息
         */
        fun showError(errorMsg: String)
    }

    interface Presenter : IPresenter<View, Model> {
        /**
         * 获取List
         */
        fun requestFollowList()

        /**
         * 加载更多
         */
        fun loadMoreData()
    }

    interface Model : IModel {
        /**
         * 获取List
         */
        fun requestFollowList() : Observable<HomeBean.Issue>

        /**
         * 加载更多
         */
        fun loadMoreData(url:String) :Observable<HomeBean.Issue>
    }
}