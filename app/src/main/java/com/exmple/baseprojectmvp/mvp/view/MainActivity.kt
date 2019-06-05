package com.exmple.baseprojectmvp.mvp.view

import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.http.MainDataBean
import com.exmple.baseprojectmvp.mvp.adapter.DemoAdapter
import com.exmple.baseprojectmvp.mvp.contract.IMainContact
import com.exmple.baseprojectmvp.mvp.presenter.MainPresenter
import com.exmple.corelib.utils.CustomLoadMoreView
import com.sihaiwanlian.baselib.mvp.BaseMvpTitleListActivity


class MainActivity : BaseMvpTitleListActivity<IMainContact.View, IMainContact.Presenter>(), IMainContact.View {

    override var mPresenter: IMainContact.Presenter = MainPresenter()
    override val setRefreshEnable = true
    override val setRecyclerViewBgColor = R.color.white
    override fun initData() {
        super.initData()
        setActivityTitle("学习kotlin")

        mPresenter.getDataByNet()

        val data = ArrayList<String>()
        data.add("")
        data.add("")
        data.add("")
        val demoAdapter = DemoAdapter(data = data)
        demoAdapter.setLoadMoreView(CustomLoadMoreView())
        mRecyclerView.adapter = demoAdapter
        demoAdapter.setOnLoadMoreListener({ demoAdapter.loadMoreEnd() }, mRecyclerView)
    }

    override fun onRetry() {

    }

    override fun onRefresh() {
        mRefreshLayout.finishRefresh(false)
    }

    override fun loadMoreFail(isRefresh: Boolean) {

    }

    private var list:List<MainDataBean>?=null
    override fun getDataSuccess() {

    }
}
