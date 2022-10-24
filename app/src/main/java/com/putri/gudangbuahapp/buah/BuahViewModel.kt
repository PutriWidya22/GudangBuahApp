package com.putri.gudangbuahapp.buah

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.putri.gudangbuahapp.Buah
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// membuat class BuahViewModel
class BuahViewModel(application: Application): AndroidViewModel(Application()) {
    val readAllData: LiveData<List<Buah>>
    private val repository: BuahRepo

    // deklarasi buahDao, repositorynya berada di BuahRepo.kt
    init {
        val buahDao = BuahDatabase.getDataBase(application).buahDao()
        repository = BuahRepo(buahDao)
        readAllData = repository.readsAllData
    }

    // function tambahBuah pada viewModel dan disimpan di repository
    fun tambahBuah(buah: Buah){
        viewModelScope.launch (Dispatchers.IO){
            repository.tambahBuah(buah)
        }
    }

    // function updateBuah pada viewModel dan disimpan di repository
    fun updateBuah(buah: Buah) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBuah(buah)
        }
    }

    // function hapusBuah pada viewModel dan disimpan di repository
    fun hapusBuah(buah: Buah) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.hapusBuah(buah)
        }
    }

    // function hapusSemuaBuah pada viewModel dan disimpan di repository
    fun hapusSemuaBuah() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.hapusSemuaBuah()
        }
    }
}