package com.example.firebasetwo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.email
import kotlinx.android.synthetic.main.activity_register.password
import java.lang.reflect.Member

class Register : AppCompatActivity() {
    var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        if (auth!!.currentUser != null) {
            val it = Intent(this, Member::class.java)
            startActivity(it)
            finish()
        }
        regis.setOnClickListener {
            val email = email.text.toString().trim()
            val pass = password.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "กรุณากรอก Email", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (pass.isEmpty()) {
                Toast.makeText(this, "กรุณากรอกPassword", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            auth!!.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    if (pass.length < 8) {
                        password.error = "กรอกรหัสผ่านให้มากกว่า 8 ตัวอักษร"
                    } else {
                        Toast.makeText(
                            this,
                            " Login ล้มเหลว เนื่องจาก : " + task.exception!!.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    email.setText("")
                    password.setText("")
                } else {
                    Toast.makeText(this, " Login Success! ", Toast.LENGTH_LONG).show()
                    val it = Intent(this,Member::class.java)
                    startActivity(it)
                    finish()
                }
            }
        }
        btnLogin2.setOnClickListener {
            val it = Intent(this, Member::class.java)
            startActivity(it)
        }
    }
}