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
 * @description:发现
 * @date: 2019/6/4
 * @time:  11:55
 */
class RankFragment : BaseMvpListFragment<IHomeContract.View, IHomeContract.Presenter>(), IHomeContract.View{

    override var mPresenter: IHomeContract.Presenter = HomePresenter()

    companion object {
        fun getInstance(title: String): RankFragment {
            val fragment = RankFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
//            fragment.mTitle = title
            return fragment
        }
    }

    override fun lazyLoad() {
    }

    override fun getContentView(): Int {
        return R.layout.fragment_rank
    }

    override fun initView(view: View) {
    }

    override fun loadMoreFail(isRefresh: Boolean) {
    }

    override fun onRefresh() {
    }

    override fun onRetry() {
    }

    override fun setHomeData(homeBean: HomeBean) {
    }

    override fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>) {
    }

    override fun showError(msg: String) {
    }
}