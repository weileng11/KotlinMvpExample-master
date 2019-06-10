package com.exmple.baseprojectmvp.mvp.contract.fragment

import com.exmple.corelib.mvp.IListView
import com.exmple.corelib.mvp.IModel
import com.exmple.corelib.mvp.IPresenter
import com.exmple.corelib.mvp.IView
import com.hazz.kotlinmvp.mvp.model.bean.CategoryBean
import com.hazz.kotlinmvp.mvp.model.bean.TabInfoBean
import io.reactivex.Observable

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.contract.fragment
 * @description:
 * @date: 2019/6/6
 * @time:  11:17
 */
interface IHotTabContract {

    interface View : IView<Presenter> {
        /**
         * 设置 TabInfo
         */
        fun setTabInfo(tabInfoBean: TabInfoBean)

        fun showError(errorMsg:String)
    }

    interface Presenter : IPresenter<View, Model> {
        /**
         * 获取 TabInfo
         */
        fun getTabInfo()
    }

    interface Model : IModel {
        /**
         * 获取首页精选数据
         */
        fun getTabInfo() :  Observable<TabInfoBean>
    }
}