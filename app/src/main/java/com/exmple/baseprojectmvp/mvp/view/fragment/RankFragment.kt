package com.exmple.baseprojectmvp.mvp.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.mvp.adapter.CategoryDetailAdapter
import com.exmple.baseprojectmvp.mvp.adapter.FollowAdapter
import com.exmple.baseprojectmvp.mvp.contract.fragment.IHomeContract
import com.exmple.baseprojectmvp.mvp.contract.fragment.IRankContract
import com.exmple.baseprojectmvp.mvp.model.fragment.RankModel
import com.exmple.baseprojectmvp.mvp.presenter.fragment.HomePresenter
import com.exmple.baseprojectmvp.mvp.presenter.fragment.RankPresenter
import com.exmple.baseprojectmvp.widget.MultipleStatusView
import com.exmple.corelib.mvp.BaseMvpListFragment
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import kotlinx.android.synthetic.main.fragment_hot.*
import kotlinx.android.synthetic.main.fragment_rank.*

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.view.fragment
 * @description:发现
 * @date: 2019/6/4
 * @time:  11:55
 */
class RankFragment : BaseMvpListFragment<IRankContract.View, IRankContract.Presenter>(), IRankContract.View{
    override var mPresenter: IRankContract.Presenter = RankPresenter()

    private var apiUrl: String? = null

    override val setRecyclerViewBgColor = R.color.white
    //设置第一次请求的数据
    private var mAdapter : CategoryDetailAdapter? = null

    private  var list= ArrayList<HomeBean.Issue.Item>()
    /**
     * 多种状态的 View 的切换
     */
    protected var mLayoutStatusView: MultipleStatusView? = null

    companion object {
        fun getInstance(apiUrl: String): RankFragment {
            val fragment = RankFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.apiUrl = apiUrl
            return fragment
        }
    }

    override fun lazyLoad() {
        if(!apiUrl.isNullOrEmpty()) {
            mPresenter.requestRankList(apiUrl!!)
        }
    }

    override fun initView(view: View) {
        //设置到适配
        mAdapter= CategoryDetailAdapter(categoryList =list)
        mRecyclerView.adapter = mAdapter
    }

    override fun loadMoreFail(isRefresh: Boolean) {
        mRefreshLayout.finishLoadMore(true)
    }

    //(上啦刷新)
    override fun onRefresh() {
        mRefreshLayout.finishRefresh(true)
    }

    //重试(下拉刷新)
    override fun onRetry() {
        mRefreshLayout.finishLoadMore(true)
    }

    override fun setRankList(itemList: ArrayList<HomeBean.Issue.Item>) {
        list.addAll(itemList)
        mAdapter?.addData(list)
    }
    override fun showError(msg: String) {
        showToast(msg)
    }
}