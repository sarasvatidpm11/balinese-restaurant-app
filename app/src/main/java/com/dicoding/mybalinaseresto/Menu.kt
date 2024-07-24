package com.dicoding.mybalinaseresto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Menu(
    val name: String,
    val price: String,
    val description: String,
    val photo: Int
) : Parcelable
