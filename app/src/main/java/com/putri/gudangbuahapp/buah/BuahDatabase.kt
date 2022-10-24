package com.putri.gudangbuahapp.buah

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.putri.gudangbuahapp.Buah
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

// menyertakan entitas, nilai default awal exportSchema adalah true.
@Database(entities = [Buah::class], version = 1, exportSchema = false)
abstract class BuahDatabase: RoomDatabase() {

    // fungsi abstrak untuk mengembalikan BuahDao
    abstract fun buahDao(): BuahDao

    // semua yang ada di companion object dapat dilihat oleh kelas lain
    companion object{

        // deklarasi variabel instance dengan nilai null
        @Volatile
        private var INSTANCE: BuahDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDataBase(context: Context): BuahDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            // sinkronisasi room database dengan nama database 'database_buah" dan melakukan build.
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BuahDatabase::class.java,
                    "database_buah"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}