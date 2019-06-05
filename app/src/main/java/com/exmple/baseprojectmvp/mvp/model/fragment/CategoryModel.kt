package com.exmple.baseprojectmvp.mvp.model.fragment

import com.exmple.baseprojectmvp.http.MainRetrofit1
import com.exmple.baseprojectmvp.mvp.contract.fragment.ICategoryContract
import com.exmple.corelib.mvp.BaseModelKt
import com.hazz.kotlinmvp.mvp.model.bean.CategoryBean
import io.reactivex.Observable

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.model.fragment
 * @description:
 * @date: 2019/6/5
 * @time:  14:39
 */
class CategoryModel : BaseModelKt(), ICategoryContract.Model{

    override fun getCategoryData(): Observable<ArrayList<CategoryBean>> {
        return  MainRetrofit1.apiService.getCategory()
    }
}