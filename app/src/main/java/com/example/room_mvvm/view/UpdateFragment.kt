package com.example.room_mvvm.view

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.room_mvvm.R
import com.example.room_mvvm.databinding.FragmentUpdateBinding
import com.example.room_mvvm.model.Person
import com.example.room_mvvm.viewmodel.MyViewModel

class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private lateinit var vPerson: MyViewModel
    private val args by navArgs<UpdateFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(layoutInflater, container, false)
        vPerson = ViewModelProvider(requireActivity())[MyViewModel::class.java]

        binding.upFirst.setText(args.currentPerson.firstName)
        binding.upLast.setText(args.currentPerson.lastName)
        binding.upAge.setText(args.currentPerson.age.toString())

        setHasOptionsMenu(true)
        binding.btnUpdate.setOnClickListener {
            updateData()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        return binding.root
    }

    private fun updateData() {
        val id = args.currentPerson.id
        val firstName = binding.upFirst.text.toString()
        val lastName = binding.upLast.text.toString()
        val age = binding.upAge.text

        if (checkInput(firstName, lastName, age)) {
            val person = Person(id, firstName, lastName, Integer.parseInt(age.toString()))
            vPerson.updateData(person)
        }
    }

    private fun checkInput(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.del_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.del_btn) {
            val dialog = AlertDialog.Builder(requireContext())
            dialog.setPositiveButton("Yes") { _, _ ->
                vPerson.deleteData(args.currentPerson)
                Toast.makeText(requireContext(), "Delete success!", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack(R.id.action_updateFragment_to_listFragment,true)
            }
            dialog.setNegativeButton("No") { _, _ ->
            }
            dialog.setTitle("Notification!")
            dialog.setMessage("Delete ${args.currentPerson.lastName} ?")
            dialog.create().show()
        }
        return super.onOptionsItemSelected(item)
    }


}