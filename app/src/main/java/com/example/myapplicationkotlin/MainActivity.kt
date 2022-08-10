package com.example.myapplicationkotlin

import android.os.Bundle
import android.util.Log.d
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationkotlin.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    companion object {
        const val BASE_URL = " https://jsonplaceholder.typicode.com/"
    }

    lateinit var binding: ActivityMainBinding
    lateinit var myAdaper: MyAdaper
    lateinit var linearLayoutManager: LinearLayoutManager
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerview.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = linearLayoutManager
        viewModel.getUser()
        viewModel.list.observe(this) {
            myAdaper = MyAdaper(it)
            myAdaper.notifyDataSetChanged()
            binding.recyclerview.adapter = myAdaper
        }
    }

    private fun getMyData() {
        val retentionBundle = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(ApiInterface::class.java)
        val retrofitdata = retentionBundle.getData()
        retrofitdata.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>?,
                response: Response<List<MyDataItem>?>?
            ) {
                val responseBoby = response!!.body()

            }

            override fun onFailure(call: Call<List<MyDataItem>?>?, t: Throwable?) {
                if (t != null) {
                    d("MainActivity", "onFailure" + t.message)
                }

            }
        })
    }
}