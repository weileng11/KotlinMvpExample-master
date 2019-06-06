package com.exmple.baseprojectmvp.mvp.model.fragment

import com.exmple.baseprojectmvp.http.MainRetrofit1
import com.exmple.baseprojectmvp.mvp.contract.fragment.IRankContract
import com.exmple.corelib.mvp.BaseModelKt
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import io.reactivex.Observable

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.model.fragment
 * @description:
 * @date: 2019/6/6
 * @time:  11:46
 */
class RankModel : BaseModelKt(), IRankContract.Model{

    /**
     * 获取 TabInfo
     */
    override fun requestRankList(apiUrl:String): Observable<HomeBean.Issue> {
        return  MainRetrofit1.apiService.getIssueData(apiUrl)
    }

}