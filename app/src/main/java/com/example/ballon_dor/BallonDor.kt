package com.example.ballon_dor

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BallonDor(
    val name: String,
    val description: String,
    val photo: Int,
    val stats: String
) : Parcelable