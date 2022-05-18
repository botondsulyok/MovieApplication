package com.example.movieapplication.ui.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}