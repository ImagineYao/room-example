package com.imagine.roomexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.imagine.roomexample.databinding.ActivityMainBinding
import com.imagine.roomexample.db.Person
import com.imagine.roomexample.db.PersonDatabase
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var db: PersonDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        db = PersonDatabase.getDatabase(applicationContext)

        mBinding.add.setOnClickListener {
            val person = Person(3, "Jack")
            db.personDao().addPerson(person).subscribeOn(Schedulers.io()).subscribe({
                Log.i(TAG, "success")
            }, {
                Log.i(TAG, "$it")
            })
        }
        mBinding.remove.setOnClickListener {
            val person = Person(3, "Jack")
            db.personDao().deletePerson(person).subscribeOn(Schedulers.io()).subscribe({
                Log.i(TAG, "success")
            }, {
                Log.i(TAG, "$it")
            })
        }
        mBinding.showAll.setOnClickListener {
            db.personDao().getAllPersons().observeOn(Schedulers.io()).subscribe({
                Log.i(TAG, it.toString())
            }, {
                Log.i(TAG, "$it")
            })
        }
        mBinding.showSpecific.setOnClickListener {
            db.personDao().getPerson("Tom").observeOn(Schedulers.io()).subscribe({
                Log.i(TAG, it.toString())
            }, {
                Log.i(TAG, "$it")
            })
        }
    }

}
