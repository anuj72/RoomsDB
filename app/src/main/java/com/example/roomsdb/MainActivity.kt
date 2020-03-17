package com.example.roomsdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.roomsdb.DB.Dto.NoteDatabase
import com.example.roomsdb.DB.Entity.Note
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : BaseFragment() {
    private var note: Note? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch {
            applicationContext?.let{
                val notes = NoteDatabase(it).getNoteDao().getAllNotes()
                textView?.text=notes.toString()
            }
        }

    }

    fun SaveDb(view: View) {

        var notesdata = editText.text.toString()
        launch {

            applicationContext?.let {
                val mNote = Note(notesdata)

                if (note == null) {
                    NoteDatabase(it).getNoteDao().addNote(mNote)
                    Toast.makeText(applicationContext,"Notes Saved",Toast.LENGTH_SHORT).show()
                    launch {
                        applicationContext?.let{
                            val notes = NoteDatabase(it).getNoteDao().getAllNotes()
                            textView?.text=notes.toString()
                        }
                    }

                } else {
                    mNote.id = note!!.id
                    NoteDatabase(it).getNoteDao().updateNote(mNote)
                    Toast.makeText(applicationContext,"Note Updated",Toast.LENGTH_SHORT).show()

                }



            }
        }
    }
}
