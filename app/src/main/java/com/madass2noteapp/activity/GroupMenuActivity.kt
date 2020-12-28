package com.madass2noteapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.madass2noteapp.R
import com.madass2noteapp.dataClasses.Group
import com.madass2noteapp.mainApp.MainApp
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.menu_group_.*

/**
 * screen for listing all groups
 */
class GroupMenuActivity  : AppCompatActivity(), GroupListener{
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_group_)
        app = application as MainApp //database acces

        //sets up recycler
        val layoutManager = LinearLayoutManager(this)
        recycler_groups.layoutManager = layoutManager
        recycler_groups.adapter = GroupAddapter(app.allGroups, this)

        //back button
        button_back.setOnClickListener{
            val intent = Intent(this,
                NoteTakerActivity::class.java)
            startActivity(intent);
            finish()
        }
    }

    //recycler button
    override fun onGroupClick(group: Group) {
        val intent = Intent(this,
            GroupObjectActivity::class.java)
        intent.putExtra("group_view",group)
        startActivity(intent);
        finish()
    }

}


interface GroupListener {
    fun onGroupClick(group: Group)
}
class GroupAddapter constructor(private var groups: List<Group>, private val listener: GroupListener):RecyclerView.Adapter<GroupAddapter.MainHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val group = groups[holder.adapterPosition]
        holder.bind(group, listener)
    }

    override fun getItemCount(): Int = groups.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(group: Group, listener: GroupListener) {
            itemView.list_Item.text = group.title
            itemView.setOnClickListener {  listener.onGroupClick(group)}
        }
    }
}