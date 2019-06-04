package com.exmple.baseprojectmvp.mvp.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.support.annotation.NonNull
import android.util.Log
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Toast
import com.exmple.baseprojectmvp.R
import com.exmple.corelib.base.BaseActivity
import com.exmple.corelib.base.LibApplication
import com.exmple.corelib.utils.AppUtils
import kotlinx.android.synthetic.main.activity_splash.*
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.view
 * @description: 启动页
 * @date: 2019/6/3
 * @time:  16:25
 */
class SplashActivity : BaseActivity(),EasyPermissions.PermissionCallbacks{

    private var textTypeface:Typeface?=null

    private var descTypeFace:Typeface?=null

    private var alphaAnimation:AlphaAnimation?=null

    init {
        textTypeface= Typeface.createFromAsset(LibApplication.mContext.assets,"fonts/Lobster-1.4.otf")
        descTypeFace=Typeface.createFromAsset(LibApplication.mContext.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
    }

    override fun getContentView(): Int {
       return R.layout.activity_splash
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {

        tv_app_name.typeface=textTypeface
        tv_splash_desc.typeface=descTypeFace
        tv_version_name.text="v${AppUtils.getVerName(LibApplication.mContext)}"

        //渐变展示启动屏
        //渐变展示启动屏
        alphaAnimation= AlphaAnimation(0.3f, 1.0f)
        alphaAnimation?.duration = 2000
        alphaAnimation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(arg0: Animation) {
                redirectTo()
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationStart(animation: Animation) {}

        })
        iv_web_icon.startAnimation(alphaAnimation)
        //检查权限
//        checkPermission()
    }

    private fun redirectTo() {
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * 6.0以下版本(系统自动申请) 不会弹框
     * 有些厂商修改了6.0系统申请机制，他们修改成系统自动申请权限了
     */
    private fun checkPermission(){
        val perms= arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        EasyPermissions.requestPermissions(this, "KotlinMvp应用需要以下权限，请允许", 0, *perms)
    }
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        if (requestCode == 0) {
            if (perms.isNotEmpty()) {
                if (perms.contains(Manifest.permission.READ_PHONE_STATE)
                        && perms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (alphaAnimation != null) {
                        iv_web_icon.startAnimation(alphaAnimation)
                    }
                }
            }
        }
    }

    /**
     * 重写要申请权限的Activity或者Fragment的onRequestPermissionsResult()方法，
     * 在里面调用EasyPermissions.onRequestPermissionsResult()，实现回调。
     *
     * @param requestCode  权限请求的识别码
     * @param permissions  申请的权限
     * @param grantResults 授权结果
     */
    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

//    /**
//     * 当权限被成功申请的时候执行回调
//     *
//     * @param requestCode 权限请求的识别码
//     * @param perms       申请的权限的名字
//     */
//    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
//        Log.i("EasyPermissions", "获取成功的权限$perms")
//    }

    /**
     * 当权限申请失败的时候执行的回调
     *
     * @param requestCode 权限请求的识别码
     * @param perms       申请的权限的名字
     */
    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        //处理权限名字字符串
        val sb = StringBuffer()
        for (str in perms) {
            sb.append(str)
            sb.append("\n")
        }
        sb.replace(sb.length - 2, sb.length, "")
        //用户点击拒绝并不在询问时候调用
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this, "已拒绝权限" + sb + "并不再询问", Toast.LENGTH_SHORT).show()
            AppSettingsDialog.Builder(this)
                    .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
                    .setPositiveButton("好")
                    .setNegativeButton("不行")
                    .build()
                    .show()
        }
    }

}