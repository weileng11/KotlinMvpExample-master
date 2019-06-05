package com.exmple.baseprojectmvp.mvp.presenter.fragment

import android.util.Log
import com.exmple.baseprojectmvp.mvp.contract.fragment.IHomeContract
import com.exmple.baseprojectmvp.mvp.model.fragment.HomeModel
import com.exmple.corelib.http.retrofit.NetCallBack
import com.exmple.corelib.mSubscribe
import com.exmple.corelib.mvp.BasePresenterKt
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.presenter.fragment
 * @description:
 * @date: 2019/6/4
 * @time:  11:44
 */
class HomePresenter: BasePresenterKt<IHomeContract.View>(),IHomeContract.Presenter{

    override var mModel: IHomeContract.Model? = HomeModel()

    private var bannerHomeBean: HomeBean? = null
    private var nextPageUrl:String?=null     //加载首页的Banner 数据+一页数据合并后，nextPageUrl没 add

    override fun requestHomeData(num: Int) {
        mModel?.requestHomeData(num)?.mSubscribe (mView,mModel,"正在获取数据中...",object : NetCallBack<HomeBean> {
            override fun success(rspBean: HomeBean) {
                Log.i("info",rspBean.issueList[0].itemList.toString())

                bannerHomeBean = rspBean //记录第一页是当做 banner 数据

                nextPageUrl = rspBean.nextPageUrl

                mView?.setHomeData(bannerHomeBean!!)
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

    override fun loadMoreData() {
        nextPageUrl?.let {
            mModel?.loadMoreData(it)?.mSubscribe (mView,mModel,"正在获取数据中...",object : NetCallBack<HomeBean> {
            override fun success(rspBean: HomeBean) {
                Log.i("info","加载更多数据"+rspBean.issueList[0].itemList.toString())
                //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
                val newItemList = rspBean.issueList[0].itemList

                newItemList.filter { item ->
                    item.type=="banner2"||item.type=="horizontalScrollCard"
                }.forEach{ item ->
                    //移除 item
                    newItemList.remove(item)
                }

                nextPageUrl = rspBean.nextPageUrl
                mView?.setMoreData(newItemList)
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

//    class MainPresenter:BasePresenterKt<IMainContact.View>(), IMainContact.Presenter {
//        override var mModel: IMainContact.Model? = MainModel()
//        override fun getDataByNet() {
//            mModel?.getMainData()?.mSubscribe(mView,mModel,"正在获取数据中...") {
//                mView?.getDataSuccess()
//            }
//        }
//    }


//    override fun loadMoreData(url:String) {
//        mModel?.loadMoreData(url)?.mSubscribe(mView,mModel,"正在获取数据中...") {
//
//            val newItemList = homeBean.issueList[0].itemList
//
//            newItemList.filter { item ->
//                item.type=="banner2"||item.type=="horizontalScrollCard"
//            }.forEach{ item ->
//                //移除 item
//                newItemList.remove(item)
//            }
//
//            nextPageUrl = homeBean.nextPageUrl
//            setMoreData(newItemList)
//        }
//    }




}