package com.example.room_mvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room_mvvm.R
import com.example.room_mvvm.adapeter.ListAdapter
import com.example.room_mvvm.databinding.FragmentListBinding
import com.example.room_mvvm.viewmodel.MyViewModel


class ListFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentListBinding
    private lateinit var vPerson: MyViewModel
    private lateinit var adapter: ListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        vPerson = ViewModelProvider(requireActivity())[MyViewModel::class.java]
        adapter = ListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.floatBtn.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_insertFragment)
        }


        setHasOptionsMenu(true)
        vPerson.readAllData.observe(requireActivity()) {
            adapter.setData(it)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val search = menu.findItem(R.id.search_btn)
        val searchView = search.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchDatabase(newText)
        }
        return true
    }

    fun searchDatabase(text: String) {
        val text = "%$text%"
        vPerson.searchDatabase(text).observe(requireActivity()) {
            it.let {
                adapter.setData(it)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.edel){
            val dialog = AlertDialog.Builder(requireContext())
            dialog.setPositiveButton("Yes"){_,_ ->
                vPerson.deleteAllData()
            }
            dialog.setNegativeButton("No"){_,_ ->
            }
            dialog.setTitle("Notification!")
            dialog.setMessage("Delete All Data ?")
            dialog.create().show()
        }
        return super.onOptionsItemSelected(item)
    }

}