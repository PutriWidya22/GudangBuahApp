package com.putri.gudangbuahapp

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

// deklarasi entitas, menamai tabel dengan nama "table_buah"
// variabelnya ada id dengan tipe data Int, NamaBuah dengan tipe data String, dan Stok dengan tipe
// data Int.
@Parcelize
@Entity(tableName = "table_buah")
class Buah(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val NamaBuah: String,
    val Stok: Int
): Parcelable