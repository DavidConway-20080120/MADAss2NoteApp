package com.madass2noteapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madass2noteapp.R
import kotlinx.android.synthetic.main.create_note.*

/**
 * This screen is used to create new Groups
 */
class CreateGroupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_group)


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