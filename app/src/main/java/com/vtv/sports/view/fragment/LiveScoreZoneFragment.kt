package com.vtv.sports.view.fragment


import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.vtv.sports.R
import com.vtv.sports.databinding.FragmentNewsZoneBinding
import com.vtv.sports.model.score.Schedule
import com.vtv.sports.model.score.ScoreRespone
import com.vtv.sports.repository.ApiConstant
import com.vtv.sports.repository.BaseService
import com.vtv.sports.util.Constant
import com.vtv.sports.util.Logs
import com.vtv.sports.util.Utils
import com.vtv.sports.view.adapter.LiveScoreAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Giang Long on 12/20/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class LiveScoreZoneFragment : BaseFragment() {
    private var zoneId: String = ""
    lateinit var binding: FragmentNewsZoneBinding
    private var scoreList: List<Schedule> = listOf()
    private lateinit var scoreAdapter: LiveScoreAdapter

    companion object {
        fun newInstance(zoneId: String): LiveScoreZoneFragment {
            val args = Bundle()
            args.putSerializable(Constant.KEY_ID, zoneId)
            val fragment = LiveScoreZoneFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_news_zone
    }

    override fun initView(binding: ViewDataBinding?) {
        this.binding = binding as FragmentNewsZoneBinding
        binding.swipeRefresh.setColorSchemeColors(
            ContextCompat.getColor(context!!, R.color.red),
            ContextCompat.getColor(context!!, R.color.green),
            ContextCompat.getColor(context!!, R.color.blue)
        )
        binding.swipeRefresh.setOnRefreshListener { fetchData(zoneId) }


        binding.recyclerNews.layoutManager = Utils.getLayoutManagerVer(context!!)
        binding.recyclerNews.setHasFixedSize(true)
    }

    override fun initData() {
        zoneId = arguments!!.getString(Constant.KEY_ID)
        fetchData(zoneId)
    }


    private fun fetchData(id: String) {
        showRefresh()
        val call = BaseService.getService().getScore(ApiConstant.SECRET_KEY, id)
        call.enqueue(object : Callback<ScoreRespone> {
            override fun onResponse(call: Call<ScoreRespone>, response: Response<ScoreRespone>) {
                hideRefresh()
                if (response.isSuccessful && response.body()?.schedule != null) {
                    scoreList = response.body()!!.schedule
                    scoreAdapter = LiveScoreAdapter(context!!, scoreList)
                    binding.recyclerNews.adapter = scoreAdapter
                }
            }

            override fun onFailure(call: Call<ScoreRespone>, t: Throwable) {
                hideRefresh()
                Logs.e(t.toString())
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

