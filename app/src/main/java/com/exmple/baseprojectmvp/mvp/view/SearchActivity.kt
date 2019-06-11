package com.exmple.baseprojectmvp.mvp.view

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.KeyEvent
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.mvp.adapter.CategoryDetailAdapter
import com.exmple.baseprojectmvp.mvp.adapter.HotKeywordsAdapter
import com.exmple.baseprojectmvp.mvp.contract.ISearchContract
import com.exmple.baseprojectmvp.mvp.contract.fragment.IFollowContract
import com.exmple.baseprojectmvp.mvp.contract.fragment.IHomeContract
import com.exmple.baseprojectmvp.mvp.presenter.SearchPresenter
import com.exmple.baseprojectmvp.mvp.presenter.fragment.FollowPresenter
import com.exmple.baseprojectmvp.widget.MultipleStatusView
import com.exmple.baseprojectmvp.widget.ViewAnimUtils
import com.exmple.corelib.base.LibApplication
import com.exmple.corelib.mvp.BaseMvpActivity
import com.exmple.corelib.utils.StatusBarUtil
import com.google.android.flexbox.*
import com.hazz.kotlinmvp.mvp.model.bean.HomeBean
import kotlinx.android.synthetic.main.activity_search.*

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.view
 * @description:搜索
 * @date: 2019/6/10
 * @time:  11:10
 */
class SearchActivity : BaseMvpActivity<ISearchContract.View, ISearchContract.Presenter>(),ISearchContract.View{

    override var mPresenter: ISearchContract.Presenter = SearchPresenter()

    //设置第一次请求的数据
    private var mAdapter : CategoryDetailAdapter? = null
    private  var list= ArrayList<HomeBean.Issue.Item>()


    private var mHotKeywordsAdapter : HotKeywordsAdapter? = null
    private  var listStr= ArrayList<String>()

    private var mTextTypeface: Typeface? = null

    /**
     * 是否加载更多
     */
    private var loadingMore = false

    private var keyWords: String? = null
    protected var mLayoutStatusView: MultipleStatusView? = null

    init {
        mPresenter.attachView(this)
        //细黑简体字体
        mTextTypeface = Typeface.createFromAsset(LibApplication.mContext.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
    }

    override fun getContentView(): Int {
        return R.layout.activity_search
    }

    override fun initView() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            setUpEnterAnimation() // 入场动画
//            setUpExitAnimation() // 退场动画
//        } else {
//            setUpView()
//        }

        setUpView()

        tv_title_tip.typeface = mTextTypeface
        tv_hot_search_words.typeface = mTextTypeface
        //初始化查询结果的 RecyclerView
        mRecyclerView_result.layoutManager = LinearLayoutManager(this)
        //设置到适配
        mAdapter= CategoryDetailAdapter(categoryList =list)
        mRecyclerView_result.adapter = mAdapter

        //实现自动加载
        mRecyclerView_result.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val itemCount = mRecyclerView_result.layoutManager.itemCount
                val lastVisibleItem = (mRecyclerView_result.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (!loadingMore && lastVisibleItem == (itemCount - 1)) {
                    loadingMore = true
                    mPresenter.loadMoreData()
                }
            }
        })

        //取消
        tv_cancel.setOnClickListener { onBackPressed() }
        //键盘的搜索按钮
        et_search_view.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    closeSoftKeyboard()
                    keyWords = et_search_view.text.toString().trim()
                    if (keyWords.isNullOrEmpty()) {
                        showToast("请输入你感兴趣的关键词")
                    } else {
                        mPresenter.querySearchData(keyWords!!)
                    }
                }
                return false
            }

        })

        mLayoutStatusView = multipleStatusView

        //状态栏透明和间距处理
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, toolbar)

        //请求热门关键词
        mPresenter.requestHotWordData()
    }

    /**
     * 退场动画
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setUpExitAnimation() {
        val fade = Fade()
        window.returnTransition = fade
        fade.duration = 300
    }

    /**
     * 进场动画
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setUpEnterAnimation() {
        val transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.arc_motion)
        window.sharedElementEnterTransition = transition
        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {

            }

            override fun onTransitionEnd(transition: Transition) {
                transition.removeListener(this)
                animateRevealShow()
            }

            override fun onTransitionCancel(transition: Transition) {

            }

            override fun onTransitionPause(transition: Transition) {

            }

            override fun onTransitionResume(transition: Transition) {

            }
        })
    }

    /**
     * 展示动画
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun animateRevealShow() {
        ViewAnimUtils.animateRevealShow(
                this, rel_frame,
                fab_circle.width / 2, R.color.backgroundColor,
                object : ViewAnimUtils.OnRevealAnimationListener {
                    override fun onRevealHide() {

                    }

                    override fun onRevealShow() {
                        setUpView()
                    }
                })
    }

    private fun setUpView() {
        val animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        animation.duration = 300
        rel_container.startAnimation(animation)
        rel_container.visibility = View.VISIBLE
        //打开软键盘
        openKeyBord(et_search_view, applicationContext)
    }

    /**
     * 打卡软键盘
     */
    fun openKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    /**
     * 关闭软键盘
     */
    fun closeKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
    }

    override fun setHotWordData(string: ArrayList<String>) {
        showHotWordView()
        listStr.addAll(string)
        mHotKeywordsAdapter = HotKeywordsAdapter(list=listStr)

        val flexBoxLayoutManager = FlexboxLayoutManager(this)
        flexBoxLayoutManager.flexWrap = FlexWrap.WRAP      //按正常方向换行
        flexBoxLayoutManager.flexDirection = FlexDirection.ROW   //主轴为水平方向，起点在左端
        flexBoxLayoutManager.alignItems = AlignItems.CENTER    //定义项目在副轴轴上如何对齐
        flexBoxLayoutManager.justifyContent = JustifyContent.FLEX_START  //多个轴对齐方式

        mRecyclerView_hot.layoutManager = flexBoxLayoutManager
        mRecyclerView_hot.adapter = mHotKeywordsAdapter
        //设置 Tag 的点击事件
        mHotKeywordsAdapter?.setOnTagItemClickListener {
            closeSoftKeyboard()
            keyWords = it
            mPresenter.querySearchData(it)
        }
    }

    override fun setSearchResult(issue: HomeBean.Issue) {
        loadingMore = false

        hideHotWordView()
        tv_search_count.visibility = View.VISIBLE
        tv_search_count.text = String.format(resources.getString(R.string.search_result_count), keyWords, issue.total)

        list.addAll(issue.itemList)
        mAdapter?.addData(list)
    }

    override fun closeSoftKeyboard() {
        closeKeyBord(et_search_view, applicationContext)
    }

    override fun setEmptyView() {
        showToast("抱歉，没有找到相匹配的内容")
        hideHotWordView()
        tv_search_count.visibility = View.GONE
        mLayoutStatusView?.showEmpty()
    }

    override fun showError(errorMsg: String) {
        showToast(errorMsg)
//        if (errorMsg == 1004) {
//            mLayoutStatusView?.showNoNetwork()
//        } else {
//            mLayoutStatusView?.showError()
//        }
    }

    /**
     * 隐藏热门关键字的 View
     */
    private fun hideHotWordView(){
        layout_hot_words.visibility = View.GONE
        layout_content_result.visibility = View.VISIBLE
    }

    /**
     * 显示热门关键字的 流式布局
     */
    private fun showHotWordView(){
        layout_hot_words.visibility = View.VISIBLE
        layout_content_result.visibility = View.GONE
    }
}