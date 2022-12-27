package com.murat.hiltroomdatabase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.murat.hiltroomdatabase.R
import com.murat.hiltroomdatabase.adapter.NoteAdapter
import com.murat.hiltroomdatabase.databinding.ActivityUpdateNoteBinding
import com.murat.hiltroomdatabase.db.NoteEntity
import com.murat.hiltroomdatabase.repository.DbRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class UpdateNoteActivity : AppCompatActivity() {

    lateinit var binding: ActivityUpdateNoteBinding

    @Inject
    lateinit var repository: DbRepository
    @Inject
    lateinit var noteAdapter: NoteAdapter
    @Inject
    lateinit var noteEntity: NoteEntity


    private var noteId =0
    private var defaultTitle =""
    private var defaultDesc =""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            noteId= it.getInt("NOTE_ID")
        }

        binding.apply {
            defaultTitle =repository.getNote(noteId).noteTitle
            defaultDesc = repository.getNote(noteId).noteDesc
            edtTitle.setText(defaultTitle)
            edtDesc.setText(defaultDesc)

            btnDelete.setOnClickListener {
                noteEntity = NoteEntity(noteId,defaultTitle,defaultDesc)
                repository.deleteNote(noteEntity)
                finish()
            }
            btnSave.setOnClickListener {
                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()
                if (title.isNotEmpty() || desc.isNotEmpty()){
                    noteEntity = NoteEntity( 0,title,desc)
                    repository.updateNote(noteEntity)
                    finish()
                }else{
                    Toast.makeText(this@UpdateNoteActivity, "Название и описание не могут быть пустыми", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}