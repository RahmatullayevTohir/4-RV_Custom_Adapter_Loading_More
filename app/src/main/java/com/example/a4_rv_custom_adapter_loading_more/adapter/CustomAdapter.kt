package com.example.a4_rv_custom_adapter_loading_more.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a4_rv_custom_adapter_loading_more.R
import com.example.a4_rv_custom_adapter_loading_more.listener.onBottomReachedListener
import com.example.a4_rv_custom_adapter_loading_more.model.User

class CustomAdapter(val users: List<User>,val listener: onBottomReachedListener):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_ITEM_VIEW_YES = 1
    private val TYPE_ITEM_VIEW_NOT = 0

    override fun getItemViewType(position: Int): Int {
        val user = users.get(position)
        return if (user.available) TYPE_ITEM_VIEW_YES else TYPE_ITEM_VIEW_NOT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType==TYPE_ITEM_VIEW_YES){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_yes,parent,false)
            return CustomViewHolderYes(view)
        }
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_not,parent,false)
        return CustomViewHolderNot(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position==users.size-1){
            listener.OnBottomReached(position)
        }
        val user = users.get(position)
        if (holder is CustomViewHolderYes){
            holder.name.setText(user.name)
        }
        if (holder is CustomViewHolderNot){
            holder.name.setText(user.name)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class CustomViewHolderYes(itemView: View):RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.name)
    }

    class CustomViewHolderNot(itemView: View):RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.name)
    }
}