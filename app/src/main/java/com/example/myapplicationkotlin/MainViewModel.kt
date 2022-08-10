package com.example.myapplicationkotlin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    val list = MutableLiveData<List<MyDataItem>>()

    fun getUser() {
        val retentionBundle = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(ApiInterface::class.java)
        val retrofitdata = retentionBundle.getData()
        retrofitdata.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>?,
                response: Response<List<MyDataItem>?>?
            ) {
                val responseBoby = response!!.body()
                list.value = responseBoby!!

            }

            override fun onFailure(call: Call<List<MyDataItem>?>?, t: Throwable?) {
                if (t != null) {
                    Log.d("MainActivity", "onFailure" + t.message)
                }

            }
        })
    }
}