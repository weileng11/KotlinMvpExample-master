package com.exmple.corelib.mvp

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import com.exmple.corelib.R
import com.exmple.corelib.state.IStateView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.layout_list.*

/**
 * Created by mou on 2018/5/6
 */
abstract class BaseMvpListFragment<V : ITopView, P : ITopPresenter> : BaseMvpFragment<V, P>(), IListView<P> {
    override fun getContentView() = R.layout.layout_list
    override val mStateView: IStateView by lazy { list_sv }
    override val mRecyclerView: RecyclerView by lazy { list_rv }
    override val mRefreshLayout: SmartRefreshLayout by lazy { refreshLayout }
    override fun initData() {
        //设置背景色
        context?.let { list_rv.setBackgroundColor(ContextCompat.getColor(it, setRecyclerViewBgColor)) }
        //重试
//        list_sv.onRetry = { onRetry() }

        refreshLayout.setEnableRefresh(true)
        //刷新
        refreshLayout.setOnRefreshListener { onRefresh() }
        refreshLayout.setEnableLoadMore(true)
//        refreshLayout.finishLoadMore(true)
        refreshLayout.setOnLoadMoreListener { onRetry()}
        //设置下拉刷新是否可用
//       refreshLayout.isEnabled = setRefreshEnable

    }

    abstract fun onRefresh() //上啦刷新
    abstract fun onRetry() //下拉刷新
    open val setRecyclerViewBgColor = R.color.white
//    open val setRefreshEnable = true
}