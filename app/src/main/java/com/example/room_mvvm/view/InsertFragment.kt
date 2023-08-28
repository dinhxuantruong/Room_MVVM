package com.example.room_mvvm.view

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.room_mvvm.R
import com.example.room_mvvm.databinding.FragmentInsertBinding
import com.example.room_mvvm.model.Person
import com.example.room_mvvm.viewmodel.MyViewModel


class InsertFragment : Fragment() {
    private lateinit var binding: FragmentInsertBinding
    private lateinit var vPerson : MyViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInsertBinding.inflate(layoutInflater,container,false)
        vPerson = ViewModelProvider(requireActivity())[MyViewModel::class.java]

        binding.btnAdd.setOnClickListener {
            insertData()
        }

        return binding.root
    }

    private fun insertData() {
        val age = binding.edtAge.text
        val firstName = binding.edtFirst.text.toString()
        val lastName = binding.edtLast.text.toString()

        if (checkInput(firstName,lastName,age)){
            val person = Person(0,firstName,lastName,Integer.parseInt(age.toString()))
            vPerson.insertData(person)
            Toast.makeText(requireContext(), "Add success!", Toast.LENGTH_SHORT).show()
            resetInput()
        }else{
            Toast.makeText(requireContext(), "Add failed!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkInput(firstName : String, lastName : String, age : Editable) : Boolean{
        return !(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || age.isEmpty())
    }

    private fun resetInput(){
        binding.edtAge.setText("")
        binding.edtFirst.setText("")
        binding.edtLast.setText("")
    }

}