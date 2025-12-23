package com.pab.putraluragungtrans

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val nik: String,
    val address: String,
    val email: String,
    val password: String,
)