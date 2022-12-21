package com.example.projectintents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectintents.databinding.ActivityRegisterInfoBinding

class RegisterInfoActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterInfoBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)
    }
}