package com.madass2noteapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madass2noteapp.R
import com.madass2noteapp.mainApp.MainApp
import kotlinx.android.synthetic.main.activity_notetaker.*

/**
 * main screen
 */
class NoteTakerActivity : AppCompatActivity() {
    lateinit var app : MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        app = application as MainApp // database accsess
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notetaker) // not a error for some reason it wants to give it a cap T

        //gose to group menu
        button_group.setOnClickListener{
            val intent = Intent(this,
                GroupMenuActivity::class.java)
            startActivity(intent);
            finish()
        }

        //create new note
        button_newText.setOnClickListener{
            val intent = Intent(this,
                CreateNoteActivity::class.java)
            startActivityForResult(intent, AppCompatActivity.RESULT_OK);
        }

        //create new group
        button_newGroup.setOnClickListener{
            val intent = Intent(this,
                CreateGroupActivity::class.java)
            startActivityForResult(intent, AppCompatActivity.RESULT_OK);
        }

        //button exit
        button_exit.setOnClickListener{
            finish();
            app.save()
            System.exit(0);
        }

    }
}