package com.exmple.baseprojectmvp.mvp.contract.fragment

import com.exmple.corelib.mvp.IListView
import com.exmple.corelib.mvp.IModel
import com.exmple.corelib.mvp.IPresenter
import com.exmple.corelib.mvp.IView
import com.hazz.kotlinmvp.mvp.model.bean.CategoryBean
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import io.reactivex.Observable

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.contract.fragment
 * @description:
 * @date: 2019/6/5
 * @time:  14:40
 */
interface ICategoryContract {

    interface View : IView<Presenter> {
        /**
         * 显示分类的信息
         */
        fun showCategory(categoryList: ArrayList<CategoryBean>)

        /**
         * 显示错误信息
         */
        fun showError(errorMsg: String)
    }

    interface Presenter : IPresenter<View, Model> {
        /**
         * 获取分类的信息
         */
        fun getCategoryData()
    }

    interface Model : IModel {
        /**
         * 获取首页精选数据
         */
        fun getCategoryData() : Observable<ArrayList<CategoryBean>>
    }
}