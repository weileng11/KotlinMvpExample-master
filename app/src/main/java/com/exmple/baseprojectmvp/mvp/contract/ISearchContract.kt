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
 * @time:  11:19
 */
interface ISearchContract {

    interface View : IView<Presenter> {
        /**
         * 设置热门关键词数据
         */
        fun setHotWordData(string: ArrayList<String>)

        /**
         * 设置搜索关键词返回的结果
         */
        fun setSearchResult(issue: HomeBean.Issue)
        /**
         * 关闭软件盘
         */
        fun closeSoftKeyboard()

        /**
         * 设置空 View
         */
        fun setEmptyView()


        fun showError(errorMsg: String)
    }

    interface Presenter : IPresenter<View, Model> {
        /**
         * 获取热门关键字的数据
         */
        fun requestHotWordData()

        /**
         * 查询搜索
         */
        fun querySearchData(words:String)

        /**
         * 加载更多
         */
        fun loadMoreData()
    }

    interface Model : IModel {
        /**
         * 获取热门关键字的数据
         */
        fun requestHotWordData() :Observable<ArrayList<String>>

        /**
         * 查询搜索
         */
        fun getSearchResult(words:String) :Observable<HomeBean.Issue>

        /**
         * 加载更多
         */
        fun loadMoreData(url:String): Observable<HomeBean.Issue>
    }
}