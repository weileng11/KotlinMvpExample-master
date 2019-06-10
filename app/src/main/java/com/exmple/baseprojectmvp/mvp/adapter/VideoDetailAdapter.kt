package com.exmple.baseprojectmvp.mvp.adapter

import android.graphics.Typeface
import android.os.Parcel
import android.os.Parcelable
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.durationFormat
import com.exmple.corelib.base.LibApplication
import com.exmple.corelib.base.LibApplication.Companion.mContext
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.adapter
 * @description:data: List<HomeGloryMusicModel.PdBean>) :
 * BaseMultiItemQuickAdapter<HomeGloryMusicModel.PdBean, BaseViewHolder>(data)
 * @date: 2019/6/6
 * @time:  16:24
 */
class VideoDetailAdapter(dataList: List<HomeBean.Issue.Item>):
        BaseMultiItemQuickAdapter<HomeBean.Issue.Item,BaseViewHolder>(dataList) {

    var list : List<HomeBean.Issue.Item> = dataList
    private var textTypeface: Typeface?=null
    var type :Int = 0

    init {
        textTypeface = Typeface.createFromAsset(LibApplication.mContext.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")

    }

//    fun VideoDetailAdapter(){
//        addItemType(1, R.layout.item_video_detail_info);  //1
//        addItemType(2, R.layout.item_video_text_card);//3
//    }

    override fun getDefItemViewType(position: Int): Int {
        when {
            position==0 -> addItemType(0, R.layout.item_video_detail_info)  //1
            list[position].type == "textCard" ->  addItemType(1, R.layout.item_video_text_card)  //1
            list[position].type == "videoSmallCard" -> addItemType(2, R.layout.item_video_small_card)//3
            else -> throw IllegalAccessException("Api 解析出错了，出现其他类型")
        }

//            position == 0 ->
//                R.layout.item_video_detail_info
//
//            list[position].type == "textCard" ->
////                R.layout.item_video_text_card
//                addItemType(1, R.layout.item_video_text_card);  //1
//
//
//            list[position].type == "videoSmallCard" ->
////                R.layout.item_video_small_card
//            addItemType(2, R.layout.item_video_small_card);//3
        return when {
            position==0 -> 0
            list[position].type.equals("textCard") -> 1
            list[position].type.equals("videoSmallCard") -> 2
            else -> 0
        }
    }

    /**
     * Kotlin的函数可以作为参数，写callback的时候，可以不用interface了
     */

    private var mOnItemClickRelatedVideo: ((item: HomeBean.Issue.Item) -> Unit)? = null


    fun setOnItemDetailClick(mItemRelatedVideo: (item: HomeBean.Issue.Item) -> Unit) {
        this.mOnItemClickRelatedVideo = mItemRelatedVideo
    }


    override fun convert(holder: BaseViewHolder?, data: HomeBean.Issue.Item?) {

        when {
            holder?.itemViewType==0 ->setVideoDetailInfo(data!!, holder)
//                holder?.adapterPosition == 0 -> setVideoDetailInfo(data!!, holder)

            holder?.itemViewType==1-> {
//            data?.type == "textCard" -> {
                holder?.setText(R.id.tv_text_card, data?.data?.text!!)
//                holder?.setText(R.id.tv_text_card, "我们都是好孩子")
//                //设置方正兰亭细黑简体
                holder?.getView<TextView>(R.id.tv_text_card)?.typeface =textTypeface

            }
            holder?.itemViewType==2-> {
//                holder?.setText(R.id.tv_title, "我们都是好孩子")
//                holder?.setText(R.id.tv_tag, "都过去了")

//            data?.type == "videoSmallCard" -> {
                with(holder) {
//                    holder?.setText(R.id.tv_title, "我们都是好孩子")
//                    holder?.setText(R.id.tv_tag, "都过去了")

                    holder?.setText(R.id.tv_title, data?.data?.title!!)
                    holder?.setText(R.id.tv_tag, "#${data?.data?.category} / ${durationFormat(data?.data?.duration)}")
//                    setImagePath(R.id.iv_video_small_card, object : ViewHolder.HolderImageLoader(data.data.cover.detail) {
//                        override fun loadImage(iv: ImageView, path: String) {
//                            GlideApp.with(mContext)
//                                    .load(path)
//                                    .optionalTransform(GlideRoundTransform())
//                                    .placeholder(R.drawable.placeholder_banner)
//                                    .into(iv)
//                        }
//                    })

                    Glide.with(mContext)
                            .load(data?.data?.cover?.detail)
//                .placeholder(R.drawable.placeholder_banner)
                            .transition(DrawableTransitionOptions().crossFade())
                            .into(holder!!.getView(R.id.iv_video_small_card))
                }
                // 判断onItemClickRelatedVideo 并使用
                holder?.itemView?.setOnClickListener {
                    if (data != null) {
                        mOnItemClickRelatedVideo?.invoke(data)
                    }
                }

            }
            else -> throw IllegalAccessException("Api 解析出错了，出现其他类型")
        }
    }

    /**
     * 设置视频详情数据
     */
    private fun setVideoDetailInfo(data: HomeBean.Issue.Item, holder: BaseViewHolder) {
//        holder?.setText(R.id.tv_title, "我们都是好孩子")

        data.data?.title?.let { holder.setText(R.id.tv_title, it) }
        //视频简介
        data.data?.description?.let { holder.setText(R.id.expandable_text, it) }
        //标签
        holder.setText(R.id.tv_tag, "#${data.data?.category} / ${durationFormat(data.data?.duration)}")
        //喜欢
        holder.setText(R.id.tv_action_favorites, data.data?.consumption?.collectionCount.toString())
        //分享
        holder.setText(R.id.tv_action_share, data.data?.consumption?.shareCount.toString())
        //评论
        holder.setText(R.id.tv_action_reply, data.data?.consumption?.replyCount.toString())

        if (data.data?.author != null) {
            with(holder) {
                setText(R.id.tv_author_name, data.data.author.name)
                setText(R.id.tv_author_desc, data.data.author.description)
//                setImagePath(R.id.iv_avatar, object : ViewHolder.HolderImageLoader(data.data.author.icon) {
//                    override fun loadImage(iv: ImageView, path: String) {
//                        //加载头像
//                        GlideApp.with(mContext)
//                                .load(path)
//                                .placeholder(R.mipmap.default_avatar).circleCrop()
//                                .into(iv)
//                    }
//                })

                Glide.with(mContext)
                        .load(data.data.author.icon)
//                .placeholder(R.drawable.placeholder_banner)
                        .transition(DrawableTransitionOptions().crossFade())
                        .into(holder!!.getView(R.id.iv_avatar))
            }
        } else {
            holder.setGone(R.id.layout_author_view, false)
        }

        with(holder) {
            getView<TextView>(R.id.tv_action_favorites).setOnClickListener {
                android.widget.Toast.makeText(LibApplication.mContext, "喜欢", android.widget.Toast.LENGTH_SHORT).show()
            }
            getView<TextView>(R.id.tv_action_share).setOnClickListener {
                android.widget.Toast.makeText(LibApplication.mContext, "分享", android.widget.Toast.LENGTH_SHORT).show()
            }
            getView<TextView>(R.id.tv_action_reply).setOnClickListener {
                android.widget.Toast.makeText(LibApplication.mContext, "评论", android.widget.Toast.LENGTH_SHORT).show()
            }
        }
    }

}