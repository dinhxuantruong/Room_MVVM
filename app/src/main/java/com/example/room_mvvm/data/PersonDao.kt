package com.example.room_mvvm.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.room_mvvm.model.Person
import kotlinx.coroutines.flow.Flow


@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(person: Person)

    @Delete
    suspend fun deleteData(person: Person)

    @Update
    suspend fun updateData(person: Person)

    @Query("select * from person_table order by id asc")
    fun readAllData() : Flow<List<Person>>


    @Query("delete from person_table")
    suspend fun deleteAllData()

    @Query("select * from person_table where firstName like :searchString or lastName like :searchString")
    fun searchDatabase(searchString : String) : Flow<List<Person>>
}