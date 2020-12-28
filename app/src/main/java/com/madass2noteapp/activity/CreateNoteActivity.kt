package com.madass2noteapp.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.ArrayAdapter
import android.widget.Toast
import com.madass2noteapp.R
import com.madass2noteapp.dataClasses.Note
import com.madass2noteapp.mainApp.MainApp
import kotlinx.android.synthetic.main.create_note.button_cancel
import kotlinx.android.synthetic.main.create_note.button_save
import kotlinx.android.synthetic.main.create_note.button_vtt
import kotlinx.android.synthetic.main.create_note.inputContent_text
import kotlinx.android.synthetic.main.create_note.inputTitle_text
import kotlinx.android.synthetic.main.create_note.select_group
import kotlinx.android.synthetic.main.object_note.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

/**
 * This screen is fo creating new notes
 */
class CreateNoteActivity  : AppCompatActivity() {
    lateinit var app : MainApp
    private val REQUEST_CODE_SPEECH_INPUT = 100
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

        setupGroupList()
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