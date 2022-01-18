package com.example.a4_rv_custom_adapter_loading_more

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a4_rv_custom_adapter_loading_more.adapter.CustomAdapter
import com.example.a4_rv_custom_adapter_loading_more.listener.onBottomReachedListener
import com.example.a4_rv_custom_adapter_loading_more.model.User

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        val users = prepareUserList()
        refreshAdapter(users)
    }

    private fun refreshAdapter(users: List<User>){
        val adapter = CustomAdapter(users,object :onBottomReachedListener{
            override fun OnBottomReached(position: Int) {
                Log.d("@@@","@@@onBottom"+position)
            }
        })
        recyclerView.adapter = adapter
    }

    private fun prepareUserList():List<User> {
        val users = ArrayList<User>()
        for (i in 1..20){
            if (i==2||i==5||i==10){
                users.add(User("Tohir "+i,false))
            }
            else{
                users.add(User("Android "+i,true))
            }
        }
        return users
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager =GridLayoutManager(this,1)
    }
}