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
import com.madass2noteapp.dataClasses.Note
import com.madass2noteapp.mainApp.MainApp
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.menu_group_.*
import kotlinx.android.synthetic.main.menu_note.*
import kotlinx.android.synthetic.main.object_group.*

/**
 * list all notes in curent group
 */
class NoteMenuActivity : AppCompatActivity(), NoteListener {
    lateinit var app : MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_note)
        app = application as MainApp // database accsess
        var group :Group = Group()

        if(intent.hasExtra("group_info")){
            val groupTitle = intent.extras?.getParcelable<Group>("group_info")?.title
            group = app.getGroup(groupTitle)!! // use the title of the group passed in to find group object in data base.
        }

        // sets up recycler
        val layoutManager = LinearLayoutManager(this)
        recycler_notes.layoutManager = layoutManager
        recycler_notes.adapter = NoteAddapter(group.notes, this)

        //back button
        button_back_notemenu.setOnClickListener{
            val intent = Intent(this,
                GroupObjectActivity::class.java)
            intent.putExtra("group_view",group)
            startActivity(intent);
            finish()
        }
    }

    //list button
    override fun onNoteClick(note: Note) {
        val intent = Intent(this,
            NoteObjectActivity::class.java)
        startActivity(intent);
        finish()
    }
}

interface NoteListener {
    fun onNoteClick(note: Note)
}
class NoteAddapter constructor(private var Notes: List<Note>, private val listener: NoteListener):
    RecyclerView.Adapter<NoteAddapter.MainHolder>(){
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
        val note = Notes[holder.adapterPosition]
        holder.bind(note, listener)
    }

    override fun getItemCount(): Int = Notes.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note, listener: NoteListener) {
            itemView.list_Item.text = note.title
            itemView.setOnClickListener {  listener.onNoteClick(note)}
        }
    }
}