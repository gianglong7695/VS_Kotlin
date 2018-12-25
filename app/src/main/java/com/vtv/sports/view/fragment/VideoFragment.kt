package com.vtv.sports.view.fragment


import android.databinding.ViewDataBinding
import com.vtv.sports.R
import com.vtv.sports.databinding.FragmentVideoBinding
import com.vtv.sports.model.video.VideoGroup
import com.vtv.sports.model.video.VideoRespone
import com.vtv.sports.repository.ApiConstant
import com.vtv.sports.repository.BaseService
import com.vtv.sports.util.Logs
import com.vtv.sports.util.Utils
import com.vtv.sports.view.adapter.VideoAdapter
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
    var listGroup: MutableList<VideoGroup> = mutableListOf()
    lateinit var videoAdapter: VideoAdapter
    var isLoaded = false

    override fun getLayoutRes(): Int {
        return R.layout.fragment_video
    }

    override fun initView(binding: ViewDataBinding?) {
        this.binding = binding as FragmentVideoBinding
        videoAdapter = VideoAdapter(context!!)
        binding.recyclerVideo.layoutManager = Utils.getLayoutManagerVer(context!!)
        binding.recyclerVideo.adapter = videoAdapter
        binding.recyclerVideo.setHasFixedSize(true)
        binding.recyclerVideo.setItemViewCacheSize(0)
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (isVisibleToUser && isResumed)
            if (!isLoaded) {
                fetchData()
                isLoaded = true
            }


    }

    override fun initData() {
        if (userVisibleHint) { // fragment is visible
            if (!isLoaded) {
                fetchData()
                isLoaded = true
            }
        }
    }


    private fun fetchData() {
        val call = BaseService.getService().getVideoHome(ApiConstant.SECRET_KEY)
        call.enqueue(object : Callback<VideoRespone> {
            override fun onResponse(call: Call<VideoRespone>, response: Response<VideoRespone>) {
                if (response.isSuccessful) {
                    val video = response.body()!!

                    listGroup.add(VideoGroup(video.videoZone[0], video.noiBat))
                    listGroup.add(VideoGroup(video.videoZone[6], video.vtvSportNews))
                    listGroup.add(VideoGroup(video.videoZone[1], video.highlight))
                    listGroup.add(VideoGroup(video.videoZone[7], video.tuVanTheThao))
                    listGroup.add(VideoGroup(video.videoZone[2], video.trongNuoc))
                    listGroup.add(VideoGroup(video.videoZone[3], video.quocTe))
                    listGroup.add(VideoGroup(video.videoZone[4], video.hauTruong))
                    listGroup.add(VideoGroup(video.videoZone[5], video.tongHopBanThang))


                    videoAdapter.setVideoList(listGroup)
                }
            }

            override fun onFailure(call: Call<VideoRespone>, t: Throwable) {
                Logs.e(t.toString())
            }
        })
    }

}
