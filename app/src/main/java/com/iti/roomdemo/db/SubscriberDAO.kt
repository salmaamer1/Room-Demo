package com.iti.roomdemo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SubscriberDAO {
    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber): Long

    /*@Insert
    fun inserSubscriber2(subscriber: Subscriber) : Long
    @Insert
    fun insertSubscribers(subscriber1: Subscriber,subscriber2: Subscriber,subscriber3: Subscriber) : List<Long>
    @Insert
    fun insertSubscribers(subscriber: List<Subscriber>) : List<Long>
    @Insert
    fun insertSubscribers2(subscriber: Subscriber,subscribers: List<Subscriber>) : List<Long> */  //euals update//
    @Update
    suspend fun updateSubscriber(subscriber: Subscriber) :Int
    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber) :Int
    @Query(" DELETE FROM subscriber_data_table")
    suspend fun deleteAll():Int

    @Query("SELECT * FROM subscriber_data_table")
    fun getAllSubscriber(): LiveData<List<Subscriber>>
}