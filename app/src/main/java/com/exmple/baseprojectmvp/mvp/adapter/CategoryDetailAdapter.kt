package com.exmple.baseprojectmvp.mvp.adapter

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.durationFormat
import com.exmple.baseprojectmvp.mvp.view.VideoDetailActivity
import com.exmple.baseprojectmvp.widget.Constants
import com.exmple.corelib.base.LibApplication.Companion.mContext
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.adapter
 * @description:
 * @date: 2019/6/6
 * @time:  12:00
 */
class CategoryDetailAdapter(categoryList: ArrayList<HomeBean.Issue.Item>):
        BaseQuickAdapter<HomeBean.Issue.Item, BaseViewHolder>(R.layout.item_category_detail,categoryList) {
    override fun convert(holder: BaseViewHolder?, item: HomeBean.Issue.Item?) {
        setVideoItem(holder!!, item!!)
    }


    /**
     * 加载 content item
     */
    private fun setVideoItem(holder: BaseViewHolder, item: HomeBean.Issue.Item) {
        val itemData = item.data
        val cover = itemData?.cover?.feed ?: ""

        // 加载封页图
        Glide.with(mContext)
                .load(cover)
//                .placeholder(R.drawable.placeholder_banner)
                .transition(DrawableTransitionOptions().crossFade())
                .into(holder!!.getView(R.id.iv_image))

        holder.setText(R.id.tv_title, itemData?.title ?: "")

        // 格式化时间
        val timeFormat = durationFormat(itemData?.duration)

        holder.setText(R.id.tv_tag, "#${itemData?.category}/$timeFormat")

        holder?.getView<RelativeLayout>(R.id.rl_video).setOnClickListener {
            goToVideoPlayer(mContext as Activity, holder.getView(R.id.iv_image), item) }
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
        intent.putExtra(VideoDetailActivity.Companion.TRANSITION, true)
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

