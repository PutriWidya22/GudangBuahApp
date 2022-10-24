package com.putri.gudangbuahapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.putri.gudangbuahapp.databinding.ActivityMainBinding

// membuat class MainActivity
class MainActivity : AppCompatActivity() {

    // deklarasi variabel binding dengan nilai ActivityMainBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Judul Halaman
        title = "Login"

        // untuk menampilkan layout ActivityMain menggunakan metode binding.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // menambahkan ClickListener kepada btnLogin yang nantinya jika di klik
        // akan mengecek password pada cekPass().
        binding.btnLogin.setOnClickListener { cekPass() }
    }

    // di dalam cekPass() terdapat username dan password dengan metode binding.
    private fun cekPass() {
        val username = binding.editNama.text.toString()
        val password = binding.editPass.text.toString()

        // mengecek apakah username dan password sudah terisi, jika belum maka
        // akan menampilkan pop-up bahwa "Username dan Password harus diisi".
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username dan Password harus di isi", Toast.LENGTH_SHORT).show()
            return
        }

        // mengecek apakah username dan password telah terisi sesuai yang ditentukan.
        if(username == "admin" && password == "456"){

            // contoh intent eksplisit
            // jika form login yang diisi sudah sesuai maka saat klik button btnLogin
            // akan berpindah ke Halaman Home.
            val intent = Intent(this, Home::class.java)

            // memasukkan data-data yang telah terisi ke dalam "user".
            intent.putExtra("user", binding.editNama.text.toString())

            // menjalankan activity intent
            startActivity(intent)

            // MainActivity akan dihapus dari backStack jika telah finish.
            finish()

        // jika data username dan password yang diisi tidak sesuai maka akan menampilkan
        // pop-up bahwa "Username atau password salah"
        }else{
            Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
            return
        }
    }
}