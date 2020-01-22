package com.developersancho.util.extensions

import android.app.Activity
import android.widget.Toast

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


fun Activity.longToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}