package com.madass2noteapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.madass2noteapp.R
import com.madass2noteapp.dataClasses.Group
import com.madass2noteapp.mainApp.MainApp
import kotlinx.android.synthetic.main.create_note.button_cancel
import kotlinx.android.synthetic.main.create_note.button_save
import kotlinx.android.synthetic.main.create_note.inputTitle_text

/**
 * This screen is used to create new Groups
 */
class CreateGroupActivity : AppCompatActivity() {
    lateinit var app : MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_group)
        app = application as MainApp //database acces
        var newGroup = Group()
        this.setTitle("Create Group")


        /**
         * save button
         */
        button_save.setOnClickListener {
            if(inputTitle_text.text.toString().isNotBlank() and inputTitle_text.text.toString().isNotBlank() and inputTitle_text.text.toString().isNotEmpty()){
                if(app.getGroup(inputTitle_text.text.toString()) == null){
                    newGroup.title = inputTitle_text.text.toString()
                    app.createGroup(newGroup.copy())
                    setResult(AppCompatActivity.RESULT_OK)
                    app.save()
                    finish()
                }
                else {
                    Toast.makeText(this,"group name already Exists", Toast.LENGTH_SHORT).show()

                }
            }
            else {
                Toast.makeText(this, "can not be blank", Toast.LENGTH_SHORT).show()
            }
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