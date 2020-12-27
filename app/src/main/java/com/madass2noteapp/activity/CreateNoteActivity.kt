package com.madass2noteapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madass2noteapp.R
import kotlinx.android.synthetic.main.create_note.*

/**
 * This screen is fo creating new notes
 */
class CreateNoteActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_note)

        /**
         * save button
         */
        button_save.setOnClickListener {
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

        /**
         * cancel button
         */
        button_cancel.setOnClickListener{
            setResult(AppCompatActivity.RESULT_CANCELED)
            finish()
        }
    }


}