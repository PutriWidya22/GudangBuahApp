package com.putri.gudangbuahapp.buah

import androidx.lifecycle.LiveData
import androidx.room.*
import com.putri.gudangbuahapp.Buah


// membuat interface dengan nama BuahDao untuk mengakses database
@Dao
interface BuahDao {

    // berisi function untuk menambahkan data buah yaitu tambahBuah
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun tambahBuah(buah: Buah)

    // berisi function untuk mengubah data buah yaitu updateBuah
    @Update
    suspend fun updateBuah(buah: Buah)

    // berisi function untuk menghapus data buah yaitu hapusBuah
    @Delete
    suspend fun hapusBuah(buah: Buah)

    // berisi query untuk menghapus data pada tabel table_buah
    @Query("Delete from table_buah")
    suspend fun hapusSemuaBuah()

    // berisi query untuk menampilkan data pada tabel table_buah diurutkan dari idnya berdasarkan yang terkecil
    // ke yang terbesar
    @Query("Select * from table_buah order by id ASC") //Ascending order annotating with the query that will fetch the data
    fun readAllData(): LiveData<List<Buah>>

}