package com.example.notesappfragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesappfragments.databinding.FragmentEditNoteBinding
import java.util.*


class EditNoteFragment : Fragment() {
    lateinit var binding: FragmentEditNoteBinding
    lateinit var viewModel: ViewModel
    private val args by navArgs<EditNoteFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNoteBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)


        binding.edttitle.setText(args.currentNote.title)
        binding.edtSubTitle.setText(args.currentNote.subTitle)
        binding.edtNotes.setText(args.currentNote.noteDescription)

        binding.btnEditNotes.setOnClickListener {
            updateNote()
        }
        // Add menu
        setHasOptionsMenu(true)

        return binding.root

    }

    private fun updateNote() {
        val titleUpdate = binding.edttitle.text.toString()
        val subTitleUpdate = binding.edtSubTitle.text.toString()
        val noteDescriptionUpdate = binding.edtNotes.text.toString()
        val d = Date()
        val notesDateUpdate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)
        if (titleUpdate.isNotEmpty() && subTitleUpdate.isNotEmpty() && noteDescriptionUpdate.isNotEmpty() && notesDateUpdate.isNotEmpty()) {
            val currentNote = Note(
                args.currentNote.pk,
                titleUpdate,
                subTitleUpdate,
                noteDescriptionUpdate,
                notesDateUpdate.toString()
            )
            viewModel.updateNote(currentNote)
            Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_editNoteFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "Fail Notes Update", Toast.LENGTH_SHORT).show()

        }


    }

    // delete selected note
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            viewModel.deleteNote(args.currentNote)
            Toast.makeText(requireContext(),"Successfully deleted:${args.currentNote.title}",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_editNoteFragment_to_homeFragment)
        }
        builder.setNegativeButton("No"){_,_ ->

        }
        builder.setTitle("Delete ${args.currentNote.title}?")
        builder.setMessage("Are you sure you want to delete ${args.currentNote.title}?")
        builder.create().show()
    }

}

