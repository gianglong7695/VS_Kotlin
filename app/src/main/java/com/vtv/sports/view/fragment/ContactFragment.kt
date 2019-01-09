package com.vtv.sports.view.fragment


import android.databinding.ViewDataBinding
import android.os.Bundle
import com.vtv.sports.R
import com.vtv.sports.databinding.FragmentContactBinding
import com.vtv.sports.util.Constant
import com.vtv.sports.view.activity.MainActivity
import kotlinx.android.synthetic.main.layout_toolbar.view.*

/**
 * Created by Giang Long on 12/20/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */

class ContactFragment : BaseFragment() {
    lateinit var binding: FragmentContactBinding
    private var title = ""
    private var zoneId: String = ""


    companion object {
        fun newInstance(zoneId: String, title: String): ContactFragment {
            val args = Bundle()
            args.putSerializable(Constant.KEY_ID, zoneId)
            args.putSerializable(Constant.KEY_TITLE, title)
            val fragment = ContactFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_contact
    }

    override fun initView(binding: ViewDataBinding?) {
        this.binding = binding as FragmentContactBinding

        binding.layoutToolbar.img_back.setOnClickListener {
            (binding.root.context as MainActivity).onBackPressed()
        }
    }

    override fun initData() {
        zoneId = arguments!!.getString(Constant.KEY_ID)
        title = arguments!!.getString(Constant.KEY_TITLE)
        binding.layoutToolbar.text_title.text = title
    }


}
