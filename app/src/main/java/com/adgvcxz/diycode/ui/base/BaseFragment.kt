package com.adgvcxz.diycode.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adgvcxz.diycode.BR
import com.adgvcxz.diycode.di.component.DaggerFragmentComponent
import com.adgvcxz.diycode.di.component.FragmentComponent
import com.adgvcxz.diycode.di.module.FragmentModule
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/11.
 */

abstract class BaseFragment<T : BaseFragmentViewModel, out B : ViewDataBinding> : Fragment() {

    val Data = "Data"

    @Inject
    lateinit var viewModel: T

    val dataBinding: B by lazy {
        DataBindingUtil.inflate<B>(LayoutInflater.from(activity), viewModel.contentId(), null, false)
    }

    val fragmentComponent: FragmentComponent by lazy {
        DaggerFragmentComponent.builder()
                .activityComponent((activity as BaseActivity<*, *>).activityComponent)
                .fragmentModule(FragmentModule(this))
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model = savedInstanceState?.getParcelable<T>(Data)
        if (model != null) {
            viewModel.restore(model)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inject()
        dataBinding.setVariable(BR.model, viewModel)
        return dataBinding.root
    }

    abstract fun inject()

}
