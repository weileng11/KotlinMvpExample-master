package com.exmple.baseprojectmvp.mvp.adapter

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.exmple.baseprojectmvp.R
import com.exmple.corelib.base.LibApplication
import com.hazz.kotlinmvp.mvp.model.bean.CategoryBean

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.adapter
 * @description:
 * @date: 2019/6/5
 * @time:  17:38
 */
class CategoryAdapter(categoryList: ArrayList<CategoryBean>): BaseQuickAdapter<CategoryBean, BaseViewHolder>(R.layout.item_category,categoryList) {

    private var textTypeface: Typeface?=null

    init {
        textTypeface = Typeface.createFromAsset(LibApplication.mContext.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
    }
//    /**
//     * 设置新数据
//     */
//    fun setData(categoryList: ArrayList<CategoryBean>){
//        mData.clear()
//        mData = categoryList
//        notifyDataSetChanged()
//    }

    override fun convert(holder: BaseViewHolder?, item: CategoryBean) {
        holder?.setText(R.id.tv_category_name, "#${item.name}")
        //设置方正兰亭细黑简体
        holder?.getView<TextView>(R.id.tv_category_name)?.typeface = textTypeface

//        holder?.setImageBitmap(R.id.iv_category, object : HolderImageLoader(item.bgPicture) {
//            override fun loadImage(iv: ImageView, path: String) {
//                Glide.with(mContext)
//                        .load(path)
////                        .placeholder(R.color.color_darker_gray)
//                        .transition(DrawableTransitionOptions().crossFade())
//                        .thumbnail(0.5f)
//                        .into(iv)
//            }
//        })

        // 加载网络图片
        Glide.with(mContext).load(item.bgPicture).into(holder!!.getView(R.id.iv_category))
    }

//        holder.setOnItemClickListener(object : View.OnClickListener{
//            override fun onClick(p0: View?) {
//                val intent = Intent(mContext as Activity,CategoryDetailActivity::class.java)
//                intent.putExtra(Constants.BUNDLE_CATEGORY_DATA,data)
//                mContext.startActivity(intent)
//            }
//        })
//    }
}