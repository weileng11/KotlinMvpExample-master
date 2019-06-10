//package com.exmple.baseprojectmvp.mvp.view
//
//import android.widget.ImageView
//
//import com.bumptech.glide.Glide
//import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
//import com.chad.library.adapter.base.BaseViewHolder
//import com.exmple.baseprojectmvp.R
//
///**
// * @author: ${bruce}
// * @project: KotlinMvpExample-master
// * @package: com.exmple.baseprojectmvp.mvp.view
// * @description:
// * @date: 2019/6/6
// * @time: 17:45
// */
//class c {
//
//    inner class HomeGloryMusicAdapter(data: List<HomeGloryMusicModel.PdBean>) : BaseMultiItemQuickAdapter<HomeGloryMusicModel.PdBean, BaseViewHolder>(data) {
//
//        init {
//            addItemType(HomeGloryServerModel.PdBean.LEFT, R.layout.home_glory_service_recycler_first_item)  //1
//            addItemType(HomeGloryServerModel.PdBean.RIGHT, R.layout.home_glory_service_recycler_last_item)//3
//        }
//
//        protected fun convert(helper: BaseViewHolder, item: HomeGloryMusicModel.PdBean) {
//            Glide.with(mContext).load(item.getMUSICIMAGE()).into(helper.getView(R.id.home_glory_service_recycler_item_img) as ImageView)
//            helper.setText(R.id.home_glory_service_recycler_item_tv, item.getMUSICTYPE())
//        }
//    }
//}
