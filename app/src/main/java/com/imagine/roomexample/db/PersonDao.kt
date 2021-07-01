package com.imagine.roomexample.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface PersonDao {

    @Query("SELECT * FROM person")
    fun getAllPersons(): Flowable<List<Person>>

    @Insert
    fun addPerson(person: Person): Completable

    @Delete
    fun deletePerson(person: Person): Completable

    @Query("SELECT * FROM person WHERE name = :name")
    fun getPerson(name: String): Single<List<Person>>

}