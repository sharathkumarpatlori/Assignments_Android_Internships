package com.naman.snfsendriya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class UserPage : AppCompatActivity() {
    lateinit var txtName: TextView
    lateinit var txtEmail: TextView
    lateinit var txtPhone: TextView
    lateinit var txtGender: TextView
    lateinit var txtAddress: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page)
        txtName = findViewById(R.id.txtName)
        txtEmail = findViewById(R.id.txtEmail)
        txtPhone = findViewById(R.id.txtPhone)
        txtGender = findViewById(R.id.txtGender)
        txtAddress = findViewById(R.id.txtAddress)

        if (intent != null){
            val user = intent.extras!!
            txtName.text = user.getString("username")
            txtEmail.text = user.getString("useremail")
            txtPhone.text = user.getString("userphone")
            txtGender.text = user.getString("usergender")
            txtAddress.text = user.getString("useraddress")
        }
    }
}