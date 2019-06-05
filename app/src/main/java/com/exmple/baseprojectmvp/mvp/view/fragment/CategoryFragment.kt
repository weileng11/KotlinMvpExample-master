package com.exmple.baseprojectmvp.mvp.view.fragment

import android.os.Bundle
import android.view.View
import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.mvp.adapter.CategoryAdapter
import com.exmple.baseprojectmvp.mvp.adapter.HomeAdapter
import com.exmple.baseprojectmvp.mvp.contract.fragment.ICategoryContract
import com.exmple.baseprojectmvp.mvp.presenter.fragment.CategoryPresenter
import com.exmple.corelib.mvp.BaseMvpListFragment
import com.hazz.kotlinmvp.mvp.model.bean.CategoryBean

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.view.fragment
 * @description: 分类
 * @date: 2019/6/5
 * @time:  14:19
 */
class CategoryFragment :BaseMvpListFragment<ICategoryContract.View, ICategoryContract.Presenter>(), ICategoryContract.View{


    override var mPresenter: ICategoryContract.Presenter = CategoryPresenter()
    //    override val setRefreshEnable = true
    override val setRecyclerViewBgColor = R.color.white
    //设置第一次请求的数据
    private var demoAdapter : CategoryAdapter? = null

    private  var categoryList= ArrayList<CategoryBean>()
    companion object {
        fun getInstance(title: String): CategoryFragment {
            val fragment = CategoryFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
//            fragment.mTitle = title
            return fragment
        }
    }

    override fun onRefresh() {
        mRefreshLayout.finishRefresh(true)
    }

    override fun onRetry() {
        mRefreshLayout.finishLoadMore(true)
    }

    override fun lazyLoad() {
        mPresenter.getCategoryData()
    }

    override fun initView(view: View) {
        //设置到适配
        demoAdapter= CategoryAdapter(categoryList =categoryList)
//        demoAdapter.setLoadMoreView(CustomLoadMoreView())
        mRecyclerView.adapter = demoAdapter
    }

    override fun loadMoreFail(isRefresh: Boolean) {
        mRefreshLayout.finishLoadMore(true)
    }

    override fun showError(msg: String) {
        showToast(msg)
    }

    override fun showCategory(cgList: ArrayList<CategoryBean>) {
        categoryList.addAll(cgList)
        demoAdapter?.addData(categoryList)
    }
}