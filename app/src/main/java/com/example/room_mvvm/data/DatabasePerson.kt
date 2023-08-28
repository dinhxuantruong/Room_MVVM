package com.example.room_mvvm.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.room_mvvm.model.Person

@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class DatabasePerson : RoomDatabase() {

    abstract fun personDao() : PersonDao
}