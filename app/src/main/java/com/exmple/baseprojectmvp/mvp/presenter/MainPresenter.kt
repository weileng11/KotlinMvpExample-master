package com.exmple.baseprojectmvp.mvp.presenter

import android.util.Log
import com.exmple.baseprojectmvp.http.MainDataBean
import com.exmple.baseprojectmvp.mvp.contract.IMainContact
import com.exmple.baseprojectmvp.mvp.model.MainModel
import com.exmple.corelib.http.retrofit.NetCallBack
import com.exmple.corelib.mSubscribe
import com.exmple.corelib.mvp.BasePresenterKt

/**
 * @FileName: MainPresenter.java
 * @author: villa_mou
 * @date: 06-16:35
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
class MainPresenter:BasePresenterKt<IMainContact.View>(), IMainContact.Presenter {
    override var mModel: IMainContact.Model? = MainModel()

    override fun getDataByNet() {
        mModel?.getMainData()?.mSubscribe(
                mView,
                mModel,
                "正在获取数据...",
                object : NetCallBack<MainDataBean> {
                    override fun backFail(errStr: String) {
                    }

                    override fun success(rspBean: MainDataBean) {
                           Log.i("info",rspBean.data.toString())
                    }

                    override fun fail(t: String?) {

                    }
                })
        }
}


