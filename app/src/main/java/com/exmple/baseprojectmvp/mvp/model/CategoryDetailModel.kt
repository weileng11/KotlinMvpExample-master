package com.exmple.baseprojectmvp.mvp.model

import com.exmple.baseprojectmvp.http.MainRetrofit1
import com.exmple.baseprojectmvp.mvp.contract.ICategoryDetailContract
import com.exmple.baseprojectmvp.mvp.contract.ISearchContract
import com.exmple.corelib.mvp.BaseModelKt
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import io.reactivex.Observable

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.model
 * @description:
 * @date: 2019/6/10
 * @time:  11:28
 */
class CategoryDetailModel : BaseModelKt(), ICategoryDetailContract.Model {

    /**
     * 获取分类下的 List 数据
     */
    override fun getCategoryDetailList(id: Long): Observable<HomeBean.Issue> {
        return MainRetrofit1.apiService.getCategoryDetailList(id)
    }

    /**
     * 加载更多数据
     */
    override fun loadMoreData(url: String): Observable<HomeBean.Issue> {
        return MainRetrofit1.apiService.getIssueData(url)
    }

}