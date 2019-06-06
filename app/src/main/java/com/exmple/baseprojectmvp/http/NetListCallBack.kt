package com.exmple.baseprojectmvp.http

import android.util.Log
import com.exmple.corelib.http.entity.BaseBean
import com.exmple.corelib.http.retrofit.NetCallBack
import com.exmple.corelib.scheduler.SchedulerUtils
import com.exmple.corelib.showToastBottom
import com.exmple.corelib.utils.NetworkUtils
import com.google.gson.JsonParseException
import com.hazz.kotlinmvp.mvp.model.bean.CategoryBean
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.*

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.http
 * @description:暂时没有用
 * @date: 2019/6/5
 * @time:  18:10
 */
object  NetListCallBack{

      fun getArrayListData(observable:Observable<Objects>,callBack: NetArrayListCallBack<Objects>) {
         observable?.compose(SchedulerUtils.ioToMain()).subscribe()
                 (object : Observer<ArrayList<Objects>> {
                     override fun onNext(rspBean: ArrayList<Objects>) {

                     }
                     override fun onComplete() {
//                         mView?.dismissLoading()
                     }

                     override fun onSubscribe(d: Disposable) {
//                         mModel?.addDisposable(d)
//                         mView?.showLoading("请求中...")
                         if (!NetworkUtils.isConnected()) {
                             showToastBottom("连接失败,请检查网络状况!")
                             onComplete()
                         }
                     }
                     override fun onError(e: Throwable) {
//                         mView?.dismissLoading()
                         if (e is SocketTimeoutException || e is ConnectException) {
                             showToastBottom("连接失败,请检查网络状况!")
                         } else if (e is JsonParseException) {
                             showToastBottom("数据解析失败")
                         } else {
                             showToastBottom("请求失败")
                         }
                     }
                 })
    }
}

