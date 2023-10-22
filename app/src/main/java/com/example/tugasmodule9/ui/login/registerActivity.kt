package com.example.tugasmodule9.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tugasmodule9.R
import com.example.tugasmodule9.ui.MainActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics

class registerActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // button
        val btnlogin = findViewById<Button>(R.id.btnRegister)
        val btnregister = findViewById<Button>(R.id.btnLogin)
        // textview
        val iptemail = findViewById<EditText>(R.id.etEmail)
        val iptpassword1 = findViewById<EditText>(R.id.etPassword1)
        val iptpassword2 = findViewById<EditText>(R.id.etPassword2)

        // firebase crasyanalytic
        FirebaseApp.initializeApp(this)
        FirebaseAnalytics.getInstance(this)
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
        // firebase
        auth = FirebaseAuth.getInstance()
        btnregister.setOnClickListener{
            val email = iptemail.text.toString().trim()
            val password1 = iptpassword1.text.toString().trim()
            val password2 = iptpassword2.text.toString().trim()

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
            if(password1.isEmpty() || password1.length < 5){
                iptpassword1.error = "Password harus lebih dari 5 angka"
                iptpassword1.requestFocus()
                return@setOnClickListener
            }
            if(password2.isEmpty() || password2.length < 5){
                iptpassword2.error = "Konfirmasi password harus lebih dari 5 angka"
                iptpassword2.requestFocus()
                return@setOnClickListener
            }

            Log.d("password1", "password1 : ${password1}")
            Log.d("password2", "password2 : ${password2}")

            if(password1 != password2){
                iptpassword2.error = "Konfirmasi password tidak valid"
                iptpassword2.requestFocus()
                return@setOnClickListener
            }

            registerakun(email, password1)

        }

        // aksi login
        btnlogin.setOnClickListener{
            finish()
        }

    }

    private fun registerakun(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if(it.isComplete){
                    Intent(this, MainActivity::class.java).also{
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                }else{
                    Toast.makeText(this, "Registrasi Gagal", Toast.LENGTH_SHORT).show()
                    Log.d("register", "error : ${it.exception}")

                    // Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }

    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
            Intent(this, LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}