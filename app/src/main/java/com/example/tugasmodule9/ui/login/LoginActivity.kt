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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // button
        val btnregister = findViewById<Button>(R.id.btnRegister)
        val btnlogin = findViewById<Button>(R.id.btnLogin)
        // textview
        val iptemail = findViewById<EditText>(R.id.etEmail)
        val iptpassword = findViewById<EditText>(R.id.etPassword)
        val btnlogingoogle = findViewById<SignInButton>(R.id.btnlogin_google)



        // firebase
        auth = Firebase.auth

        // aksi btn register
        btnregister.setOnClickListener{
            Intent(this, registerActivity::class.java).also{
                startActivity(it)
            }
        }

        // inisiasi buat kebutuhan tombol sigin with google
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.webclient_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        btnlogingoogle.setOnClickListener{
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, 1)
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

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d("Team1 : 1 : 1 : ", "step 1")
        if (requestCode == 1) {
            Log.d("Team1 : 1 : 1 : ", "step 2")
            // ambil data google account yang dipake user
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("Team 1 : 1 : ", "firebaseAuthWithGoogle: ${account.idToken}")
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.e("Team 1 : 1 : ", "error -> ${e.localizedMessage}")
                Toast.makeText(this, "Google Sign-In Error: ${e.statusCode}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        Log.d("Team1 : 1 : 2 : ", "step 1")
        Log.d("Team 1", "token -> $idToken")
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("Team1 : 1 : 2 : ", "step 2")
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Team 1 : 2 : ", "signInWithCredential:success")
                    val user = auth.currentUser
                    Toast.makeText(
                        this,
                        "Berhasil sign in ${user?.displayName}",
                        Toast.LENGTH_SHORT
                    )
                        .show()

                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Team 1 : 2 : ", "signInWithCredential:failure", task.exception)
                    Toast.makeText(this, "Gagal sign in", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Intent(this, MainActivity::class.java).also {inten ->
                        inten.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(inten)
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