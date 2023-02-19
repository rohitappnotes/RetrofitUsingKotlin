package com.network.ui.test;

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.network.data.remote.ApiService
import com.network.data.remote.ApiServiceGenerator.createService
import com.network.data.remote.model.BaseResponseObjectFormat
import com.network.databinding.ActivityTestBinding
import com.network.model.Data
import com.network.ui.base.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


public class TestActivity : BaseActivity<ActivityTestBinding>() {

    override fun getActivityClassName(): String {
        return TestActivity::class.java.simpleName
    }

    override fun doInOnCreate(savedInstanceState: Bundle?) {
    }

    override fun getViewBinding(inflater: LayoutInflater): ActivityTestBinding {
        return ActivityTestBinding.inflate(inflater)
    }

    override fun init() {
        val apiService = createService(ApiService::class.java)
        apiService.employee()
            .enqueue(object : Callback<BaseResponseObjectFormat<Data>> {
                override fun onResponse(call: Call<BaseResponseObjectFormat<Data>>, response: Response<BaseResponseObjectFormat<Data>>) {
                /*    if (response.body() != null) {

                        val clothes = response.body()
                        clothes?.message
                    }
                    */








                    if (response.body() != null) {
                        if (response.isSuccessful) {
                            val body: BaseResponseObjectFormat<Data> = response.body()!!
                            if (body.success) {
                                if (body.data == null) {
                                    Log.e(mTag, "onResponse 1 : " + body.message)
                                } else {
                                    Log.e(mTag, "onResponse 2 : " + body.data!!.name)
                                }
                            } else {
                                Log.e(mTag, "onResponse 3 : " + body.success)
                            }
                        } else {
                            Log.e(mTag, "onResponse 4 : " + response.body()!!.message)
                        }
                    } else {
                        Log.e(mTag, "onResponse 5 : " + response.body())
                    }
                }

                override fun onFailure(call: Call<BaseResponseObjectFormat<Data>>, throwable: Throwable) {
                    Log.e(mTag, "onFailure : " + throwable.message)
                }
            })

    }




}