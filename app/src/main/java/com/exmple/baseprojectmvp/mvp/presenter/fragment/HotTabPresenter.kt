package com.exmple.baseprojectmvp.mvp.presenter.fragment

import android.util.Log
import com.exmple.baseprojectmvp.mvp.contract.fragment.IHotTabContract
import com.exmple.baseprojectmvp.mvp.model.fragment.HotTabModel
import com.exmple.corelib.http.retrofit.NetCallBack
import com.exmple.corelib.mSubscribe
import com.exmple.corelib.mvp.BasePresenterKt
import com.hazz.kotlinmvp.mvp.model.bean.TabInfoBean

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.presenter.fragment
 * @description:
 * @date: 2019/6/6
 * @time:  11:16
 */
class HotTabPresenter: BasePresenterKt<IHotTabContract.View>(), IHotTabContract.Presenter{

    override var mModel: IHotTabContract.Model? = HotTabModel()

    override fun getTabInfo() {

        mModel?.getTabInfo()?.mSubscribe (mView,mModel,"正在获取数据中...",object : NetCallBack<TabInfoBean> {
            override fun success(rspBean: TabInfoBean) {
                mView?.setTabInfo(rspBean)
            }

            override fun backFail(errStr: String) {
                mView?.showError(errStr)
            }

            override fun fail(t: String?) {
                if (t != null) {
                    mView?.showError(t)
                }
            }
        })
    }

}