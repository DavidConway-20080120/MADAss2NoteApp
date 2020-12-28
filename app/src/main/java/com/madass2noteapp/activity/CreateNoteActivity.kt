package com.madass2noteapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.madass2noteapp.R
import com.madass2noteapp.dataClasses.Group
import com.madass2noteapp.dataClasses.Note
import com.madass2noteapp.mainApp.MainApp
import kotlinx.android.synthetic.main.activity_notetaker.*
import kotlinx.android.synthetic.main.create_note.*

/**
 * This screen is fo creating new notes
 */
class CreateNoteActivity  : AppCompatActivity() {
    lateinit var app : MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_note)
        app = application as MainApp //database acces
        var note = Note()
        /**
         * save button
         */
        button_save.setOnClickListener {
            if(inputTitle_text.text.toString().isNotBlank() and inputTitle_text.text.toString().isNotBlank() and inputTitle_text.text.toString().isNotEmpty()){
                var group = app.getGroup(select_group.selectedItem.toString())!!
                if(group.notes.find { p -> p.title == inputTitle_text.text.toString()} == null){
                    note.title = inputTitle_text.text.toString()
                    note.text = inputContent_text.text.toString()
                    app.createNote(group,note.copy())
                    setResult(AppCompatActivity.RESULT_OK)
                    finish()
                }
                else {
                    Toast.makeText(this,"note in group already Exists", Toast.LENGTH_SHORT).show()
                }
            }
            else {
               Toast.makeText(this, "title can not be blank", Toast.LENGTH_SHORT).show()
            }
        }

        /**
         * cancel button
         */
        button_cancel.setOnClickListener{
            setResult(AppCompatActivity.RESULT_CANCELED)
            finish()
        }

        setupGroupList()
    }

    /**
     * sets up and populates group select spinner
     */
    fun setupGroupList(){
        var allGroupsNames = ArrayList<String>()
        for(group in app.allGroups){
            group.title?.let { allGroupsNames.add(it) }
        }

        select_group.adapter = ArrayAdapter(applicationContext,android.R.layout.simple_expandable_list_item_1,allGroupsNames)
    }

}