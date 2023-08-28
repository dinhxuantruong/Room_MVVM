package com.example.room_mvvm.adapeter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.room_mvvm.R
import com.example.room_mvvm.model.Person
import com.example.room_mvvm.view.ListFragmentDirections


class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    //check update
    private var oldPerson = emptyList<Person>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentPerson = oldPerson[position]
        val age = holder.itemView.findViewById<TextView>(R.id.txtAge)
        val firstName = holder.itemView.findViewById<TextView>(R.id.txtFirst)
        val lastName = holder.itemView.findViewById<TextView>(R.id.txtLast)
        val rowlayout : ConstraintLayout = holder.itemView.findViewById(R.id.rowlayout)

        age.text = currentPerson.age.toString()
        firstName.text = currentPerson.firstName
        lastName.text = currentPerson.lastName


        rowlayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentPerson)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return oldPerson.size
    }

    fun setData(newPerson: List<Person>) {
        oldPerson = newPerson
        notifyDataSetChanged()
    }
}