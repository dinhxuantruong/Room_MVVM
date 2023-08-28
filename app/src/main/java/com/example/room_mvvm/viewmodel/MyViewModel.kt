package com.example.room_mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.room_mvvm.data.Repository
import com.example.room_mvvm.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val readAllData  = repository.readAllData().asLiveData()

    fun insertData(person: Person){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(person)
        }
    }

    fun updateData(person: Person){
        viewModelScope.launch {
            repository.updateData(person)
        }
    }
    fun deleteData(person: Person){
        viewModelScope.launch {
            repository.deleteData(person)
        }
    }

    fun searchDatabase(text : String) : LiveData<List<Person>>{
        return repository.searchDatabase(text).asLiveData()
    }
    fun deleteAllData(){
        viewModelScope.launch {
            repository.deleteAllData()
        }
    }
}