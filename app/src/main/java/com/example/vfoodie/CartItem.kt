package com.example.vfoodie

import android.os.Parcel
import android.os.Parcelable

data class CartItem(
    val name: String,
    val price: Int,
    var quantity: Int,
    val imageResId: Int // Tambahkan properti ini untuk gambar lokal
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt() // Tambahkan pembacaan gambar dari parcel
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(price)
        parcel.writeInt(quantity)
        parcel.writeInt(imageResId) // Tulis gambar ke parcel
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CartItem> {
        override fun createFromParcel(parcel: Parcel): CartItem {
            return CartItem(parcel)
        }

        override fun newArray(size: Int): Array<CartItem?> {
            return arrayOfNulls(size)
        }
    }
}
