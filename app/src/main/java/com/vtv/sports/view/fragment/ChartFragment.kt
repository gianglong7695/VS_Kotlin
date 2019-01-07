package com.vtv.sports.view.fragment


import android.databinding.ViewDataBinding
import com.vtv.sports.R
import com.vtv.sports.databinding.FragmentChartBinding
import com.vtv.sports.model.chart.ChartRespone
import com.vtv.sports.repository.ApiConstant
import com.vtv.sports.repository.BaseService
import com.vtv.sports.util.Logs
import com.vtv.sports.view.adapter.PagerChartAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Giang Long on 12/20/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des: Tab1
 */

class ChartFragment : BaseFragment() {
    lateinit var pagerAdapter: PagerChartAdapter
    var isLoaded = false
    lateinit var binding: FragmentChartBinding

    override fun getLayoutRes(): Int {
        return R.layout.fragment_chart
    }

    override fun initView(binding: ViewDataBinding?) {
        this.binding = binding as FragmentChartBinding
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
        val call = BaseService.getService().getChart(ApiConstant.SECRET_KEY, "0")
        call.enqueue(object : Callback<ChartRespone> {
            override fun onResponse(call: Call<ChartRespone>, response: Response<ChartRespone>) {
                if (response.isSuccessful && response.body()?.standingCategory != null) {
                    pagerAdapter = PagerChartAdapter(fragmentManager, response.body()!!.standingCategory)
                    binding.pagerChart.offscreenPageLimit = 1
                    binding.pagerChart.adapter = pagerAdapter
                    binding.tabChart.setupWithViewPager(binding.pagerChart)

                }
            }

            override fun onFailure(call: Call<ChartRespone>, t: Throwable) {
                Logs.e(t.toString())
            }
        })
    }


}
