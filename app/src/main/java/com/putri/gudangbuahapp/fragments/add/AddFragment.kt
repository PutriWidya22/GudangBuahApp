package com.putri.gudangbuahapp.fragments.add

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.putri.gudangbuahapp.Buah
import com.putri.gudangbuahapp.R
import com.putri.gudangbuahapp.buah.BuahViewModel
import kotlinx.android.synthetic.main.fragment_tambah_buah.*
import kotlinx.android.synthetic.main.fragment_tambah_buah.view.*

// membuat class AddFragment sebagau fragment ke "fragment_tambah_buah"
class AddFragment : Fragment() {

    // deklarasi variabel mBuahViewMode sebagai view model
    private lateinit var mBuahViewModel: BuahViewModel

    // mengembangkan layout fragment_tambah_buah
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tambah_buah, container, false)

        // view model yang tertuju pada file BuahViewModel.kt
        mBuahViewModel = ViewModelProvider(this)[BuahViewModel::class.java]

        // membuat fungsi setOnClickListener untuk button btnTambah yang digunakan untuk menambahkan
        // data buah.
        view.btnTambah.setOnClickListener {
            insertDataToDataBase()
        }

        return view
    }

    // membuat fungsi insert data untuk memasukkan data nama buah dan stok dengan tipe data string.
    @SuppressLint("CommitTransaction")
    private fun insertDataToDataBase() {
        val NamaBuah = namaBuah.text.toString()
        val Stok = stokBuah.text.toString()

        // jika nama buah dan stok kosong maka akan menampilkan pesan error "Mohon untuk mengisi nama
        // buah dan stok.
        if (NamaBuah.isEmpty() && Stok.isEmpty()) {
            namaBuah.error = "Mohon mengisi nama buah"
            stokBuah.error = "Mohon mengisi jumlah stok"

            // jika hanya nama buahnya saja yang kosong maka akan menampilkan pesan error "Mohon
        // mengisi nama buah" dan begitu juga sebaliknya.
        } else if (NamaBuah.isEmpty()) {
            namaBuah.error = "Mohon mengisi nama buah"
        } else if (Stok.isEmpty()) {
            stokBuah.error = "Mohon mengisi jumlah stok"
        } else {
            // membuat objek buah
            val buah = Buah(0, NamaBuah, Integer.parseInt(Stok))

            // menambahkan data pada database, jika berhasil maka akan menampilkan pesan pada text.
            mBuahViewModel.tambahBuah(buah)
            Toast.makeText(requireContext(), "Berhasil Menambahkan Data Buah", Toast.LENGTH_SHORT).show()

            // navigation untuk kembali
            findNavController().popBackStack(R.id.action_addFragment_to_listFragment, false)

            Log.e("Fragment", "${findNavController().popBackStack()}")
        }
    }

}