package com.exmple.baseprojectmvp.mvp.model.fragment

import com.exmple.baseprojectmvp.http.MainRetrofit1
import com.exmple.baseprojectmvp.mvp.contract.fragment.IHotTabContract
import com.exmple.corelib.mvp.BaseModelKt
import com.hazz.kotlinmvp.mvp.model.bean.TabInfoBean
import io.reactivex.Observable

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.model.fragment
 * @description:
 * @date: 2019/6/6
 * @time:  11:17
 */
class HotTabModel : BaseModelKt(), IHotTabContract.Model{

    /**
     * 获取 TabInfo
     */
    override fun getTabInfo(): Observable<TabInfoBean> {
        return  MainRetrofit1.apiService.getRankList()
    }

}