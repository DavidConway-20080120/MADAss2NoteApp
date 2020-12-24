package com.madass2noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.create_note.*

class CreateNoteActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_note)

        button_save.setOnClickListener {
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }
    }


}