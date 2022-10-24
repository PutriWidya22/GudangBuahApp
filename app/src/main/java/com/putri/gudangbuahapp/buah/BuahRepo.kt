package com.putri.gudangbuahapp.buah

import androidx.lifecycle.LiveData
import com.putri.gudangbuahapp.Buah

// membuat class BuahRepo dengan live data
class BuahRepo(private val buahDao: BuahDao) {
    val readsAllData: LiveData<List<Buah>> = buahDao.readAllData()

    // membuah function tambahBuah yang disimpan pada buahDao
    suspend fun tambahBuah(buah: Buah){
        buahDao.tambahBuah(buah)
    }

    // membuah function updateBuah yang disimpan pada buahDao
    suspend fun updateBuah(buah: Buah){
        buahDao.updateBuah(buah)
    }

    // membuah function hapusBuah yang disimpan pada buahDao
    suspend fun hapusBuah(buah: Buah) {
        buahDao.hapusBuah(buah)
    }

    // membuah function hapusSemuaBuah yang disimpan pada buahDao
    suspend fun hapusSemuaBuah() {
        buahDao.hapusSemuaBuah()
    }
}