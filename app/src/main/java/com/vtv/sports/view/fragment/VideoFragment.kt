package com.vtv.sports.view.fragment


import android.databinding.ViewDataBinding
import com.vtv.sports.R
import com.vtv.sports.databinding.FragmentNewsBinding
import com.vtv.sports.databinding.FragmentVideoBinding
import com.vtv.sports.model.video.VideoGroup
import com.vtv.sports.model.video.VideoRespone
import com.vtv.sports.repository.ApiConstant
import com.vtv.sports.repository.BaseService
import com.vtv.sports.util.Logs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Giang Long on 12/20/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des: Tab1
 */

class VideoFragment : BaseFragment() {
    lateinit var binding: FragmentVideoBinding
    var listGroup: List<VideoGroup> = listOf()

    override fun getLayoutRes(): Int {
        return R.layout.fragment_video
    }

    override fun initView(binding: ViewDataBinding?) {
        this.binding = binding as FragmentVideoBinding
    }

    override fun initData() {
        fetchData()
    }


    private fun fetchData() {
        val call = BaseService.getService().getVideoHome(ApiConstant.SECRET_KEY)
        call.enqueue(object : Callback<VideoRespone> {
            override fun onResponse(call: Call<VideoRespone>, response: Response<VideoRespone>) {
                if (response.isSuccessful) {

                }
            }

            override fun onFailure(call: Call<VideoRespone>, t: Throwable) {
                Logs.e(t.toString())
            }
        })
    }

}
