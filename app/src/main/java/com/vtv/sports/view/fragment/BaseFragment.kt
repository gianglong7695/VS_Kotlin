package com.vtv.sports.view.fragment

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vtv.sports.util.Constant
import com.vtv.sports.util.Logs
import com.vtv.sports.util.NetworkUtil

/**
 * Created by Giang Long on 12/21/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
open abstract class BaseFragment : Fragment() {
    abstract fun getLayoutRes(): Int
    abstract fun initView(binding: ViewDataBinding?)
    abstract fun initData()

    private lateinit var binding: ViewDataBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(binding)
        // Check network first
        if (NetworkUtil.isNetworkAvailable(view.context))
            initData()
        else
            Logs.e(Constant.NO_CONNECT)
    }
}