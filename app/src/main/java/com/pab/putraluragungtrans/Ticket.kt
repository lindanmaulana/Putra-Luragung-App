package com.pab.putraluragungtrans

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ticket(
    val busName: String,
    val departureDate: String,

    val originCity: String,
    val originTerminal: String,
    val departureTime: String,

    val destinationCity: String,
    val destinationTerminal: String,
    val arrivalTime: String,
) : Parcelable