package com.memksim.vkussovetapp.model

import android.graphics.Bitmap
import android.os.Parcelable
import com.squareup.picasso.RequestCreator
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

const val EXTRA_TAG = "PARSED_MENU"
@Parcelize
data class ParsedMenu (
    val menuID: String,
    val image: String,
    val name: String,
    val subMenuCount: String
): Parcelable