package com.putri.gudangbuahapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

// membuat class Awal dengan layout ke "fragment_awal"
class Awal : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_awal)

        // Menyiapkan actionbarNavController dan menambahkan fragmen data
        setupActionBarWithNavController(findNavController(R.id.fragment))


    }

    // Pindah kembali ke daftar fragmen dengan mengklik tombol panah kembali
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}