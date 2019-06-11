package com.exmple.baseprojectmvp.mvp.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import android.view.View
import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.R.id.tab_layout
import com.exmple.baseprojectmvp.mvp.contract.IMainContact
import com.exmple.baseprojectmvp.mvp.presenter.MainPresenter
import com.exmple.baseprojectmvp.mvp.view.fragment.DiscoveryFragment
import com.exmple.baseprojectmvp.mvp.view.fragment.HomeFragment
import com.exmple.baseprojectmvp.mvp.view.fragment.HotFragment
import com.exmple.baseprojectmvp.mvp.view.fragment.MineFragment
import com.exmple.corelib.base.BaseActivity
import com.exmple.corelib.showToastCenter
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.hazz.kotlinmvp.mvp.model.bean.TabEntity
import com.sihaiwanlian.baselib.mvp.BaseMvpTitleActivity
import kotlinx.android.synthetic.main.activity_main1.*
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.view
 * @description: 主页
 * @date: 2019/6/4
 * @time:  10:58
 */
 class MainActivity1 : BaseActivity() {
    private val mTitles = arrayOf("每日精选", "发现", "热门", "我的")

    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_home_normal, R.mipmap.ic_discovery_normal, R.mipmap.ic_hot_normal, R.mipmap.ic_mine_normal)
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.ic_home_selected, R.mipmap.ic_discovery_selected, R.mipmap.ic_hot_selected, R.mipmap.ic_mine_selected)

    private val mTabEntities=ArrayList<CustomTabEntity>()

    private var mHomeFragment: HomeFragment? = null //首页
    private var mDiscoveryFragment : DiscoveryFragment?=null //发现
    private var mHotFragment: HotFragment? = null //热门
    private var mMineFragment: MineFragment? = null //我的

    //默认为0
    private var mIndex = 0

    override fun getContentView(): Int {
        return R.layout.activity_main1
    }

    override fun initView() {
        btn_search.setOnClickListener { openSearchActivity() }
    }

    private fun openSearchActivity() {
        startActivity(Intent(this, SearchActivity::class.java))

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val options = this?.let { ActivityOptionsCompat.makeSceneTransitionAnimation(it, btn_search, btn_search.transitionName) }
//            startActivity(Intent(this, SearchActivity::class.java), options?.toBundle())
//        } else {
//            startActivity(Intent(this, SearchActivity::class.java))
//        }
    }

    override fun onSetContentViewNext(savedInstanceState: Bundle?) {
        super.onSetContentViewNext(savedInstanceState)
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        initTab()
        tab_layout.currentTab = mIndex
        switchFragment(mIndex)
    }

    private fun initTab() {
        (0 until mTitles.size).mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        //为Tab赋值
        tab_layout.run {
            setTabData(mTabEntities)
            tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
                override fun onTabSelect(position: Int) {
                    //切换Fragment
                    switchFragment(position)
                }

                override fun onTabReselect(position: Int) {

                }
            })
        }
    }

    /**
     * 切换Fragment
     * @param position 下标
     */
    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 // 首页
            -> mHomeFragment?.let {
                transaction.show(it)
            } ?: HomeFragment.getInstance(mTitles[position]).let {
                mHomeFragment = it
                transaction.add(R.id.fl_container, it, "home")
            }
            1  //发现
            -> mDiscoveryFragment?.let {
                transaction.show(it)
            } ?: DiscoveryFragment.getInstance(mTitles[position]).let {
                mDiscoveryFragment = it
                transaction.add(R.id.fl_container, it, "discovery") }
            2  //热门
            -> mHotFragment?.let {
                transaction.show(it)
            } ?: HotFragment.getInstance(mTitles[position]).let {
                mHotFragment = it
                transaction.add(R.id.fl_container, it, "hot") }
            3 //我的
            -> mMineFragment?.let {
                transaction.show(it)
            } ?: MineFragment.getInstance(mTitles[position]).let {
                mMineFragment = it
                transaction.add(R.id.fl_container, it, "mine") }

            else -> {

            }
        }

        mIndex = position
        tab_layout.currentTab = mIndex
        transaction.commitAllowingStateLoss()
    }

    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mDiscoveryFragment?.let { transaction.hide(it) }
        mHotFragment?.let { transaction.hide(it) }
        mMineFragment?.let { transaction.hide(it) }
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
//        showToast("onSaveInstanceState->"+mIndex)
//        super.onSaveInstanceState(outState)
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        if (tab_layout != null) {
            outState.putInt("currTabIndex", mIndex)
        }
    }

    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                showToastCenter("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}