package com.exmple.corelib.http.retrofit

import com.exmple.corelib.http.entity.BaseBean

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.corelib.http.retrofit
 * @description:
 * @date: 2019/6/4
 * @time:  17:54
 */
interface NetCallBack<T: BaseBean> {

    @Throws(Exception::class)
    fun success(rspBean: T)

    @Throws(Exception::class)
    fun backFail(errStr: String)

    fun fail(t: Throwable)

}