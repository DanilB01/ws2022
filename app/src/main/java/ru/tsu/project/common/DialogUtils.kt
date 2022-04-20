package ru.tsu.project.common

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

object DialogUtils {

    fun createDialog(context: Context) = AlertDialog.Builder(context)
        .setTitle("Возникла ошибка. Попробуйте позже")
        .setPositiveButton("Ясно") { _, _ -> }
        .create()
        .show()
}