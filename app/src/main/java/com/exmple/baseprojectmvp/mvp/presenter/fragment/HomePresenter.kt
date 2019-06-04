package com.exmple.baseprojectmvp.mvp.presenter.fragment

import com.exmple.baseprojectmvp.mvp.contract.fragment.IHomeContract
import com.exmple.baseprojectmvp.mvp.model.fragment.HomeModel
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
//        mModel?.requestHomeData(num)?.mSubscribe (mView,mModel,"正在获取数据中...",HomeBean){
//            //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
//        }
    }

    override fun loadMoreData() {
//        mModel?.loadMoreData(num)?.mSubscribe(mView, mModel, "正在获取数据中...") {
//            mView.loadMoreFail()
//        }
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