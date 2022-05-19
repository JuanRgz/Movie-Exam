package com.study.mymovies.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.study.mymovies.ui.fragment.registry.data.model.UserModel

abstract class BaseFragment<B: ViewDataBinding>: Fragment() {

    lateinit var bind: B

    abstract fun initElements()
    abstract fun initView()
    abstract fun getLayout(): Int
    abstract fun initObservers()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        bind= DataBindingUtil.inflate(inflater, getLayout(), container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initElements()
        initObservers()
        initView()
        super.onViewCreated(view, savedInstanceState)
    }

}