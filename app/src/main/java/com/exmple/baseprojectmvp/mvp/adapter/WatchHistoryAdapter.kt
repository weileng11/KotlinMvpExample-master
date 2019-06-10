package com.exmple.baseprojectmvp.mvp.adapter

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
import android.support.v4.util.Pair
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.durationFormat
import com.exmple.baseprojectmvp.mvp.view.VideoDetailActivity
import com.exmple.baseprojectmvp.widget.Constants
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean

/**
 * Created by xuhao on 2017/12/11.
 * desc:
 */
class WatchHistoryAdapter(dataList: ArrayList<HomeBean.Issue.Item>):
        BaseQuickAdapter<HomeBean.Issue.Item, BaseViewHolder>(R.layout.item_video_small_card,dataList) {

    override fun convert(holder: BaseViewHolder?, data: HomeBean.Issue.Item?) {
        with(holder) {
            holder?.setText(R.id.tv_title, data?.data?.title!!)
            holder?.setText(R.id.tv_tag, "#${data?.data?.category} / ${durationFormat(data?.data?.duration)}")

            Glide.with(mContext)
                    .load(data?.data?.cover?.detail)
//                .placeholder(R.drawable.placeholder_banner)
                    .transition(DrawableTransitionOptions().crossFade())
                    .into(holder!!.getView(R.id.iv_video_small_card))

//            setImagePath(R.id.iv_video_small_card, object : ViewHolder.HolderImageLoader(data?.data?.cover.detail) {
//                override fun loadImage(iv: ImageView, path: String) {
//                    GlideApp.with(mContext)
//                            .load(path)
//                            .placeholder(R.drawable.placeholder_banner)
//                            .transition(com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions().crossFade())
//                            .into(iv)
//                }
//            })
        }
        holder?.getView<TextView>(R.id.tv_title)?.setTextColor(ContextCompat.getColor(mContext,R.color.color_black))
//        holder.setOnItemClickListener(listener = View.OnClickListener {
//            goToVideoPlayer(mContext as Activity, holder.getView(R.id.iv_video_small_card), data)
//        })

        holder?.getView<RelativeLayout>(R.id.rl_video)?.setOnClickListener {
            if (data != null) {
                goToVideoPlayer(mContext as Activity, holder.getView(R.id.iv_video_small_card), data)
            }
        }
    }

    /**
     * 跳转到视频详情页面播放
     *
     * @param activity
     * @param view
     */
    private fun goToVideoPlayer(activity: Activity, view: View, itemData: HomeBean.Issue.Item) {
        val intent = Intent(activity, VideoDetailActivity::class.java)
        intent.putExtra(Constants.BUNDLE_VIDEO_DATA, itemData)
        intent.putExtra(VideoDetailActivity.TRANSITION, true)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val pair = Pair<View, String>(view, VideoDetailActivity.IMG_TRANSITION)
            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, pair)
            ActivityCompat.startActivity(activity, intent, activityOptions.toBundle())
        } else {
            activity.startActivity(intent)
            activity.overridePendingTransition(R.anim.anim_in, R.anim.anim_out)
        }
    }
}