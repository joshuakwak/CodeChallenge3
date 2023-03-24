package com.heroapps.codechallenge3.presentation.ui.main

import android.widget.EditText
import com.heroapps.codechallenge3.common.util.*
import com.heroapps.periodictablereviewer.common.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Calendar

class MainViewModel : BaseViewModel() {

    private val _calendar = MutableStateFlow(getCalendar18FromNow())
    val calendar = _calendar.asStateFlow()

    private val _isNameValid = MutableStateFlow(true)
    val isNameValid = _isNameValid.asStateFlow()

    private val _isEmailValid = MutableStateFlow(true)
    val isEmailValid = _isEmailValid.asStateFlow()

    private val _isMobileNumberValid = MutableStateFlow(true)
    val isMobileNumberValid = _isMobileNumberValid.asStateFlow()

    private val _isAgeValid = MutableStateFlow(true)
    val isAgeValid = _isAgeValid.asStateFlow()

    fun setCalendar(c: Calendar) {
        _calendar.value = c
    }

    fun submitDetails(
        name: EditText,
        email: EditText,
        mobileNumber: EditText,
        age: EditText
    ) {
        _isNameValid.value = name.validateFullName()
        _isEmailValid.value = email.validateEmail()
        _isMobileNumberValid.value = mobileNumber.validateMobileNumber()
        _isAgeValid.value = age.validateAge()
    }

    fun isFormValid(): Boolean {
        return isNameValid.value && isEmailValid.value && isMobileNumberValid.value && isAgeValid.value
    }


}