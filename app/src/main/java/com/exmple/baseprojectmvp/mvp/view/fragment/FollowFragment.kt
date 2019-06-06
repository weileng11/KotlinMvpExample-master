package com.exmple.baseprojectmvp.mvp.view.fragment

import android.os.Bundle
import android.view.View
import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.mvp.adapter.CategoryAdapter
import com.exmple.baseprojectmvp.mvp.adapter.FollowAdapter
import com.exmple.baseprojectmvp.mvp.contract.fragment.IFollowContract
import com.exmple.baseprojectmvp.mvp.contract.fragment.IHomeContract
import com.exmple.baseprojectmvp.mvp.presenter.fragment.FollowPresenter
import com.exmple.baseprojectmvp.mvp.presenter.fragment.HomePresenter
import com.exmple.corelib.mvp.BaseMvpListFragment
import com.hazz.kotlinmvp.mvp.model.bean.CategoryBean
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.view.fragment
 * @description: 关注
 * @date: 2019/6/5
 * @time:  14:19
 */
class FollowFragment : BaseMvpListFragment<IFollowContract.View, IFollowContract.Presenter>(), IFollowContract.View{

    override var mPresenter: IFollowContract.Presenter = FollowPresenter()
    //    override val setRefreshEnable = true
    override val setRecyclerViewBgColor = R.color.white

    //设置第一次请求的数据
    private var demoAdapter : FollowAdapter? = null

    private  var followList= ArrayList<HomeBean.Issue.Item>()

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
//        mPresenter.requestFollowList()
        mRefreshLayout.finishRefresh(true)
    }

    override fun onRetry() {
        mPresenter.loadMoreData()
        mRefreshLayout.finishLoadMore(true)
    }

    override fun lazyLoad() {
        mPresenter.requestFollowList()
    }

    override fun initView(view: View) {
        //设置到适配
        demoAdapter= FollowAdapter(dataList =followList)
//        demoAdapter.setLoadMoreView(CustomLoadMoreView())
        mRecyclerView.adapter = demoAdapter
    }

    override fun loadMoreFail(isRefresh: Boolean) {
        mRefreshLayout.finishLoadMore(true)
    }

    override fun setFollowInfo(issue: HomeBean.Issue) {
        followList.addAll(issue.itemList)
        demoAdapter?.addData(followList)
    }

    override fun showError(msg: String) {
        showToast(msg)
    }

}