package com.madass2noteapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.madass2noteapp.R
import com.madass2noteapp.dataClasses.Group
import com.madass2noteapp.mainApp.MainApp
import kotlinx.android.synthetic.main.menu_group_.button_back
import kotlinx.android.synthetic.main.object_group.*
import kotlinx.android.synthetic.main.object_group.button_save
import kotlinx.android.synthetic.main.object_group.inputTitle_text

/**
 * object creen for a group
 */
class GroupObjectActivity : AppCompatActivity() {
    lateinit var app : MainApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.object_group)
        var group: Group = Group()
        app = application as MainApp //database acces

        if(intent.hasExtra("group_view")){
            val groupTitle = intent.extras?.getParcelable<Group>("group_view")?.title
            group = app.getGroup(groupTitle)!! // use the title of the group passed in to find group object in data base.
            inputTitle_text.setText(group.title)
        }
        this.setTitle("${group.title}")

        //back button
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
            intent.putExtra("group_info", group)
            startActivity(intent);
            finish()
        }

        //delete button
        button_delete.setOnClickListener(){
            if(group.title != "Default") {
                app.allGroups.remove(group)
                Toast.makeText(this, "${group.title} Deleted", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,
                    GroupMenuActivity::class.java)
                startActivity(intent);
                app.save()
                finish()
            }
            else {
                Toast.makeText(this, "Default can not be modified", Toast.LENGTH_SHORT).show()
            }
        }

        button_save.setOnClickListener(){
            if(group.title != "Default") {
                if (inputTitle_text.text.toString().isNotBlank() && inputTitle_text.text.toString()
                        .isNotBlank() && inputTitle_text.text.toString().isNotEmpty()
                ) {
                    if (app.getGroup(inputTitle_text.text.toString()) == null || group.title == inputTitle_text.text.toString()) {
                        group.title = inputTitle_text.text.toString()
                        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                        val intent = Intent(
                            this,
                            GroupMenuActivity::class.java
                        )
                        startActivity(intent);
                        app.save()
                        finish()
                    } else {
                        Toast.makeText(this, "group name already Exists", Toast.LENGTH_SHORT).show()

                    }
                } else {
                    Toast.makeText(this, "can not be blank", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(this, "Default can not be modified", Toast.LENGTH_SHORT).show()
            }
        }
    }
}