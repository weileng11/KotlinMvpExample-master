package com.exmple.baseprojectmvp.mvp.model

import com.exmple.baseprojectmvp.http.MainRetrofit
import com.exmple.baseprojectmvp.http.MainRetrofit1
import com.exmple.baseprojectmvp.mvp.contract.IVideoDetailContract
import com.exmple.corelib.mvp.BaseModelKt
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import io.reactivex.Observable

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.model
 * @description:
 * @date: 2019/6/6
 * @time:  16:00
 */
class VideoDetailModel : BaseModelKt(), IVideoDetailContract.Model {

    override fun requestRelatedVideo(id: Long): Observable<HomeBean.Issue> {
        return MainRetrofit1.apiService.getRelatedData(id)
    }


}