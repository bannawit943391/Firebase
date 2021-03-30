package com.example.firehotbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clear.setOnClickListener {
            Ename.setText("")
            Esurname.setText("")
        }
        send.setOnClickListener {
            val name =Ename.text.toString()
            val surname = Esurname.text.toString()

            val fire = FirebaseDatabase.getInstance()
            val ref = fire.getReference("Employee")
            val id:String? = ref.push().key

            val Employee =Employee(id.toString(),name,surname)

            ref.child(id.toString()).setValue(Employee).addOnCanceledListener {
                Toast.makeText(applicationContext,"Complete", Toast.LENGTH_LONG).show()

            }
            Ename.setText("")
            Esurname.setText("")
        }
    }
}