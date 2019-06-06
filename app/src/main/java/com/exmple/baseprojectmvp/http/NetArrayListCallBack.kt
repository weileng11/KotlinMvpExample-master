package com.exmple.baseprojectmvp.http

import com.exmple.corelib.http.entity.BaseBean

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.corelib.http.retrofit
 * @description: 暂时没有用
 * @date: 2019/6/4
 * @time:  17:54
 */
interface NetArrayListCallBack <T>{

    @Throws(Exception::class)
    fun success(rspBean: ArrayList<T>)

    @Throws(Exception::class)
    fun backFail(errStr: String)

    fun fail(t: String?)

}