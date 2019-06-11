package com.exmple.baseprojectmvp.mvp.view

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.mvp.adapter.CategoryDetailAdapter
import com.exmple.baseprojectmvp.mvp.contract.ICategoryDetailContract
import com.exmple.baseprojectmvp.mvp.presenter.CategoryDetailPresenter
import com.exmple.baseprojectmvp.widget.Constants
import com.exmple.corelib.mvp.BaseMvpActivity
import com.exmple.corelib.utils.StatusBarUtil
import com.hazz.kotlinmvp.mvp.model.bean.CategoryBean
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import kotlinx.android.synthetic.main.activity_category_detail.*

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.view
 * @description: 分类详情
 * @date: 2019/6/11
 * @time:  10:31
 */
class CategoryDetailActivity : BaseMvpActivity<ICategoryDetailContract.View, ICategoryDetailContract.Presenter>(), ICategoryDetailContract.View {

    override var mPresenter: ICategoryDetailContract.Presenter = CategoryDetailPresenter()

    private var categoryData: CategoryBean? = null

    //设置第一次请求的数据
    private var mAdapter : CategoryDetailAdapter? = null

    private  var list= ArrayList<HomeBean.Issue.Item>()

    /**
     * 是否加载更多
     */
    private var loadingMore = false

    override fun getContentView(): Int {
         return R.layout.activity_category_detail
    }

    override fun initView() {
        categoryData = intent.getSerializableExtra(Constants.BUNDLE_CATEGORY_DATA) as CategoryBean?

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        // 加载headerImage
        Glide.with(this)
                .load(categoryData?.headerImage)
//                .placeholder(R.color.color_darker_gray)
                .into(imageView)

        tv_category_desc.text ="#${categoryData?.description}#"

        collapsing_toolbar_layout.title = categoryData?.name
        collapsing_toolbar_layout.setExpandedTitleColor(Color.WHITE) //设置还没收缩时状态下字体颜色
        collapsing_toolbar_layout.setCollapsedTitleTextColor(Color.BLACK) //设置收缩后Toolbar上字体的颜色

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        //设置到适配
        mAdapter= CategoryDetailAdapter(categoryList =list)
        mRecyclerView.adapter = mAdapter
        //实现自动加载
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val itemCount = mRecyclerView.layoutManager.itemCount
                val lastVisibleItem = (mRecyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (!loadingMore && lastVisibleItem == (itemCount - 1)) {
                    loadingMore = true
                    mPresenter.loadMoreData()
                }
            }
        })

        //状态栏透明和间距处理
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, toolbar)

        //获取当前分类列表
        categoryData?.id?.let { mPresenter.getCategoryDetailList(it) }
    }

    override fun setCateDetailList(itemList: ArrayList<HomeBean.Issue.Item>) {
        loadingMore = false
        list.addAll(itemList)
        mAdapter?.addData(list)
    }

    override fun showError(errorMsg: String) {
        showToast(errorMsg)
    }
}