package com.example.projectintents

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.TooltipCompat
import com.example.projectintents.databinding.ActivityRegisterInfoBinding
import com.example.projectintents.models.MyProfile
import java.io.IOException
import java.util.function.IntConsumer


class RegisterInfoActivity : AppCompatActivity() {

    val SELECT_PICTURE = 1000

    lateinit var binding: ActivityRegisterInfoBinding
    var selectedImageUriToPass: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSave.setOnClickListener {
            if(checkValidity()){
                val myProfile = readyParcel()
                passProfile(myProfile)
            }
        }

        binding.imgProfile.setOnClickListener(View.OnClickListener {
            chooseProfileImage()
        })
    }

    var launchSomeActivity = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode
            == RESULT_OK
        ) {
            val data = result.data

            if (data != null
                && data.data != null
            ) {
                val selectedImageUri: Uri? = data.data
                selectedImageUriToPass = selectedImageUri!!

                var selectedImageBitmap: Bitmap? = null
                try {
                    selectedImageBitmap = MediaStore.Images.Media.getBitmap(
                        this.contentResolver,
                        selectedImageUri
                    )
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                binding.imgProfile.setImageBitmap(
                    selectedImageBitmap
                )
            }
        }
    }

    private fun chooseProfileImage() {
        val intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)

        launchSomeActivity.launch(intent)

    }

    private fun checkValidity():Boolean {
        if (binding.etName.text.toString().isEmpty()) {
            binding.etName.setError("Please enter full name here!")
        }
        if (binding.etEmail.text.toString().isBlank()) {
            binding.etEmail.setError("Please enter email here!")
        }
        if (binding.etAge.text.toString().isBlank()) {
            binding.etAge.setError("Please enter age here!")
        }
        if (binding.etPhone.text.toString().isBlank()) {
            binding.etPhone.setError("Please enter phone number here!")
        }else{
            return true
        }
        return false
    }

    private fun readyParcel(): MyProfile {
        val name = binding.etName.text?.toString() ?: "No Name"
        val age = binding.etAge.text?.toString()?.toInt() ?: 0
        val email = binding.etAge.text?.toString()?:"No Email"
        val phone = binding.etAge.text?.toString()?:"No Phone"
        val image = selectedImageUriToPass?.path ?: "No Image"

        Log.d("TAG", "readyParcel: name " + name)
        Log.d("TAG", "readyParcel: age " + age)
        Log.d("TAG", "readyParcel: email " + email)
        Log.d("TAG", "readyParcel: phone " + phone)
        Log.d("TAG", "readyParcel: image " + image)
        return MyProfile(name, age, email, phone, image)
    }

    private fun passProfile(myProfile: MyProfile) {
        val parcel = myProfile
        val intent = Intent(this, ProfileActivity::class.java)
        var bundle = Bundle()
        bundle.putParcelable("Profile", parcel)
        intent.putExtra("ProfileBundle", bundle)
        startActivity(intent)
    }
}