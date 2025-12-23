package com.pab.putraluragungtrans

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Seat(
    val id: Int,
    val seatNumber: String,
    val price: Int,
    var isSelected: Boolean = false,
    var isBooked: Boolean = false,
) : Parcelable