package com.vtv.sports.view.fragment


import android.databinding.ViewDataBinding
import android.os.Bundle
import com.vtv.sports.R
import com.vtv.sports.databinding.FragmentMoreVideoBinding
import com.vtv.sports.model.video.VideoRespone
import com.vtv.sports.repository.ApiConstant
import com.vtv.sports.repository.BaseService
import com.vtv.sports.util.Constant
import com.vtv.sports.util.Logs
import com.vtv.sports.view.activity.MainActivity
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Giang Long on 12/20/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */

class MoreVideoFragment : BaseFragment() {
    private var zoneId: String = ""
    private var pageIndex: Int = 1
    private var title = ""
    private lateinit var binding: FragmentMoreVideoBinding

    companion object {
        fun newInstance(zoneId: String, title: String): MoreVideoFragment {
            val args = Bundle()
            args.putSerializable(Constant.KEY_ID, zoneId)
            args.putSerializable(Constant.KEY_TITLE, title)
            val fragment = MoreVideoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_more_video
    }

    override fun initView(binding: ViewDataBinding?) {
        this.binding = binding as FragmentMoreVideoBinding

        binding.layoutToolbar.img_back.setOnClickListener {
            (binding.root.context as MainActivity).onBackPressed()
        }
    }

    override fun initData() {
        zoneId = arguments!!.getString(Constant.KEY_ID)
        title = arguments!!.getString(Constant.KEY_TITLE)
        binding.layoutToolbar.text_title.text = title
        fetchData()
    }

    private fun fetchData() {
        val call = BaseService.getService().getVideoPaging(ApiConstant.SECRET_KEY, zoneId, pageIndex.toString(), "20")
        call.enqueue(object : Callback<VideoRespone> {
            override fun onResponse(call: Call<VideoRespone>, response: Response<VideoRespone>) {
                if (response.isSuccessful) {
                    val video = response.body()!!

                }
            }

            override fun onFailure(call: Call<VideoRespone>, t: Throwable) {
                Logs.e(t.toString())
            }
        })
    }


}
