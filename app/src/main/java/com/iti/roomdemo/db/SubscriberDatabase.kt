package com.iti.roomdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Subscriber::class] , version = 1)
abstract class SubscriberDatabase : RoomDatabase() {
    abstract val subscriberDAO: SubscriberDAO

    companion object {
        @Volatile
        private var INSTANCE: SubscriberDatabase? = null
        fun GetInstance(context: Context): SubscriberDatabase {
            synchronized(this) {
                var instance : SubscriberDatabase?= INSTANCE
                if (instance==null){
                    instance= Room.databaseBuilder(                    //take 3 things
                        context.applicationContext,
                        SubscriberDatabase::class.java,
                        "Subscriber_data_database"
                    ).build()
                }
              return instance
            }

        }
    }

}