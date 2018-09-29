package com.spqrta.common.delegates

import android.app.Activity
import android.content.DialogInterface
import android.support.v7.app.AlertDialog

class AlertDialogDelegate(
        activity: Activity,
        val title: String,
        val message: String = "",
        positiveButtonText: String = "OK",
        negativeButtonText: String? = null,
        positiveAction: (DialogInterface)-> Unit = {it.dismiss()},
        negativeAction: (DialogInterface)-> Unit = {it.dismiss()}
) {
    val dialog: AlertDialog

    init {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText) { dialog, _ ->
                    positiveAction(dialog)
                }

        if(negativeButtonText != null) builder.setNegativeButton(negativeButtonText) { dialog, _ ->
            negativeAction(dialog)
        }

        dialog = builder.create()
    }

    fun show(title:String = this.title, message: String = this.message) {
        dialog.setTitle(title)
        dialog.setMessage(message)
        dialog.show()
    }

    fun hide() {
        dialog.dismiss()
    }
}