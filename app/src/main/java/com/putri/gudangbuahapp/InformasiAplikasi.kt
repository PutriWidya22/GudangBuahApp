package com.putri.gudangbuahapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// membuat class InformasiAplikasi dengan layout ke "activity_informasi_aplikasi"
class InformasiAplikasi : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informasi_aplikasi)

        // judul halaman
        title = "Informasi Aplikasi"

    }
}