package com.exmple.baseprojectmvp.mvp.adapter

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
 * @time:  10:35
 */
class FollowAdapter(dataList: ArrayList<HomeBean.Issue.Item>): BaseQuickAdapter<HomeBean.Issue.Item, BaseViewHolder>(R.layout.item_follow,dataList) {
    override fun convert(holder: BaseViewHolder?, data: HomeBean.Issue.Item?) {
        when {
            data?.type == "videoCollectionWithBrief" -> setAuthorInfo(data, holder!!)
        }
    }


    /**
     * 加载作者信息
     */
    private fun setAuthorInfo(item: HomeBean.Issue.Item, holder: BaseViewHolder) {
        val headerData = item.data?.header
//        /**
//         * 加载作者头像
//         */
//        holder.setImagePath(R.id.iv_avatar, object : ViewHolder.HolderImageLoader(headerData?.icon!!) {
//            override fun loadImage(iv: ImageView, path: String) {
//                GlideApp.with(mContext)
//                        .load(path)
//                        .placeholder(R.mipmap.default_avatar).circleCrop()
//                        .transition(DrawableTransitionOptions().crossFade())
//                        .into(holder.getView(R.id.iv_avatar))
//            }
//
//        })

        // 加载封页图
        Glide.with(mContext)
                .load(headerData?.icon)
//                .placeholder(R.drawable.placeholder_banner)
                .transition(DrawableTransitionOptions().crossFade())
                .into(holder!!.getView(R.id.iv_avatar))

        holder.setText(R.id.tv_title, headerData?.title)
        holder.setText(R.id.tv_desc, headerData?.description)

        val recyclerView = holder.getView<RecyclerView>(R.id.fl_recyclerView)
        /**
         * 设置嵌套水平的 RecyclerView
         */
        recyclerView.layoutManager = LinearLayoutManager(mContext as Activity, LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = FollowHorizontalAdapter(categoryList= item.data?.itemList!!)

    }
}