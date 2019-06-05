package com.exmple.baseprojectmvp.mvp.presenter.fragment

import android.util.Log
import com.exmple.baseprojectmvp.mvp.contract.fragment.ICategoryContract
import com.exmple.baseprojectmvp.mvp.contract.fragment.IHomeContract
import com.exmple.baseprojectmvp.mvp.model.fragment.CategoryModel
import com.exmple.baseprojectmvp.mvp.model.fragment.HomeModel
import com.exmple.corelib.http.constant.CodeStatus
import com.exmple.corelib.http.retrofit.NetCallBack
import com.exmple.corelib.listSubcribe
import com.exmple.corelib.mSubscribe
import com.exmple.corelib.mvp.BasePresenterKt
import com.exmple.corelib.scheduler.SchedulerUtils
import com.exmple.corelib.showToastBottom
import com.exmple.corelib.utils.NetworkUtils
import com.google.gson.JsonParseException
import com.hazz.kotlinmvp.mvp.model.bean.CategoryBean
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.presenter.fragment
 * @description:
 * @date: 2019/6/5
 * @time:  14:46
 */
class CategoryPresenter : BasePresenterKt<ICategoryContract.View>(), ICategoryContract.Presenter{
    override var mModel: ICategoryContract.Model? = CategoryModel()

    override fun getCategoryData() {
         mModel?.getCategoryData()?.compose(SchedulerUtils.ioToMain())!!
                .subscribe(object : Observer<ArrayList<CategoryBean>> {
                    override fun onNext(rspBean: ArrayList<CategoryBean>) {
                        Log.i("info",rspBean.toString())
                        for (i in rspBean){
                            Log.i("info","名称===="+i.name)
                        }

                        mView?.showCategory(rspBean)
                    }
                    override fun onComplete() {
                        mView?.dismissLoading()
                    }

                    override fun onSubscribe(d: Disposable) {
                        mModel?.addDisposable(d)
                        mView?.showLoading("请求中...")
                        if (!NetworkUtils.isConnected()) {
                            showToastBottom("连接失败,请检查网络状况!")
                            onComplete()
                        }
                    }
                    override fun onError(e: Throwable) {
                        mView?.dismissLoading()
                        if (e is SocketTimeoutException || e is ConnectException) {
                            showToastBottom("连接失败,请检查网络状况!")
                        } else if (e is JsonParseException) {
                            showToastBottom("数据解析失败")
                        } else {
                            showToastBottom("请求失败")
                        }
                    }
                })

//        mModel?.getCategoryData()?.mArrayListSubscribe (mView,mModel,"正在获取数据中...",object : NetCallBack<CategoryBean> {
//            override fun success(rspBean: CategoryBean) {
//                Log.i("info",rspBean.toString())
////                   mView?.showCategory(rspBean)
//            }
//
//            override fun backFail(errStr: String) {
//                mView?.showError(errStr)
//            }
//
//            override fun fail(t: String?) {
//                if (t != null) {
//                    mView?.showError(t)
//                }
//            }
//        })
    }


}