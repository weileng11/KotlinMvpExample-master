package com.exmple.baseprojectmvp.mvp.contract.fragment

import com.exmple.corelib.mvp.IListView
import com.exmple.corelib.mvp.IModel
import com.exmple.corelib.mvp.IPresenter
import com.exmple.corelib.mvp.IView
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import com.hazz.kotlinmvp.mvp.model.bean.TabInfoBean
import io.reactivex.Observable

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.contract.fragment
 * @description:
 * @date: 2019/6/6
 * @time:  11:45
 */
interface IRankContract {
    interface View : IView<Presenter> {
        /**
         * 设置排行榜的数据
         */
        fun setRankList(itemList: ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg: String)
    }

    interface Presenter : IPresenter<View, Model> {
        /**
         * 获取 TabInfo
         */
        fun requestRankList(apiUrl:String)
    }

    interface Model : IModel {
        /**
         * 获取首页精选数据
         */
        fun requestRankList(apiUrl:String): Observable<HomeBean.Issue>
    }
}