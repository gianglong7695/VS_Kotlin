package com.vtv.sports.view.fragment


import android.databinding.ViewDataBinding
import com.vtv.sports.R
import com.vtv.sports.databinding.FragmentLivescoreBinding
import com.vtv.sports.model.score.ScoreRespone
import com.vtv.sports.repository.ApiConstant
import com.vtv.sports.repository.BaseService
import com.vtv.sports.util.Logs
import com.vtv.sports.view.adapter.PagerScoreAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Giang Long on 12/20/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des: Tab1
 */

class LiveScoreFragment : BaseFragment() {

    lateinit var pagerAdapter: PagerScoreAdapter
    var isLoaded = false
    lateinit var binding: FragmentLivescoreBinding


    override fun getLayoutRes(): Int {
        return R.layout.fragment_livescore
    }

    override fun initView(binding: ViewDataBinding?) {
        this.binding = binding as FragmentLivescoreBinding
    }

    override fun initData() {
        if (userVisibleHint) { // fragment is visible
            if (!isLoaded) {
                fetchData()
                isLoaded = true
            }
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (isVisibleToUser && isResumed)
            if (!isLoaded) {
                fetchData()
                isLoaded = true
            }


    }

    private fun fetchData() {
        val call = BaseService.getService().getScore(ApiConstant.SECRET_KEY, "0")
        call.enqueue(object : Callback<ScoreRespone> {
            override fun onResponse(call: Call<ScoreRespone>, response: Response<ScoreRespone>) {
                if (response.isSuccessful && response.body()?.scheduleCategory != null) {
                    pagerAdapter = PagerScoreAdapter(fragmentManager, response.body()!!.scheduleCategory)
                    binding.pagerScore.offscreenPageLimit = 1
                    binding.pagerScore.adapter = pagerAdapter
                    binding.tabLayoutScore.setupWithViewPager(binding.pagerScore)

                }
            }

            override fun onFailure(call: Call<ScoreRespone>, t: Throwable) {
                Logs.e(t.toString())
            }
        })
    }


}
