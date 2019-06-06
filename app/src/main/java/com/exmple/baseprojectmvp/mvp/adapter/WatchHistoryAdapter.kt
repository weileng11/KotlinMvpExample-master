package com.exmple.baseprojectmvp.mvp.adapter

import android.app.Activity
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.durationFormat
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean

/**
 * Created by xuhao on 2017/12/11.
 * desc:
 */
class WatchHistoryAdapter(dataList: ArrayList<HomeBean.Issue.Item>): BaseQuickAdapter<HomeBean.Issue.Item, BaseViewHolder>(R.layout.item_video_small_card,dataList) {

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
    }
}