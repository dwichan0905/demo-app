package com.vikiwahyudi.deteksigempadantsunami.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.vikiwahyudi.deteksigempadantsunami.R
import com.vikiwahyudi.deteksigempadantsunami.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var fAuth: FirebaseAuth
    private lateinit var fStore: FirebaseFirestore
    private var valid = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fAuth = FirebaseAuth.getInstance()
        fStore = FirebaseFirestore.getInstance()

        binding.apply {
            btnLogin.setOnClickListener {
                checkField(etEmail)
                checkField(etPassword)

                if (valid) {
                    fAuth.signInWithEmailAndPassword(
                        etEmail.text.toString(),
                        etPassword.text.toString()
                    )
                        .addOnSuccessListener { fauthResult ->
                            Toast.makeText(this@LoginActivity, "Sukses Login", Toast.LENGTH_SHORT)
                                .show()
                            checkUserAccessLevel(fauthResult.user!!.uid)
                        }.addOnFailureListener {
                            Toast.makeText(
                                this@LoginActivity,
                                "Password salah",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
            }

            btnRegister.setOnClickListener {
                startActivity(
                    Intent(
                        applicationContext,
                        RegisterActivity::class.java
                    )
                )
            }
        }
    }

    private fun checkUserAccessLevel(uid: String) {
        val df = fStore.collection("Users").document(uid)

        df.get().addOnSuccessListener { documentSnapshot ->
            Log.d("TAG", "onSuccess: " + documentSnapshot.data)


            if (documentSnapshot.getString("isUser") != null) {
                Toast.makeText(this@LoginActivity, "Selamat datang User", Toast.LENGTH_LONG )
                startActivity(Intent(applicationContext, HomeActivity::class.java))
            }
        }
    }

    fun checkField(textField: EditText?): Boolean {
        if (textField!!.text.toString().isEmpty()) {
            textField.error = "Error"
            valid = false
        } else {
            valid = true
        }
        return valid
    }

    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser != null) {
            checkUserAccessLevel(FirebaseAuth.getInstance().currentUser!!.uid)
        }
    }
}