package com.example.notesappfragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.notesappfragments.databinding.FragmentCreateNoteBinding
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CreateNoteFragment : Fragment() {

    lateinit var binding: FragmentCreateNoteBinding
    lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNoteBinding.inflate(layoutInflater,container,false)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        binding.btnSaveNotes.setOnClickListener {
            addNote(it)
        }
        return binding.root
    }
    private fun addNote(it: View?) {
        val title = binding.edtTitle.text.toString()
        val subTitle = binding.edtSubTitle.text.toString()
        val noteDescription = binding.edtNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)
        Log.e("error", "createNotes: $notesDate")

        if (title.isNotEmpty() && subTitle.isNotEmpty() && noteDescription.isNotEmpty() && notesDate.isNotEmpty()){
            viewModel.addNote(Note(0,title,subTitle,noteDescription,notesDate.toString()))
            Toast.makeText(requireContext(), "Notes added", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_homeFragment)
        }
        else{
            Toast.makeText(requireContext(), "Fail Notes added", Toast.LENGTH_SHORT).show()

        }

    }

}