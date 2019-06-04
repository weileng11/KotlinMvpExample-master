package com.exmple.baseprojectmvp.mvp.view.fragment

import android.os.Bundle
import android.view.View
import com.exmple.baseprojectmvp.R
import com.exmple.corelib.base.BaseFragment

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.view.fragment
 * @description: 我的
 * @date: 2019/6/4
 * @time:  11:55
 */
class MineFragment : BaseFragment() {

    companion object {
        fun getInstance(title: String): MineFragment {
            val fragment = MineFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
//            fragment.mTitle = title
            return fragment
        }
    }

    override fun lazyLoad() {
    }

    override fun getContentView(): Int {
        return R.layout.abc_search_view
    }

    override fun initView(view: View) {
    }


}