package com.exmple.baseprojectmvp.mvp.view

import android.support.v7.widget.LinearLayoutManager
import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.mvp.adapter.WatchHistoryAdapter
import com.exmple.baseprojectmvp.widget.Constants
import com.exmple.baseprojectmvp.widget.WatchHistoryUtils
import com.exmple.corelib.base.BaseActivity
import com.exmple.corelib.base.LibApplication
import com.exmple.corelib.utils.StatusBarUtil
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import kotlinx.android.synthetic.main.layout_watch_history.*
import java.util.*

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.view
 * @description:观看记录
 * @date: 2019/6/6
 * @time:  15:32
 */
class WatchHistoryActivity : BaseActivity() {

    private var itemListData = ArrayList<HomeBean.Issue.Item>()

    companion object {
        private const val HISTORY_MAX = 20
    }

    override fun getContentView(): Int {
        return  R.layout.layout_watch_history
    }

    override fun initView() {
        multipleStatusView.showLoading()
        itemListData = queryWatchHistory()

    }

    override fun initData() {
        //返回
        toolbar.setNavigationOnClickListener { finish() }

        val mAdapter = WatchHistoryAdapter(dataList=itemListData)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter

        if (itemListData.size > 0) {
            multipleStatusView.showContent()
        } else {
            multipleStatusView.showEmpty()
        }

        //状态栏透明和间距处理
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, toolbar)
        StatusBarUtil.setPaddingSmart(this, mRecyclerView)
    }

    /**
     * 查询观看的历史记录
     */
    private fun queryWatchHistory(): ArrayList<HomeBean.Issue.Item> {
        val watchList = ArrayList<HomeBean.Issue.Item>()
        val hisAll = WatchHistoryUtils.getAll(Constants.FILE_WATCH_HISTORY_NAME, LibApplication.mContext) as Map<*, *>
        //将key排序升序
        val keys = hisAll.keys.toTypedArray()
        Arrays.sort(keys)
        val keyLength = keys.size
        //这里计算 如果历史记录条数是大于 可以显示的最大条数，则用最大条数做循环条件，防止历史记录条数-最大条数为负值，数组越界
        val hisLength = if (keyLength > HISTORY_MAX) HISTORY_MAX else keyLength
        // 反序列化和遍历 添加观看的历史记录
        (1..hisLength).mapTo(watchList) {
            WatchHistoryUtils.getObject(Constants.FILE_WATCH_HISTORY_NAME, LibApplication.mContext,
                    keys[keyLength - it] as String) as HomeBean.Issue.Item
        }

        return watchList
    }

}