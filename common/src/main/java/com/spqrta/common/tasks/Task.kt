package com.spqrta.common.tasks

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Task(val id: Int, val name: String, val description: String): Parcelable {

    companion object {
        fun default(): Task {
            val id = Random().nextInt(100)
            return Task(id, "Task $id", "Description $id")
        }
    }
}
