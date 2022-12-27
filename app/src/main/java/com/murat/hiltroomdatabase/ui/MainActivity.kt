package com.murat.hiltroomdatabase.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.murat.hiltroomdatabase.R
import com.murat.hiltroomdatabase.adapter.NoteAdapter
import com.murat.hiltroomdatabase.databinding.ActivityMainBinding
import com.murat.hiltroomdatabase.db.NoteEntity
import com.murat.hiltroomdatabase.repository.DbRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var repository: DbRepository

    @Inject
    lateinit var noteAdapter: NoteAdapter

    @Inject
    lateinit var noteEntity: NoteEntity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAddNote.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        checkItem()
    }

    fun checkItem(){
        binding.apply {
            if (repository.getAllNote().isNotEmpty()){
                rvNoteList.isVisible = true
                tvEmptyText.isVisible = false
                noteAdapter.differ.submitList(repository.getAllNote())
                setupRecyclerView()
            }
            else{
                rvNoteList.isVisible = false
                tvEmptyText.isVisible = true

            }
        }

    }

    private fun setupRecyclerView() {
        binding.rvNoteList.apply {
            layoutManager= LinearLayoutManager(this@MainActivity)
            adapter =noteAdapter
        }
    }
}