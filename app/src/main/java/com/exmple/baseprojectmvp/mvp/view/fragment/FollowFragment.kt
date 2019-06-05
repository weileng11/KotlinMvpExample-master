package com.exmple.baseprojectmvp.mvp.view.fragment

import android.os.Bundle
import android.view.View
import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.mvp.contract.fragment.IHomeContract
import com.exmple.baseprojectmvp.mvp.presenter.fragment.HomePresenter
import com.exmple.corelib.mvp.BaseMvpListFragment
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.view.fragment
 * @description: 关注
 * @date: 2019/6/5
 * @time:  14:19
 */
class FollowFragment : BaseMvpListFragment<IHomeContract.View, IHomeContract.Presenter>(), IHomeContract.View{

    override var mPresenter: IHomeContract.Presenter = HomePresenter()
    //    override val setRefreshEnable = true
    override val setRecyclerViewBgColor = R.color.white

    companion object {
        fun getInstance(title: String): FollowFragment {
            val fragment = FollowFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
//            fragment.mTitle = title
            return fragment
        }
    }

    override fun onRefresh() {
    }

    override fun onRetry() {
    }

    override fun lazyLoad() {
    }

    override fun initView(view: View) {
    }

    override fun loadMoreFail(isRefresh: Boolean) {
    }

    override fun setHomeData(homeBean: HomeBean) {
    }

    override fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>) {
    }

    override fun showError(msg: String) {
    }

}