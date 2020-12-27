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
import com.madass2noteapp.dataClasses.test
import com.madass2noteapp.mainApp.MainApp
import kotlinx.android.synthetic.main.activity_notetaker.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.menu_group_.*

class GroupMenuActivity  : AppCompatActivity(), testListener{
    lateinit var app : MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_group_)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recycler_groups.layoutManager = layoutManager
        recycler_groups.adapter = testAddapter(app.test, this)

        button_back.setOnClickListener{
            val intent = Intent(this,
                NoteTakerActivity::class.java)
            startActivity(intent);
            finish()
        }
    }

    override fun onTestClick(testThing: String) {
        val intent = Intent(this,
            GroupObjectActivity::class.java)
        startActivity(intent);
        finish()
    }

}


interface testListener {
    fun onTestClick(testThing: String)
}
class testAddapter constructor(private var tests: List<String>, private val listener: testListener):RecyclerView.Adapter<testAddapter.MainHolder>(){
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
        val test = tests[holder.adapterPosition]
        holder.bind(test, listener)
    }

    override fun getItemCount(): Int = tests.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(placemark: String, listener: testListener) {
            itemView.list_Item.text = placemark
            itemView.setOnClickListener {  listener.onTestClick(placemark)}
        }
    }
}