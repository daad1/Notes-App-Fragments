package com.example.notesappfragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappfragments.databinding.ItemRowBinding

class RVAdapter (private  val fragment:HomeFragment): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    private var notesList = listOf<Note>()
    class ItemViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val note = notesList[position]
        holder.binding.apply {
            notesTitle.text = note.title
            notesSubTitle.text = note.subTitle
            noteDescription.text = note.noteDescription
            notesDate.text = note.date
        }

        holder.binding.rowLayout.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(note)
            holder.itemView.findNavController().navigate(action)
        }
    }


    override fun getItemCount(): Int {
        return notesList.size
    }

    fun updateRV(notesList: List<Note>) {
        this.notesList = notesList
        notifyDataSetChanged()
    }
}

private fun Any.actionHomeFragmentToEditNoteFragment(note: Note): NavDirections {
    TODO("Not yet implemented")
}
