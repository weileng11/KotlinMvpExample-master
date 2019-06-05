package com.exmple.baseprojectmvp.mvp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.mvp.adapter.BaseFragmentAdapter
import com.exmple.baseprojectmvp.widget.TabLayoutHelper
import com.exmple.corelib.base.BaseFragment
import com.exmple.corelib.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_hot.*

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.view.fragment
 * @description://发现(包含关注和分类)
 * @date: 2019/6/4
 * @time:  11:54
 */
class DiscoveryFragment : BaseFragment(){

    private val tabList=ArrayList<String>()
    private val fragments=ArrayList<Fragment>()
    private var mTitle :String?=null

    companion object {
        fun getInstance(title: String): DiscoveryFragment {
            val fragment = DiscoveryFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun lazyLoad() {
    }

    override fun getContentView(): Int = R.layout.fragment_hot

    override fun initView(view: View) {
        //状态栏透明和间距处理
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, toolbar) }

        tv_header_title.text = mTitle
        tabList.add("关注")
        tabList.add("分类")

        fragments.add(FollowFragment.getInstance("关注"))
        fragments.add(CategoryFragment.getInstance("分类"))

        /**
         * getSupportFragmentManager() 替换为getChildFragmentManager()
         */
        mViewPager.adapter= BaseFragmentAdapter(childFragmentManager, fragments, tabList)
        mTabLayout.setupWithViewPager(mViewPager)
        TabLayoutHelper.setUpIndicatorWidth(mTabLayout)
    }


}