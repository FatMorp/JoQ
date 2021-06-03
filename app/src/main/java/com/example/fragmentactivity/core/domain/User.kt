package com.example.fragmentactivity.core.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
        val id: String,
        val name: String,
        val city: String,
        val photoUrl: String
) : Parcelable
