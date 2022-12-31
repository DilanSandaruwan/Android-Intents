package com.example.projectintents

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectintents.databinding.ActivityProfileBinding
import com.example.projectintents.models.MyProfile

class ProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val bundle: Bundle = intent.getBundleExtra("ProfileBundle")!!

        val profileData = bundle.getParcelable<MyProfile>("Profile")
        binding.etName.text = profileData?.myName ?: "No Name"
        binding.etEmail.text = profileData?.myEmail ?: "No Name"
        binding.etAge.text = profileData?.myAge?.toString() ?: "No Name"
        binding.etPhone.text = profileData?.myPhoneNumber ?: "No Name"
        var img = profileData?.myImage
        if (img != null) {
            binding.imgProfile.setImageURI(Uri.parse(img))
        }

    }

}