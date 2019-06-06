package com.exmple.baseprojectmvp.mvp.presenter.fragment

import com.exmple.baseprojectmvp.mvp.contract.fragment.IRankContract
import com.exmple.baseprojectmvp.mvp.model.fragment.RankModel
import com.exmple.corelib.http.retrofit.NetCallBack
import com.exmple.corelib.mSubscribe
import com.exmple.corelib.mvp.BasePresenterKt
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.presenter.fragment
 * @description:
 * @date: 2019/6/6
 * @time:  11:44
 */
class RankPresenter: BasePresenterKt<IRankContract.View>(), IRankContract.Presenter{

    override var mModel: IRankContract.Model? = RankModel()

    override fun requestRankList(apiUrl: String) {
        mModel?.requestRankList(apiUrl)?.mSubscribe (mView,mModel,"正在获取数据中...",object : NetCallBack<HomeBean.Issue> {
            override fun success(rspBean: HomeBean.Issue) {
                mView?.setRankList(rspBean.itemList)
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