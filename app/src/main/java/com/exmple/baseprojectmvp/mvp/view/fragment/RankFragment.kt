package com.exmple.baseprojectmvp.mvp.view.fragment

import android.os.Bundle
import android.view.View
import com.exmple.baseprojectmvp.R
import com.exmple.baseprojectmvp.mvp.contract.fragment.IHomeContract
import com.exmple.baseprojectmvp.mvp.presenter.fragment.HomePresenter
import com.exmple.corelib.mvp.BaseMvpFragment

/**
 * @author: ${bruce}
 * @project: KotlinMvpExample-master
 * @package: com.exmple.baseprojectmvp.mvp.view.fragment
 * @description:
 * @date: 2019/6/4
 * @time:  11:55
 */
class RankFragment : BaseMvpFragment<IHomeContract.View, IHomeContract.Presenter>(), IHomeContract.View{

    override var mPresenter: IHomeContract.Presenter = HomePresenter()

    companion object {
        fun getInstance(title: String): RankFragment {
            val fragment = RankFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
//            fragment.mTitle = title
            return fragment
        }
    }

    override fun lazyLoad() {
    }

    override fun getContentView(): Int {
        return R.layout.fragment_rank
    }

    override fun initView(view: View) {
    }

}