package com.mantequilla.walletwizardapp.utils

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object CommonFunction {
    fun formatRupiah(value: Long): String {
        val rupiah = "Rp "
        val formatter = java.text.DecimalFormat("#,###")
        val formattedValue = formatter.format(value)
        return rupiah + formattedValue
    }
    fun formatDollar(value: Long): String {
        val dollar = "$"
        val formatter = java.text.DecimalFormat("#,###.00")
        val formattedValue = formatter.format(value)
        return dollar + formattedValue
    }

    fun getCurrentDate () : String {
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
    }

    fun convertDateFormat(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        return try {
            val date = inputFormat.parse(inputDate)
            outputFormat.format(date!!)
        } catch (e: ParseException) {
            "Error while converting date format $e"
        }
    }
}