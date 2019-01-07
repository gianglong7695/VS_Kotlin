package com.vtv.sports.view.fragment


import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.vtv.sports.R
import com.vtv.sports.databinding.FragmentChartZoneBinding
import com.vtv.sports.model.chart.ChartRespone
import com.vtv.sports.model.chart.Standing
import com.vtv.sports.repository.ApiConstant
import com.vtv.sports.repository.BaseService
import com.vtv.sports.util.Constant
import com.vtv.sports.util.Logs
import com.vtv.sports.util.Utils
import com.vtv.sports.view.adapter.ChartAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Giang Long on 12/20/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class ChartZoneFragment : BaseFragment() {
    private var zoneId: String = ""
    lateinit var binding: FragmentChartZoneBinding
    private var scoreList: List<Standing> = listOf()
    private lateinit var scoreAdapter: ChartAdapter

    companion object {
        fun newInstance(zoneId: String): ChartZoneFragment {
            val args = Bundle()
            args.putSerializable(Constant.KEY_ID, zoneId)
            val fragment = ChartZoneFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_chart_zone
    }

    override fun initView(binding: ViewDataBinding?) {
        this.binding = binding as FragmentChartZoneBinding
        binding.swipeRefresh.setColorSchemeColors(
            ContextCompat.getColor(context!!, R.color.red),
            ContextCompat.getColor(context!!, R.color.green),
            ContextCompat.getColor(context!!, R.color.blue)
        )
        binding.swipeRefresh.setOnRefreshListener { fetchData(zoneId) }


        binding.recyclerChart.layoutManager = Utils.getLayoutManagerVer(context!!)
        binding.recyclerChart.setHasFixedSize(true)
    }

    override fun initData() {
        zoneId = arguments!!.getString(Constant.KEY_ID)
        fetchData(zoneId)
    }


    private fun fetchData(id: String) {
        showRefresh()
        val call = BaseService.getService().getChart(ApiConstant.SECRET_KEY, id)
        call.enqueue(object : Callback<ChartRespone> {
            override fun onResponse(call: Call<ChartRespone>, response: Response<ChartRespone>) {
                hideRefresh()
                if (response.isSuccessful && response.body()?.standing != null) {
                    scoreList = response.body()!!.standing
                    scoreAdapter = ChartAdapter(context!!, scoreList)
                    binding.recyclerChart.adapter = scoreAdapter
                }
            }

            override fun onFailure(call: Call<ChartRespone>, t: Throwable) {
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

