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
import com.madass2noteapp.mainApp.MainApp
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.menu_group_.*
import kotlinx.android.synthetic.main.menu_note.*

/**
 * list all notes in curent group
 */
class NoteMenuActivity : AppCompatActivity(), testListener2 {
    lateinit var app : MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_note)
        app = application as MainApp // database accsess

        // sets up recycler
        /*val layoutManager = LinearLayoutManager(this)
        recycler_notes.layoutManager = layoutManager
        recycler_notes.adapter = testAddapter2(app.test, this)*/

        //back button
        button_back_notemenu.setOnClickListener{
            val intent = Intent(this,
                GroupObjectActivity::class.java)
            startActivity(intent);
            finish()
        }
    }

    //list button
    override fun onTestClick(testThing: String) {
        val intent = Intent(this,
            NoteObjectActivity::class.java)
        startActivity(intent);
        finish()
    }
}

interface testListener2 {
    fun onTestClick(testThing: String)
}
class testAddapter2 constructor(private var tests: List<String>, private val listener: testListener2):
    RecyclerView.Adapter<testAddapter2.MainHolder>(){
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

        fun bind(placemark: String, listener: testListener2) {
            itemView.list_Item.text = placemark
            itemView.setOnClickListener {  listener.onTestClick(placemark)}
        }
    }
}