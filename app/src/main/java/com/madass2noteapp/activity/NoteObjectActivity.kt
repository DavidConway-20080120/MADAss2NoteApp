package com.madass2noteapp.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.madass2noteapp.R
import com.madass2noteapp.dataClasses.Group
import com.madass2noteapp.dataClasses.Note
import com.madass2noteapp.mainApp.MainApp
import kotlinx.android.synthetic.main.create_note.*
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.menu_note.*
import kotlinx.android.synthetic.main.object_group.*
import kotlinx.android.synthetic.main.object_note.*
import kotlinx.android.synthetic.main.object_note.button_cancel
import kotlinx.android.synthetic.main.object_note.button_delete
import kotlinx.android.synthetic.main.object_note.button_save
import kotlinx.android.synthetic.main.object_note.button_vtt
import kotlinx.android.synthetic.main.object_note.inputContent_text
import kotlinx.android.synthetic.main.object_note.inputTitle_text
import kotlinx.android.synthetic.main.object_note.select_group
import java.lang.Exception
import java.util.*

/**
 * object screen for a note
 */
class NoteObjectActivity : AppCompatActivity() {
    private val REQUEST_CODE_SPEECH_INPUT = 100
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.object_note)
        var group = Group()
        var note = Note()
        app = application as MainApp // database accsess
        setupGroupList()

        if(intent.hasExtra("group_info")){
            val groupTitle = intent.extras?.getParcelable<Group>("group_info")?.title
            group = app.getGroup(groupTitle)!! // use the title of the group passed in to find group object in data base.
        }

        if(intent.hasExtra("note_view")){
            val noteTitle = intent.extras?.getParcelable<Note>("note_view")?.title
            note = app.getNote(group,noteTitle)!! // use the title of the group passed in to find group object in data base.
            inputContent_text.setText(note.text)
            inputTitle_text.setText(note.title)
            select_group.setSelection(app.allGroups.indexOf(group)) // sets the group selection to the corect group

        }
        //cancel button
        button_cancel.setOnClickListener{
            val intent = Intent(this,
                NoteMenuActivity::class.java)
            intent.putExtra("group_info",group)
            startActivity(intent);
            finish()
        }

        //voice to text button
        button_vtt.setOnClickListener{
            //sets up and exicute text to speach request
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "what is your note")

            try{
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT )
            }
            catch (e: Exception){
                inputContent_text.setText("an error has occurred")
            }
        }

        //save button
        button_save.setOnClickListener{
            val intent = Intent(this,
                NoteMenuActivity::class.java)

            if(inputTitle_text.text.toString().isNotBlank() and inputTitle_text.text.toString().isNotBlank() and inputTitle_text.text.toString().isNotEmpty()){ //makes sure titel is not blank
                if(group.notes.find { p -> p.title == inputTitle_text.text.toString()} == null || inputTitle_text.text.toString() == note.title){ //make sure titel dosent alredy exist
                    note.title = inputTitle_text.text.toString()
                    note.text = inputContent_text.text.toString()
                    val newGroup = app.getGroup(select_group.selectedItem.toString())!!
                    if(group != newGroup) { // if the group has change
                        app.noteSwap(note, group, newGroup)
                    }
                    Toast.makeText(this,"saved", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,
                        NoteMenuActivity::class.java)
                    intent.putExtra("group_info",group)
                    startActivity(intent);
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

        //back button
        button_delete.setOnClickListener{
            val intent = Intent(this,
                NoteMenuActivity::class.java)
            intent.putExtra("group_info",group)
            group.notes.remove(note)
            startActivity(intent);
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //checks what the request code was
        when(requestCode){
            REQUEST_CODE_SPEECH_INPUT -> {
                // if request was from text to speach
                if(resultCode == Activity.RESULT_OK && data != null){
                    //get the result and display it
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    inputContent_text.setText(result?.get(0))
                }
            }
        }
    }

    fun setupGroupList(){
        var allGroupsNames = ArrayList<String>()
        for(group in app.allGroups){
            group.title?.let { allGroupsNames.add(it) }
        }

        select_group.adapter = ArrayAdapter(applicationContext,android.R.layout.simple_expandable_list_item_1,allGroupsNames)
    }
}