package com.vtv.sports.view.fragment


import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import com.vtv.sports.R
import com.vtv.sports.databinding.FragmentMoreVideoBinding
import com.vtv.sports.model.video.VideoRespone
import com.vtv.sports.repository.ApiConstant
import com.vtv.sports.repository.BaseService
import com.vtv.sports.util.Constant
import com.vtv.sports.util.Logs
import com.vtv.sports.util.Utils
import com.vtv.sports.view.activity.MainActivity
import com.vtv.sports.view.adapter.VideoItemAdapter
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
    private lateinit var itemAdapter: VideoItemAdapter
    private var isLoadMore = true

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

        binding.swipeRefresh.setColorSchemeColors(
                ContextCompat.getColor(context!!, R.color.red),
                ContextCompat.getColor(context!!, R.color.green),
                ContextCompat.getColor(context!!, R.color.blue)
        )
        binding.swipeRefresh.setOnRefreshListener {
            if (itemAdapter != null) {
                pageIndex = 1
                isLoadMore = true
                itemAdapter.setData(listOf())
            }

            fetchData()
        }

        binding.recyclerVideo.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!binding.recyclerVideo.canScrollVertically(1)) {
                    if (!isLoadMore) {
                        isLoadMore = true
                        fetchData()
                    }
                }
            }
        })

        binding.recyclerVideo.layoutManager = Utils.getLayoutManagerVer(binding.root.context)
        itemAdapter = VideoItemAdapter(binding.root.context)
        binding.recyclerVideo.adapter = itemAdapter
    }


    override fun initData() {
        zoneId = arguments!!.getString(Constant.KEY_ID)
        title = arguments!!.getString(Constant.KEY_TITLE)
        binding.layoutToolbar.text_title.text = title
        fetchData()
    }

    private fun fetchData() {
        showRefresh()
        val call = BaseService.getService().getVideoPaging(ApiConstant.SECRET_KEY, zoneId, pageIndex.toString(), "20")
        call.enqueue(object : Callback<VideoRespone> {
            override fun onResponse(call: Call<VideoRespone>, response: Response<VideoRespone>) {
                if (response.isSuccessful && isLoadMore) {
                    val video = response.body()!!
                    pageIndex++
                    itemAdapter.insertData(video.videos)
                }

                isLoadMore = false
                hideRefresh()
            }

            override fun onFailure(call: Call<VideoRespone>, t: Throwable) {
                Logs.e(t.toString())
                isLoadMore = false
                hideRefresh()

            }
        })
    }

    private fun showRefresh() {
        if (!binding.swipeRefresh.isRefreshing) 5
        binding.swipeRefresh.isRefreshing = true
    }

    private fun hideRefresh() {
        Utils.after(Constant.DELAY_REFRESH_DEFAULT) {
            binding.swipeRefresh.isRefreshing = false
        }
    }


}
