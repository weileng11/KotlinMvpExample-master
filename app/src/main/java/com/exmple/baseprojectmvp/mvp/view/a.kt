package com.exmple.baseprojectmvp.mvp.view


import com.exmple.corelib.http.entity.BaseBean

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.view
 * @description:
 * @date: 2019/6/4
 * @time: 18:09
 */
class a {

    fun aaa() {
        c(object : NetCallBack<List<BaseBean>, BaseBean> {
            @Throws(Exception::class)
            override fun success(rspBean: List<BaseBean>) {

            }

            @Throws(Exception::class)
            override fun backFail(rspBean: BaseBean, errStr: String) {

            }

            override fun fail(t: Throwable) {

            }
        })
    }

    fun c(callBack: NetCallBack<*, *>) {

    }

    interface NetCallBack<T, K : BaseBean> {

        @Throws(Exception::class)
        fun success(rspBean: T)

        @Throws(Exception::class)
        fun backFail(rspBean: K, errStr: String)

        fun fail(t: Throwable)

    }
}
