package com.pab.putraluragungtrans

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bus(
    val name: String,
    val route: String,
    val price: String,
    val ImageUrl: Int
) : Parcelable