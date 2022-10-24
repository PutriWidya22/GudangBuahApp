package com.putri.gudangbuahapp.fragments.list

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.putri.gudangbuahapp.Buah
import com.putri.gudangbuahapp.R
import com.putri.gudangbuahapp.buah.BuahViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

// membuat ListFragment sebagai fragment pada fragment_list
class ListFragment : Fragment() {

    // deklarasi variabel buahViewModel
    private lateinit var buahViewModel: BuahViewModel

    // mengembangkan layout fragment_tambah_buah
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // RecyclerView yang berisi list buah menggunakan adapter
        val listAdapter = activity?.let { ListAdapter(it) }
        val recyclerView = view.listBuahLL
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // bagian view model yang tertuju pada file BuahViewModel.kt
        buahViewModel = ViewModelProvider(this)[BuahViewModel::class.java]
        buahViewModel.readAllData.observe(viewLifecycleOwner, Observer { buah ->
            listAdapter?.setData(buah)
        })

        // membuat fungsi setOnClickListener untuk button btnTambahData yang digunakan untuk menambah
        // data buah pada fragment
        view.btnTambahData.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return view
    }

    // berisi notifikasi saat mengklik icon hapus, jika memilih yes dan data berhasil dihapus maka
    // akan menampilkan popup. jika no maka akan kembali.
    // dan terdapat popup apakah yakin akan menghapus data?
    fun notifHapusBuah(buah: Buah, activity: Activity) {
        println("Name: ${buah.NamaBuah}")
        buahViewModel = BuahViewModel(application = Application())
        val builder = AlertDialog.Builder(activity)
        builder.setPositiveButton("Yes") { _, _ ->
            buahViewModel.hapusBuah(buah)
            Toast.makeText(
                activity,
                "${buah.NamaBuah} Berhasil Dihapus",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No") { _, _ ->
        }
        builder.setIcon(R.drawable.ic_warning)
        builder.setTitle("Hapus Buah ${buah.NamaBuah}")
        builder.setMessage("Apakah kamu yakin akan menghapus buah ${buah.NamaBuah}?")
        builder.create().show()
    }
}