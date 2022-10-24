package com.putri.gudangbuahapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

// membuat class Home dengan layout ke "activity_home"
class Home : AppCompatActivity(), View.OnClickListener {

    // deklarasi  variabel aplikasi, tambah yaitu sebagai button.
    private lateinit var aplikasi: Button
    private lateinit var tambah: Button

    // memuat layout activity_home
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // judul halaman
        title = "Home"

        // variabel aplikasi memuat button dengan id "btnInfromasiAplikasi" yang berada
        // di activity_home
        aplikasi = findViewById(R.id.btnInformasiAplikasi)
        aplikasi.setOnClickListener(this)

        // variabel tambah memuat button dengan id "btnTambahBuah" yang berada di activity_home
        tambah = findViewById(R.id.btnTambahBuah)
        tambah.setOnClickListener(this)
    }

    // membuat fungsi onClick dan intent yang nantinya jika di klik maka akan berpindah ke halaman
    // InformasiAplikasi, lalu disusul dengan perintah startActivity yang artinya memulai activity
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnInformasiAplikasi -> {
                val intentInformasiAplikasi = Intent(this@Home, InformasiAplikasi::class.java)
                startActivity(intentInformasiAplikasi)
            }
        }

        // ketika yang di klik adalah button "btnTambahBuah" maka akan beralih ke halaman Awal
        // lalu disusul dengan perintah startActivity yang artinya memulai activity
        when (v.id) {
            R.id.btnTambahBuah -> {
                val intentTambah = Intent(this@Home, Awal::class.java)
                startActivity(intentTambah)
            }
        }
    }

}