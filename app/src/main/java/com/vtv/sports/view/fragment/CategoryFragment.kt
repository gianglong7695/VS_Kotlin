package com.vtv.sports.view.fragment


import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import com.vtv.sports.R
import com.vtv.sports.databinding.FragmentCategoryBinding
import com.vtv.sports.model.news.News
import com.vtv.sports.model.news.NewsRespone
import com.vtv.sports.repository.ApiConstant
import com.vtv.sports.repository.BaseService
import com.vtv.sports.util.Constant
import com.vtv.sports.util.Logs
import com.vtv.sports.util.Utils
import com.vtv.sports.view.activity.MainActivity
import com.vtv.sports.view.adapter.NewsAdapter
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Giang Long on 12/20/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */

class CategoryFragment : BaseFragment() {

    private var zoneId: String = ""
    lateinit var binding: FragmentCategoryBinding
    private var newsList: List<News>? = listOf()
    private lateinit var newsAdapter: NewsAdapter
    private var pageIndex = 2
    private var isLoadMore = false
    private var title = ""

    companion object {
        fun newInstance(zoneId: String, title: String): CategoryFragment {
            val args = Bundle()
            args.putSerializable(Constant.KEY_ID, zoneId)
            args.putSerializable(Constant.KEY_TITLE, title)
            val fragment = CategoryFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_category
    }

    override fun initView(binding: ViewDataBinding?) {
        this.binding = binding as FragmentCategoryBinding


        binding.layoutToolbar.img_back.setOnClickListener {
            (binding.root.context as MainActivity).onBackPressed()
        }

        binding.swipeRefresh.setColorSchemeColors(
            ContextCompat.getColor(context!!, R.color.red),
            ContextCompat.getColor(context!!, R.color.green),
            ContextCompat.getColor(context!!, R.color.blue)
        )
        binding.swipeRefresh.setOnRefreshListener { fetchData(zoneId) }

        binding.recyclerCategory.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!binding.recyclerCategory.canScrollVertically(1)) {
                    if (!isLoadMore) {
                        isLoadMore = true
                        fetchDataWithPaging()
                    }
                }
            }
        })


        binding.recyclerCategory.layoutManager = Utils.getLayoutManagerVer(context!!)
        newsAdapter = NewsAdapter(context!!)
        newsAdapter.setHeaderEnable(false)
        binding.recyclerCategory.adapter = newsAdapter
    }

    override fun initData() {
        zoneId = arguments!!.getString(Constant.KEY_ID)
        title = arguments!!.getString(Constant.KEY_TITLE)
        binding.layoutToolbar.text_title.text = title
        fetchData(zoneId)
    }


    private fun fetchData(id: String) {
        showRefresh()
        val call = BaseService.getService().getNewsZone(ApiConstant.SECRET_KEY, id)
        call.enqueue(object : Callback<NewsRespone> {
            override fun onResponse(call: Call<NewsRespone>, response: Response<NewsRespone>) {
                hideRefresh()
                if (response.isSuccessful && response.body()?.news != null) {
                    newsList = response.body()!!.news
                    newsAdapter.insertData(newsList!!)
                }
            }

            override fun onFailure(call: Call<NewsRespone>, t: Throwable) {
                hideRefresh()
                Logs.e(t.toString())
            }
        })
    }


    private fun fetchDataWithPaging() {
        val call = BaseService.getService().getNewsZonePaging(ApiConstant.SECRET_KEY, zoneId, pageIndex.toString())
        call.enqueue(object : Callback<NewsRespone> {
            override fun onResponse(call: Call<NewsRespone>, response: Response<NewsRespone>) {
                if (response.isSuccessful && response.body()?.news != null) {
                    if (newsAdapter != null && isLoadMore) {
                        pageIndex++
                        newsAdapter.insertData(response.body()!!.news)
                    }
                }

                isLoadMore = false
            }

            override fun onFailure(call: Call<NewsRespone>, t: Throwable) {
                Logs.e(t.toString())
                isLoadMore = false
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
