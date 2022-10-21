package com.vikiwahyudi.deteksigempadantsunami.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.vikiwahyudi.deteksigempadantsunami.R
import com.vikiwahyudi.deteksigempadantsunami.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private lateinit var fAuth: FirebaseAuth
    private lateinit var fStore: FirebaseFirestore
    private var valid = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fAuth = FirebaseAuth.getInstance()
        fStore = FirebaseFirestore.getInstance()

        binding.apply {
            btnRegister.setOnClickListener {
                checkField(etNama)
                checkField(etEmail)
                checkField(etPassword)

                if (valid) {
                    fAuth.createUserWithEmailAndPassword(
                        etEmail.text.toString(),
                        etPassword.text.toString()
                    )

                        .addOnSuccessListener {
                            val nama = etNama.text.toString()
                            val user = Firebase.auth.currentUser
                            val profileUpdates = userProfileChangeRequest {
                                displayName = nama
                            }
                            user!!.updateProfile(profileUpdates)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(
                                            this@RegisterActivity,
                                            "Akun berhasil dibuat",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        val df = fStore.collection("Users").document(
                                            user.uid
                                        )
                                        val userInfo: MutableMap<String, Any> = HashMap()
                                        userInfo["userName"] = nama
                                        userInfo["userEmail"] = etEmail.text.toString()
                                        userInfo["isUser"] = "1"
                                        df.set(userInfo)
                                        startActivity(
                                            Intent(
                                                applicationContext,
                                                HomeActivity::class.java
                                            )
                                        )
                                        finish()
                                    }
                                }
                        }.addOnFailureListener {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Gagal membuat akun",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
            }
            btnLogin.setOnClickListener {
                startActivity(
                    Intent(
                        applicationContext,
                        LoginActivity::class.java
                    )
                )
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
}