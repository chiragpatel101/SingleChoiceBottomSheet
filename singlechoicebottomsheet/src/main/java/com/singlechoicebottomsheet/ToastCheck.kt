package com.singlechoicebottomsheet

import android.content.Context
import android.widget.Toast

object ToastCheck {
    fun showToast(message : String, context : Context){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}