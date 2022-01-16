package com.example.notesappfragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappfragments.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: ViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RVAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        setupRV()

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.getAllNote.observe(viewLifecycleOwner, { noteList ->
            adapter.updateRV(noteList)
        })
        // Add menu
        setHasOptionsMenu(true)
        return binding.root
    }


    private fun setupRV() {
        recyclerView = binding.recyclerView
        adapter = RVAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    // delete All note
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteAllNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteAllNote()
            Toast.makeText(requireContext(), "Successfully delete everything", Toast.LENGTH_SHORT)
                .show()
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete Everything")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }

}