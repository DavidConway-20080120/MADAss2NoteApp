package com.madass2noteapp.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.madass2noteapp.R
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.menu_note.*
import kotlinx.android.synthetic.main.object_note.*
import java.lang.Exception
import java.util.*

/**
 * object screen for a note
 */
class NoteObjectActivity : AppCompatActivity() {
    private val REQUEST_CODE_SPEECH_INPUT = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.object_note)

        //cancel button
        button_cancel.setOnClickListener{
            val intent = Intent(this,
                NoteMenuActivity::class.java)
            startActivity(intent);
            finish()
        }

        //voice to text button
        button_vtt.setOnClickListener{
            //sets up and exicute text to speach request
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "what is your note")

            try{
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT )
            }
            catch (e: Exception){
                inputContent_text.setText("an error has occurred")
            }
        }

        //save button
        button_save.setOnClickListener{
            val intent = Intent(this,
                NoteMenuActivity::class.java)
            startActivity(intent);
            finish()
        }

        //back button
        button_delete.setOnClickListener{
            val intent = Intent(this,
                NoteMenuActivity::class.java)
            startActivity(intent);
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //checks what the request code was
        when(requestCode){
            REQUEST_CODE_SPEECH_INPUT -> {
                // if request was from text to speach
                if(resultCode == Activity.RESULT_OK && data != null){
                    //get the result and display it
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    inputContent_text.setText(result?.get(0))
                }
            }
        }
    }
}