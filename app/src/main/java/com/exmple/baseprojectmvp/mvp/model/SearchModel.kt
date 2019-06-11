package com.exmple.baseprojectmvp.mvp.model

import com.exmple.baseprojectmvp.http.MainDataBean
import com.exmple.baseprojectmvp.http.MainRetrofit
import com.exmple.baseprojectmvp.http.MainRetrofit1
import com.exmple.baseprojectmvp.mvp.contract.IMainContact
import com.exmple.baseprojectmvp.mvp.contract.ISearchContract
import com.exmple.corelib.mvp.BaseModelKt
import com.exmple.corelib.scheduler.SchedulerUtils
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import io.reactivex.Observable

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.model
 * @description:
 * @date: 2019/6/10
 * @time:  11:19
 */
class SearchModel : BaseModelKt(), ISearchContract.Model {
    /**
     * 请求热门关键词的数据
     */
    override fun requestHotWordData(): Observable<ArrayList<String>> {

        return MainRetrofit1.apiService.getHotWord()
    }

    /**
     * 搜索关键词返回的结果
     */
    override fun getSearchResult(words: String):Observable<HomeBean.Issue>{
        return MainRetrofit1.apiService.getSearchData(words)
    }

    /**
     * 加载更多数据
     */
    override fun loadMoreData(url: String): Observable<HomeBean.Issue> {
        return MainRetrofit1.apiService.getIssueData(url)
    }
}