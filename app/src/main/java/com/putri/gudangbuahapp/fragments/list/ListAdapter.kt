package com.putri.gudangbuahapp.fragments.list

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.putri.gudangbuahapp.Buah
import com.putri.gudangbuahapp.R
import com.putri.gudangbuahapp.fragments.list.ListFragment
import kotlinx.android.synthetic.main.data_buah.view.*
import java.util.Collections.emptyList

// membuat class ListAdapter
class ListAdapter(activity: Activity) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    // deklarasi variabel buahList, ListFragment, dan myActivity
    private var buahList = emptyList<Buah>()
    private var listFragment: ListFragment = ListFragment()
    private var myActivity: Activity = activity

    // class myViewHolder yang menggunakan RecyclerView
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.data_buah, parent, false)
        )
    }

    // buah list yang memuat id buah, nama buah, dan stok pada layout data_buah.xml
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = buahList[position]
        holder.itemView.idBuah.text = currentItem.id.toString()
        holder.itemView.textNamaBuah.text = currentItem.NamaBuah
        holder.itemView.textStokBuah.text = currentItem.Stok.toString()

        // membuat fungsi setOnClickListener untuk icon ubah yang digunakan untuk mengubah
        // data buah
        holder.itemView.iconUbah.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
        // membuat fungsi setOnClickListener untuk dataBuahLL
        holder.itemView.dataBuahLL.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

        // membuat fungsi setOnClickListener untuk icon hapus yang digunakan untuk menghapus
        // data buah
        holder.itemView.iconHapus.setOnClickListener {
            myActivity.let { it1 -> listFragment.notifHapusBuah(currentItem, it1) }
            //Log.e("DeleteClick, ")
        }
    }

    // fungsi getItemCount dengan tipe data Int
    override fun getItemCount(): Int {
        return buahList.size
    }

    // fungsi setData list buah
    @SuppressLint("NotifyDataSetChanged")
    fun setData(buah: List<Buah>) {
        this.buahList = buah
        notifyDataSetChanged()
    }
}