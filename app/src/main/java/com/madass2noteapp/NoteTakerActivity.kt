package com.madass2noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_notetaker.*
import java.io.Console

class NoteTakerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notetaker) // not a error for some reason it wants to give it a cap T

        //gose to group menu
        button_group.setOnClickListener{
            val intent = Intent(this,GroupMenuActivity::class.java)
            startActivity(intent);
            finish()
        }

        button_newText.setOnClickListener{
            val intent = Intent(this,CreateNoteActivity::class.java)
            startActivityForResult(intent, AppCompatActivity.RESULT_OK);
        }

        button_newGroup.setOnClickListener{
            val intent = Intent(this,CreateGroupActivity::class.java)
            startActivityForResult(intent, AppCompatActivity.RESULT_OK);
        }

        button_exit.setOnClickListener{
            finish();
            System.exit(0);
        }

    }
}