package com.example.firebasechat

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI
import java.util.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)
        val auth = FirebaseAuth.getInstance()

        registerButton.setOnClickListener {
            val userEmail = userEmailInput.text.toString()
            val userPassword = userPasswordInput.text.toString()
            if(userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "Please enter Email & Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener {
                if (!it.isSuccessful) {
                    return@addOnCompleteListener
                } else {
                    Log.d("Main", "Successful Created User with uid: ${it.result!!.user.uid}")
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }

        }

        previousAccountTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        selectPhoto_Button_Register.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type="image/*"
            startActivityForResult(intent, 0)
        }


    }
    private var selectedPhotoUri : Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){
            selectedPhotoUri = data.data
            val bitMap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            val bitMapDrawable = BitmapDrawable(bitMap)
            selectPhoto_Button_Register.setBackgroundDrawable(bitMapDrawable)
        }
    }

    private fun uploadImageToFireBase() {
        val filename = UUID.randomUUID().toString()

    }
}
