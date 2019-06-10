package com.exmple.baseprojectmvp.mvp.contract.fragment

import com.exmple.baseprojectmvp.http.MainDataBean
import com.exmple.corelib.mvp.IListView
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
 * @date: 2019/6/4
 * @time:  11:41
 */
interface IHomeContract {
    interface View : IView<Presenter> {
        /**
         * 设置第一次请求的数据
         */
        fun setHomeData(homeBean: HomeBean)

        /**
         * 设置加载更多的数据
         */
        fun setMoreData(itemList:ArrayList<HomeBean.Issue.Item>)

        /**
         * 显示错误信息
         */
        fun showError(msg: String)
    }
    interface Presenter : IPresenter<View, Model> {

        /**
         * 获取首页精选数据
         */
        fun requestHomeData(num: Int)

        /**
         * 加载更多数据
         */
        fun loadMoreData()
    }

    interface Model : IModel {
        /**
         * 获取首页精选数据
         */
        fun requestHomeData(num:Int) :Observable<HomeBean>

        /**
         * 加载更多数据
         */
        fun loadMoreData(str:String) :Observable<HomeBean>
    }

}