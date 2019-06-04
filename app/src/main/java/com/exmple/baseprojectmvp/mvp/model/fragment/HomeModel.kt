package com.exmple.baseprojectmvp.mvp.model.fragment

import com.exmple.baseprojectmvp.http.MainRetrofit1
import com.exmple.baseprojectmvp.mvp.contract.fragment.IHomeContract
import com.exmple.corelib.mvp.BaseModelKt
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import io.reactivex.Observable

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.model.fragment
 * @description:
 * @date: 2019/6/4
 * @time:  11:43
 */
class HomeModel : BaseModelKt(), IHomeContract.Model{
    /**
     * 获取首页 Banner 数据
     */
    override fun requestHomeData(num:Int): Observable<HomeBean> {
        return  MainRetrofit1.apiService.getFirstHomeData(num)
    }

    /**
     * 加载更多
     */
    override fun loadMoreData(url:String): Observable<HomeBean> {
        return MainRetrofit1.apiService.getMoreHomeData(url)
    }

}