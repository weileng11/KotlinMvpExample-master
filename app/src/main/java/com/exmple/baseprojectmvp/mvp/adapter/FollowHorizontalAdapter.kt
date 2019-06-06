package com.exmple.baseprojectmvp.mvp.adapter

import android.app.Activity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.exmple.baseprojectmvp.R
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.adapter
 * @description:
 * @date: 2019/6/6
 * @time:  10:42
 */
class FollowHorizontalAdapter(categoryList: ArrayList<HomeBean.Issue.Item>): BaseQuickAdapter<HomeBean.Issue.Item, BaseViewHolder>(R.layout.item_follow_horizontal,categoryList) {
    override fun convert(holder: BaseViewHolder?, data: HomeBean.Issue.Item?) {
        val horizontalItemData = data?.data

        // 加载封页图
        Glide.with(mContext)
                .load(horizontalItemData?.cover?.feed!!)
//                .placeholder(R.drawable.placeholder_banner)
                .transition(DrawableTransitionOptions().crossFade())
                .into(holder!!.getView(R.id.iv_cover_feed))

        //横向 RecyclerView 封页图下面标题
        holder?.setText(R.id.tv_title, horizontalItemData?.title ?: "")

        // 格式化时间
        val timeFormat = durationFormat(horizontalItemData?.duration)
        //标签
        with(holder) {
            com.orhanobut.logger.Logger.d("horizontalItemData===title:${horizontalItemData?.title}tag:${horizontalItemData?.tags?.size}")

            if (horizontalItemData?.tags != null && horizontalItemData.tags.size > 0) {
                setText(R.id.tv_tag, "#${horizontalItemData.tags[0].name} / $timeFormat")
            }else{
                setText(R.id.tv_tag,"#$timeFormat")
            }

//            setOnItemClickListener(listener = android.view.View.OnClickListener {
//                goToVideoPlayer(mContext as Activity, holder.getView(R.id.iv_cover_feed), data)
//            })
        }
    }

    fun durationFormat(duration: Long?): String {
        val minute = duration!! / 60
        val second = duration % 60
        return if (minute <= 9) {
            if (second <= 9) {
                "0$minute' 0$second''"
            } else {
                "0$minute' $second''"
            }
        } else {
            if (second <= 9) {
                "$minute' 0$second''"
            } else {
                "$minute' $second''"
            }
        }
    }

}