package com.example.myapplicationkotlin

import android.content.Context
import android.service.quicksettings.Tile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdaper(val userList: List<MyDataItem>?):RecyclerView.Adapter<MyAdaper.ViewHolder>() {
    class ViewHolder(itemView: View ):RecyclerView.ViewHolder(itemView) {
        var userId : TextView
        var Tile : TextView
        init {
            userId = itemView.findViewById(R.id.userid)
            Tile = itemView.findViewById(R.id.title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.userId.text = userList!![position].userId.toString()
       holder.Tile.text = userList[position].title
    }

    override fun getItemCount(): Int {
        return userList!!.size
    }
}