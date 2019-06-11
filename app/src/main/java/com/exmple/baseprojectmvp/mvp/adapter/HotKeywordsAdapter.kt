package com.exmple.baseprojectmvp.mvp.adapter

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.exmple.baseprojectmvp.R
import com.google.android.flexbox.FlexboxLayoutManager
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.adapter
 * @description:
 * @date: 2019/6/10
 * @time:  11:54
 */
class HotKeywordsAdapter(list: ArrayList<String>):
        BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_flow_text,list) {

    /**
     * Kotlin的函数可以作为参数，写callback的时候，可以不用interface了
     */

    private var mOnTagItemClick: ((tag:String) -> Unit)? = null

    fun setOnTagItemClickListener(onTagItemClickListener:(tag:String) -> Unit) {
        this.mOnTagItemClick = onTagItemClickListener
    }


    override fun convert(holder: BaseViewHolder?, data: String?) {

        holder?.setText(R.id.tv_title,data)

        val params = holder?.getView<TextView>(R.id.tv_title)?.layoutParams
        if(params is FlexboxLayoutManager.LayoutParams){
            params.flexGrow = 1.0f
        }

        holder?.itemView?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                if (data != null) {
                    mOnTagItemClick?.invoke(data)
                }
            }

        }

        )
    }

}