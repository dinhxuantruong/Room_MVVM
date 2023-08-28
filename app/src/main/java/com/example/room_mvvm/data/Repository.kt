package com.example.room_mvvm.data

import androidx.lifecycle.LiveData
import com.example.room_mvvm.model.Person
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(private val personDao: PersonDao) {

    fun readAllData() : Flow<List<Person>> = personDao.readAllData()

    suspend fun insertData(person: Person){
        personDao.insertData(person)
    }

    suspend fun updateData(person: Person){
        personDao.updateData(person)
    }

    suspend fun deleteData(person: Person){
        personDao.deleteData(person)
    }

    fun searchDatabase(text : String) : Flow<List<Person>>{
        return personDao.searchDatabase(text)
    }

    suspend fun deleteAllData() {
        personDao.deleteAllData()
    }
}