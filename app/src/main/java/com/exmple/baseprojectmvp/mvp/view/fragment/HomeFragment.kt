package com.exmple.baseprojectmvp.mvp.view.fragment

import android.os.Bundle
import android.view.View
import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.mvp.contract.fragment.IHomeContract
import com.exmple.baseprojectmvp.mvp.presenter.fragment.HomePresenter
import com.exmple.corelib.mvp.BaseMvpFragment
import com.exmple.corelib.mvp.BaseMvpListFragment
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.view.fragment
 * @description:首页
 * @date: 2019/6/4
 * @time:  11:51
 */
class HomeFragment : BaseMvpListFragment<IHomeContract.View, IHomeContract.Presenter>(),IHomeContract.View{
    override fun setHomeData(homeBean: HomeBean) {
    }

    override fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>) {
    }

    override fun showError(msg: String, errorCode: Int) {

    }

    override var mPresenter: IHomeContract.Presenter = HomePresenter()
    override val setRefreshEnable = true
    override val setRecyclerViewBgColor = R.color.white

    companion object {
        fun getInstance(title: String): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
//            fragment.mTitle = title
            return fragment
        }
    }


    override fun lazyLoad() {
    }

    override fun initView(view: View) {

    }

    override fun onRefresh() {

//        mPresenter.requestHomeData(num)
        mRefreshLayout.finishRefresh(false)
    }

    override fun onRetry() {
    }

    override fun loadMoreFail(isRefresh: Boolean) {
        mRefreshLayout.finishLoadMore(false)
    }


//    override fun getContentView(): Int {
//        return R.layout.fragment_home
//    }

}