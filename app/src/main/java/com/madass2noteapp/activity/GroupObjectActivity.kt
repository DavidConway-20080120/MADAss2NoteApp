package com.madass2noteapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madass2noteapp.R
import kotlinx.android.synthetic.main.activity_notetaker.*
import kotlinx.android.synthetic.main.menu_group_.*
import kotlinx.android.synthetic.main.menu_group_.button_back
import kotlinx.android.synthetic.main.object_group.*

/**
 * object creen for a group
 */
class GroupObjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.object_group)

        //bacl button
        button_back.setOnClickListener{
            val intent = Intent(this,
                GroupMenuActivity::class.java)
            startActivity(intent);
            finish()
        }

        //open notes of group button
        button_notes.setOnClickListener{
            val intent = Intent(this,
                NoteMenuActivity::class.java)
            startActivity(intent);
            finish()
        }

        //delete button
        button_delete.setOnClickListener{
            val intent = Intent(this,
                GroupMenuActivity::class.java)
            startActivity(intent);
            finish()
        }
    }
}