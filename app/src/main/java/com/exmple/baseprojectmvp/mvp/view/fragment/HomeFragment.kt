package com.exmple.baseprojectmvp.mvp.view.fragment

import android.os.Bundle
import android.view.View
import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.mvp.adapter.HomeAdapter
import com.exmple.baseprojectmvp.mvp.contract.fragment.IHomeContract
import com.exmple.baseprojectmvp.mvp.presenter.fragment.HomePresenter
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

    override var mPresenter: IHomeContract.Presenter = HomePresenter()
//    override val setRefreshEnable = true
    override val setRecyclerViewBgColor = R.color.white

    private var num: Int = 1
    //设置第一次请求的数据
    private lateinit var demoAdapter :HomeAdapter

    companion object {
        fun getInstance(title: String): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
//            fragment.mTitle = title
            return fragment
        }
    }


    //初始化获取数据
    override fun lazyLoad() {
        mPresenter.requestHomeData(num)
    }

    override fun initView(view: View) {

    }

    //(上啦刷新)
    override fun onRefresh() {

//        mPresenter.requestHomeData(num)
        mPresenter.loadMoreData()
        mRefreshLayout.finishRefresh(true)
    }

    //重试(下拉刷新)
    override fun onRetry() {
        mPresenter.loadMoreData()
        mRefreshLayout.finishLoadMore(true)
    }

    override fun loadMoreFail(isRefresh: Boolean) {
        mRefreshLayout.finishLoadMore(true)
    }

    override fun setHomeData(homeBean: HomeBean) {
        //设置到适配
        demoAdapter= HomeAdapter(data = homeBean.issueList[0].itemList)
//        demoAdapter.setLoadMoreView(CustomLoadMoreView())
        mRecyclerView.adapter = demoAdapter
//        demoAdapter.setOnLoadMoreListener({ demoAdapter.loadMoreEnd() }, mRecyclerView)
    }

    //设置加载更多的数据
    override fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>) {
        demoAdapter.addData(itemList)
    }

    //显示错误信息
    override fun showError(msg: String) {
        showToast(msg)
    }

//    override fun getContentView(): Int {
//        return R.layout.fragment_home
//    }

}