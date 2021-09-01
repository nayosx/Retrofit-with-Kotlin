package com.ness.demoretrofitkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ness.demoretrofitkotlin.adapters.MessageResponseAdapter
import com.ness.demoretrofitkotlin.retrofit.app.RetrofitInstance
import com.ness.demoretrofitkotlin.retrofit.domain.MessageResponse
import com.ness.demoretrofitkotlin.retrofit.infra.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var listOfMessageResponse: ArrayList<MessageResponse>
    private lateinit var adapter: MessageResponseAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        getFromWebService()
    }

    private fun initialize() {
        val manager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recyclerViewMessages)

        listOfMessageResponse = ArrayList()
        demo()
        adapter = MessageResponseAdapter(listOfMessageResponse)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, manager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun demo() {
        for(num in 1..10) {
            val messageResponse = MessageResponse(num, num, "date ${num}", false)
            listOfMessageResponse.add(messageResponse)
        }
    }

    private fun getFromWebService() {
        val retrofitServices =  RetrofitInstance.getInstance()?.create(RetrofitService::class.java)
        val responseCall = retrofitServices?.getMessages()

        responseCall?.enqueue(object: Callback<List<MessageResponse>>{
            override fun onResponse(call: Call<List<MessageResponse>>, response: Response<List<MessageResponse>>) {
                if(response.body() != null) {
                    val messages = response.body() as ArrayList<MessageResponse>
                    updateRecyclerView(messages)
                }
            }

            override fun onFailure(call: Call<List<MessageResponse>>, t: Throwable) {
                println(t)
            }

        })
    }

    private fun updateRecyclerView(messages: ArrayList<MessageResponse>) {
        if(messages.size > 0) {
            listOfMessageResponse.clear()
            listOfMessageResponse.addAll(messages)
            adapter.notifyDataSetChanged()
        }
    }
}