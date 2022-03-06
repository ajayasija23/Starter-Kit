package com.app.starterkit.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

fun String.showDialog(mContext: Context){
    val alertDialog=AlertDialog.Builder(mContext)
    alertDialog.setMessage(this)
    alertDialog.setPositiveButton("Ok",object :DialogInterface.OnClickListener{
        override fun onClick(p0: DialogInterface?, p1: Int) {
            alertDialog.create().dismiss()
        }
    })
    alertDialog.show()
}