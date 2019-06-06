package com.hazz.kotlinmvp.mvp.model.bean

import com.exmple.corelib.http.entity.BaseBean

/**
 * Created by xuhao on 2017/11/30.
 * desc: 热门的 tabInfo
 */

data class TabInfoBean(val tabInfo: TabInfo) : BaseBean() {
    data class TabInfo(val tabList: ArrayList<Tab>)

    data class Tab(val id: Long, val name: String, val apiUrl: String)
}
