package com.spqrta.architecture_sandbox.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Task(val name: String): Parcelable {

    companion object {
        fun default() = Task("Task " + Random().nextInt(100))
    }
}
