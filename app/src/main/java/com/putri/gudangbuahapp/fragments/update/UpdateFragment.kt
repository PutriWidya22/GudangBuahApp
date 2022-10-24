package com.putri.gudangbuahapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.putri.gudangbuahapp.Buah
import com.putri.gudangbuahapp.R
import com.putri.gudangbuahapp.buah.BuahViewModel
import kotlinx.android.synthetic.main.fragment_ubah_buah.*
import kotlinx.android.synthetic.main.fragment_ubah_buah.view.*
import kotlinx.android.synthetic.main.fragment_tambah_buah.*
import kotlinx.android.synthetic.main.fragment_tambah_buah.view.*

// membuat class UpdateFragment sebagai fragment ke file fragment_ubah_buah
class UpdateFragment : Fragment() {

    // deklarasi variabel args dari navArgs
    private val args by navArgs<UpdateFragmentArgs>()

    // deklarasi variabel mBuahViewModel sebagai view model
    private lateinit var mBuahViewModel: BuahViewModel

    // mengembangkan layout pada fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ubah_buah, container, false)

        // view model yang tertuju pada file BuahViewModel.kt
        mBuahViewModel = ViewModelProvider(this)[BuahViewModel::class.java]

        // untuk mengambil data yang telah dirubah
        view.namaBuahUbah.setText(args.currentBuah.NamaBuah)
        view.stokBuahUbah.setText(args.currentBuah.Stok.toString())

        // membuat fungsi setOnClickListener untuk button btnUbah yang digunakan untuk mengubah
        // data buah.
        view.btnUbah.setOnClickListener {
            updateItem()
        }


        setHasOptionsMenu(true)

        return view
    }

    // membuat fungsi updateItem untuk memasukkan data nama buah dan stok dengan tipe data string.
    private fun updateItem() {
        val NamaBuah = namaBuahUbah.text.toString()
        val Stok = stokBuahUbah.text.toString()

        // jika nama buah dan stok kosong maka akan menampilkan pesan error "Mohon untuk mengisi nama
        // buah dan stok.
        if (NamaBuah.isEmpty() && Stok.isEmpty()) {
            namaBuahUbah.error = "Mohon mengisi nama buah"
            stokBuahUbah.error = "Mohon mengisi jumlah stok"
            //Create user object

            // jika hanya nama buahnya saja yang kosong maka akan menampilkan pesan error "Mohon
            // mengisi nama buah" dan begitu juga sebaliknya.
        } else if (NamaBuah.isEmpty()) {
            namaBuahUbah.error = "Mohon mengisi nama buah"
        } else if (Stok.isEmpty()) {
            stokBuahUbah.error = "Mohon mengisi jumlah stok"
        } else {

            // membuat objek buah
            val updateBuah = Buah(args.currentBuah.id, NamaBuah, Integer.parseInt(Stok))

            // menambahkan data pada database, jika berhasil maka akan menampilkan pesan pada text.
            mBuahViewModel.updateBuah(updateBuah)
            Toast.makeText(requireContext(), "Berhasil Mengubah Data Buah", Toast.LENGTH_SHORT).show()

            // navigation untuk kembali
            findNavController().popBackStack(R.id.action_updateFragment_to_listFragment, false)
            Log.e("Fragment", "${findNavController().popBackStack()}")
        }
    }

    // berisi menu delete
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    // berisi icon hapus untuk menghapus data buah
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.icon_hapus){
            notifHapusBuah()
        }
        return super.onOptionsItemSelected(item)
    }

    // berisi notifikasi saat mengklik icon hapus, jika memilih yes dan data berhasil dihapus maka
    // akan menampilkan popup. jika no maka akan kembali.
    // dan terdapat popup apakah yakin akan menghapus data?
    private fun notifHapusBuah() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mBuahViewModel.hapusBuah(args.currentBuah)
            Toast.makeText(requireContext(),
                "${args.currentBuah.NamaBuah} Berhasil Dihapus!",
                Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
        builder.setNegativeButton("No") {_, _, ->}
        builder.setIcon(R.drawable.ic_warning)
        builder.setTitle("Hapus buah ${args.currentBuah.NamaBuah}")
        builder.setMessage("Apakah kamu yakin akan menghapus buah ${args.currentBuah.NamaBuah}?")
        builder.create().show()
    }
}