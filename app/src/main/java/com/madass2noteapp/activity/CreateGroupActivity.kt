package com.madass2noteapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madass2noteapp.R
import kotlinx.android.synthetic.main.create_note.*

class CreateGroupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_group)

        button_save.setOnClickListener {
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

        button_cancel.setOnClickListener{
            setResult(AppCompatActivity.RESULT_CANCELED)
            finish()
        }
    }

}