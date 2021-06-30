package com.imagine.roomexample.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
) {
    override fun toString(): String {
        return "Person($id, $name)"
    }
}
