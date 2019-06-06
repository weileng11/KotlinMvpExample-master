package com.exmple.baseprojectmvp.mvp.view.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.R.id.*
import com.exmple.baseprojectmvp.mvp.adapter.BaseFragmentAdapter
import com.exmple.baseprojectmvp.mvp.contract.fragment.IHotTabContract
import com.exmple.baseprojectmvp.mvp.presenter.fragment.HotTabPresenter
import com.exmple.baseprojectmvp.widget.MultipleStatusView
import com.exmple.corelib.mvp.BaseMvpFragment
import com.exmple.corelib.mvp.BaseMvpListFragment
import com.exmple.corelib.state.IStateView
import com.exmple.corelib.utils.StatusBarUtil
import com.hazz.kotlinmvp.mvp.model.bean.TabInfoBean
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.fragment_hot.*

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.view.fragment
 * @description:热门
 * @date: 2019/6/4
 * @time:  11:54
 */
 class HotFragment : BaseMvpFragment<IHotTabContract.View, IHotTabContract.Presenter>(),IHotTabContract.View{
    override val mRecyclerView: RecyclerView?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val mStateView: IStateView?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    override val mRefreshLayout: SmartRefreshLayout
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override var mPresenter: IHotTabContract.Presenter = HotTabPresenter()

    private var mTitle: String? = null
    /**
     * 存放 tab 标题
     */
    private val mTabTitleList = ArrayList<String>()

    private val mFragmentList = ArrayList<Fragment>()

    /**
     * 多种状态的 View 的切换
     */
    protected var mLayoutStatusView: MultipleStatusView? = null

    companion object {
        fun getInstance(title: String): HotFragment {
            val fragment = HotFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun lazyLoad() {
        mPresenter.getTabInfo()
    }

    override fun getContentView(): Int {
        return R.layout.fragment_hot
    }

    override fun initView(view: View) {
        mLayoutStatusView = multipleStatusView
        //状态栏透明和间距处理
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, toolbar) }

        //多种状态切换的view 重试点击事件
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        lazyLoad()
    }

    override fun loadMoreFail(isRefresh: Boolean) {
        mRefreshLayout.finishLoadMore(true)
    }

    override fun setTabInfo(tabInfoBean: TabInfoBean) {

        multipleStatusView.showContent()

        tabInfoBean.tabInfo.tabList.mapTo(mTabTitleList) { it.name }
        tabInfoBean.tabInfo.tabList.mapTo(mFragmentList) { RankFragment.getInstance(it.apiUrl) }

        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager,mFragmentList,mTabTitleList)
        mViewPager.offscreenPageLimit=3
        mTabLayout.setupWithViewPager(mViewPager)

        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab?.getText()!!.equals("周排行")){
//                    RankFragment.getInstance()
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    override fun showError(msg: String) {
           showToast(msg)
    }

}