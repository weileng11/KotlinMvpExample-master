package com.exmple.baseprojectmvp.mvp.model.fragment

import com.exmple.baseprojectmvp.http.MainRetrofit1
import com.exmple.baseprojectmvp.mvp.contract.fragment.IFollowContract
import com.exmple.corelib.mvp.BaseModelKt
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import io.reactivex.Observable

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.model.fragment
 * @description: 关注
 * @date: 2019/6/6
 * @time:  10:15
 */
class FollowModel : BaseModelKt(), IFollowContract.Model{

    /**
     * 获取关注信息
     */
    override fun requestFollowList(): Observable<HomeBean.Issue> {
        return MainRetrofit1.apiService.getFollowInfo()
    }

    /**
     * 加载更多
     */
    override fun loadMoreData(url:String):Observable<HomeBean.Issue>{
        return MainRetrofit1.apiService.getIssueData(url)
    }
}