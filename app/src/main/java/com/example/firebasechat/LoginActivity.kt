package com.example.firebasechat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            val userEmail =  userEmailInput.text.toString()
            val userPassword = userPasswordInput.text.toString()

            val auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener {

            }
        }
    }
}
