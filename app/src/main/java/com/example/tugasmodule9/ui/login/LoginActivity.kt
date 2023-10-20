package com.example.tugasmodule9.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tugasmodule9.R
import com.example.tugasmodule9.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // button
        val btnregister = findViewById<Button>(R.id.btnRegister)
        val btnlogin = findViewById<Button>(R.id.btnLogin)
        // textview
        val iptemail = findViewById<EditText>(R.id.etEmail)
        val iptpassword = findViewById<EditText>(R.id.etPassword)



        // firebase
        auth = FirebaseAuth.getInstance()

        // aksi btn register
        btnregister.setOnClickListener{
            Intent(this, registerActivity::class.java).also{
                startActivity(it)
            }
        }

        // aksi btn login
        btnlogin.setOnClickListener{
            val email = iptemail.text.toString().trim()
            val password = iptpassword.text.toString().trim()

            if(email.isEmpty()){
                iptemail.error = "Email harus diisi"
                iptemail.requestFocus()
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                iptemail.error = "Email tidak valid"
                iptemail.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty() || password.length < 5){
                iptpassword.error = "Password harus lebih dari 5 angka"
                iptpassword.requestFocus()
                return@setOnClickListener
            }
            loginUser(email, password)
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Intent(this, MainActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                } else {
                    Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }

    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
            Intent(this, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}