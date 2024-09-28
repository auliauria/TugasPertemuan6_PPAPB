package com.example.tugaspertemuan6

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tugaspertemuan6.databinding.ActivityMainBinding
import com.example.tugaspertemuan6.databinding.DialogPemesananBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tujuan: Array<String>
    private lateinit var harga: Array<String>

    private var selectedDate = ""
    private var selectedTime = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tujuan = resources.getStringArray(R.array.tujuan)
        harga = resources.getStringArray(R.array.harga)

        with(binding) {
            val adapterTujuan = ArrayAdapter(
                this@MainActivity,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, tujuan
            )
            spinnerDestination.adapter = adapterTujuan // Update to match the Spinner ID

            ButtonDate.setOnClickListener {
                showDatePickerDialog() // Update to match EditText for date
            }

            ButtonTime.setOnClickListener {
                showTimePickerDialog() // Update to match EditText for time
            }

            registerButton.setOnClickListener {
                val username = name.text.toString() // Update to match EditText for name
                val tujuanDipilih = spinnerDestination.selectedItemPosition // Update to match Spinner for destination
                val hargaDipilih = harga[tujuanDipilih]

                if (selectedDate.isEmpty() || selectedTime.isEmpty()) {
                    Toast.makeText(this@MainActivity, "Pilih tanggal dan waktu!", Toast.LENGTH_SHORT).show()
                } else {
                    showConfirmationDialog(username, selectedTime, selectedDate, tujuan[tujuanDipilih], hargaDipilih)
                }
            }
        }
    }

    private fun showConfirmationDialog(nama: String, jam: String, tanggal: String, tujuan: String, harga: String) {
        val dialog = DialogPemesanan(this, nama, jam, tanggal, tujuan, harga)
        dialog.show(supportFragmentManager, "DialogPemesanan")
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            selectedDate = "$selectedDay-${selectedMonth + 1}-$selectedYear"
            binding.ButtonDate.setText(selectedDate) // Update to set text on EditText for date
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            binding.ButtonTime.setText(selectedTime) // Update to set text on EditText for time
        }, hour, minute, true)

        timePickerDialog.show()
    }
}
