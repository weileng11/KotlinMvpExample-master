package com.exmple.baseprojectmvp.mvp.contract

import com.exmple.corelib.mvp.IModel
import com.exmple.corelib.mvp.IPresenter
import com.exmple.corelib.mvp.IView
import com.hazz.kotlinmvp.mvp.model.bean.CategoryBean
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import io.reactivex.Observable

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.contract
 * @description:
 * @date: 2019/6/10
 * @time:  11:20
 */
interface ICategoryDetailContract {

    interface View : IView<Presenter> {
        /**
         *  设置列表数据
         */
        fun setCateDetailList(itemList: ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg: String)
    }

    interface Presenter : IPresenter<View, Model> {
        fun getCategoryDetailList(id: Long)

        fun loadMoreData()
    }

    interface Model : IModel {
        /**
         * 获取首页精选数据
         */
        fun getCategoryDetailList(id:Long): Observable<HomeBean.Issue>

        fun loadMoreData(url:String): Observable<HomeBean.Issue>
    }
}