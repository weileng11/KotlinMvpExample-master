package com.exmple.baseprojectmvp.mvp.presenter

import android.app.Activity
import com.exmple.baseprojectmvp.dataFormat
import com.exmple.baseprojectmvp.mvp.contract.IVideoDetailContract
import com.exmple.baseprojectmvp.mvp.contract.fragment.IFollowContract
import com.exmple.baseprojectmvp.mvp.model.VideoDetailModel
import com.exmple.baseprojectmvp.showToast
import com.exmple.corelib.base.LibApplication
import com.exmple.corelib.http.retrofit.NetCallBack
import com.exmple.corelib.mSubscribe
import com.exmple.corelib.mvp.BasePresenterKt
import com.exmple.corelib.utils.DisplayManager
import com.exmple.corelib.utils.NetworkUtil
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.presenter
 * @description:
 * @date: 2019/6/6
 * @time:  16:00
 */
class VideoDetailPresenter: BasePresenterKt<IVideoDetailContract.View>(), IVideoDetailContract.Presenter {
    override var mModel: IVideoDetailContract.Model? = VideoDetailModel()

    override fun loadVideoInfo(itemInfo: HomeBean.Issue.Item) {

        val playInfo = itemInfo.data?.playInfo
        val netType = NetworkUtil.isWifi(LibApplication.mContext)
        if (playInfo!!.size > 1) {
            // 当前网络是 Wifi环境下选择高清的视频
            if (netType) {
                for (i in playInfo) {
                    if (i.type == "high") {
                        val playUrl = i.url
                        mView?.setVideo(playUrl)
                        break
                    }
                }
            } else {
                //否则就选标清的视频
                for (i in playInfo) {
                    if (i.type == "normal") {
                        val playUrl = i.url
                        mView?.setVideo(playUrl)
                        //Todo 待完善
                        (mView as Activity).showToast("本次消耗${(mView as Activity)
                                .dataFormat(i.urlList[0].size)}流量")
                        break
                    }
                }
            }
        } else {
            mView?.setVideo(itemInfo.data.playUrl)
        }

        //设置背景
        val backgroundUrl = itemInfo.data.cover.blurred + "/thumbnail/${DisplayManager.getScreenHeight()!! - DisplayManager.dip2px(250f)!!}x${DisplayManager.getScreenWidth()}"
        backgroundUrl.let { mView?.setBackground(it) }

        mView?.setVideoInfo(itemInfo)
    }

    /**
     * 请求相关的视频数据
     */
    override fun requestRelatedVideo(id: Long) {
        mModel?.requestRelatedVideo(id)?.mSubscribe (mView,mModel,"",object : NetCallBack<HomeBean.Issue> {
            override fun success(rspBean: HomeBean.Issue) {
                mView?.dismissLoading()
                mView?.setRecentRelatedVideo(rspBean.itemList)
            }

            override fun backFail(errStr: String) {
                mView?.dismissLoading()
                mView?.showError(errStr)
            }

            override fun fail(t: String?) {
                if (t != null) {
                    mView?.dismissLoading()
                    mView?.showError(t)
                }
            }
        })
    }
}