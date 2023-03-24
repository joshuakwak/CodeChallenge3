package com.heroapps.codechallenge3.common.util

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes

fun EditText.validateFullName(): Boolean {

    val fullNameText = text.toString()
    val regex = Regex("^[A-z]+\\s[A-z]+\$")

    return regex.matches(fullNameText)
}

fun EditText.validateEmail(): Boolean {

    val emailText = text.toString()
    val regex = Regex("^[a-zA-Z\\d.!#\$%&'*+/=?^_`{|}~-]+@[a-zA-Z\\d](?:[a-zA-Z\\d-]{0,61}[a-zA-Z\\d])?(?:\\.[a-zA-Z\\d](?:[a-zA-Z\\d-]{0,61}[a-zA-Z\\d])?)*\$")

    return regex.matches(emailText)
}

fun EditText.validateMobileNumber(): Boolean {

    val mobileNumberText = text.toString()
    val regex = Regex("(\\+?\\d{2}?\\s?\\d{3}\\s?\\d{3}\\s?\\d{4})|(0\\d{3}\\s?\\d{3}\\s?\\d{4})")

    return regex.matches(mobileNumberText)
}

fun EditText.validateAge(): Boolean {

    if (text.isNullOrEmpty()) return false

    val age = text.toString().toInt()

    return age >= 18
}

fun Context.showToastShort(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showToastShort(@StringRes resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
}

fun Context.showToastLong(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.showToastLong(@StringRes resId: Int) {
    Toast.makeText(this, resId, Toast.LENGTH_LONG).show()
}