package com.heroapps.codechallenge3.presentation.ui.main

import android.app.DatePickerDialog
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.heroapps.codechallenge3.R
import com.heroapps.codechallenge3.common.util.*
import com.heroapps.codechallenge3.databinding.ActivityMainBinding
import com.heroapps.periodictablereviewer.common.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel,ActivityMainBinding>() {

    override val layoutId = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    override fun initViews() {
        super.initViews()

        binding.buttonSubmit.setOnClickListener {
            with (binding) {
                this@MainActivity.viewModel.submitDetails(
                    editTextFullName,
                    editTextEmail,
                    editTextMobileNumber,
                    editTextAge
                )

                if (this@MainActivity.viewModel.isFormValid()) {
                    showToastShort("Form is Valid")
                } else {
                    showToastShort("Form is Invalid")
                }
            }
        }

        binding.editTextDateOfBirth.setOnClickListener {

            val c = viewModel.calendar.value

            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, y, m, d ->
                    c.set(y,m,d)
                    binding.editTextDateOfBirth.setText(formatDate(c))
                    binding.editTextAge.setText(getAge(c).toString())
                    viewModel.setCalendar(c)
                },
                year,
                month,
                day
            )

            c.set(year,month,day)
            datePickerDialog.datePicker.maxDate = Calendar.getInstance().timeInMillis

            datePickerDialog.show()
        }
    }

    override fun subscribe() {
        super.subscribe()

        with (binding) {

            this@MainActivity.viewModel.isNameValid.collectLatestData(lifecycleScope) { result ->
                if (!result) {
                    textInputLayoutFullName.error = "Name is not valid."
                } else {
                    textInputLayoutFullName.error = null
                    textInputLayoutFullName.isErrorEnabled = false
                }
            }

            this@MainActivity.viewModel.isEmailValid.collectLatestData(lifecycleScope) { result ->
                if (!result) {
                    textInputLayoutEmail.error = "Email is not valid."
                } else {
                    textInputLayoutEmail.error = null
                    textInputLayoutEmail.isErrorEnabled = false
                }
            }

            this@MainActivity.viewModel.isMobileNumberValid.collectLatestData(lifecycleScope) { result ->
                if (!result) {
                    textInputLayoutMobileNumber.error = "Mobile Number is not valid."
                } else {
                    textInputLayoutMobileNumber.error = null
                    textInputLayoutMobileNumber.isErrorEnabled = false
                }
            }

            this@MainActivity.viewModel.isAgeValid.collectLatestData(lifecycleScope) { result ->
                if (!result) {
                    textInputLayoutAge.error = "Age is not valid. Must be 18 above."
                } else {
                    textInputLayoutAge.error = null
                    textInputLayoutAge.isErrorEnabled = false
                }
            }
        }
    }
}