package com.example.tugaspertemuan6

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.tugaspertemuan6.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val nama = intent.getStringExtra("nama")
        val jam = intent.getStringExtra("jam")
        val tanggal = intent.getStringExtra("tanggal")
        val tujuan = intent.getStringExtra("tujuan")

        // Tampilkan data di TextView menggunakan binding
        binding.Nama.text = nama
        binding.Jam.text = jam
        binding.Tanggal.text = tanggal
        binding.Tujuan.text = tujuan
    }

}

