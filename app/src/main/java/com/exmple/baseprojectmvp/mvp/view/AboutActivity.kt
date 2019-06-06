package com.exmple.baseprojectmvp.mvp.view

import android.content.Intent
import android.net.Uri
import com.exmple.baseprojectmvp.R
import com.exmple.corelib.base.BaseActivity
import com.exmple.corelib.base.LibApplication
import com.exmple.corelib.utils.AppUtils
import com.exmple.corelib.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_about.*

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.view
 * @description:关于
 * @date: 2019/6/6
 * @time:  15:30
 */
class AboutActivity : BaseActivity() {

    override fun getContentView(): Int {
        return R.layout.activity_about
    }

    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, toolbar)

        tv_version_name.text ="v${AppUtils.getVerName(LibApplication.mContext)}"
        //返回
        toolbar.setNavigationOnClickListener { finish() }
        //访问 GitHub
        relayout_gitHub.setOnClickListener {
            val uri = Uri.parse("https://github.com/git-xuhao/KotlinMvp")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

}