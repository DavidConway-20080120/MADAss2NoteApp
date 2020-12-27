package com.madass2noteapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madass2noteapp.R
import kotlinx.android.synthetic.main.activity_notetaker.*
import kotlinx.android.synthetic.main.menu_group_.*
import kotlinx.android.synthetic.main.menu_group_.button_back
import kotlinx.android.synthetic.main.object_group.*

class GroupObjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.object_group)

        button_back.setOnClickListener{
            val intent = Intent(this,
                GroupMenuActivity::class.java)
            startActivity(intent);
            finish()
        }
        button_notes.setOnClickListener{
            val intent = Intent(this,
                NoteObjectActivity::class.java)
            startActivity(intent);
            finish()
        }
        button_delete.setOnClickListener{
            val intent = Intent(this,
                GroupMenuActivity::class.java)
            startActivity(intent);
            finish()
        }
    }
}