package com.example.room_mvvm.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "person_table")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val firstName : String,
    val lastName : String,
    val age : Int
) : Parcelable
