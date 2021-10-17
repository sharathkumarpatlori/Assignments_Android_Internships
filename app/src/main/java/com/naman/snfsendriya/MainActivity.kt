package com.naman.snfsendriya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*


class MainActivity : AppCompatActivity() {

    lateinit var userNameInput: EditText
    lateinit var userEmailInput: EditText
    lateinit var userPhoneInput: EditText
    lateinit var genderDropdown: Spinner
    lateinit var userAddressInput: EditText
    lateinit var btnRegister: Button

    lateinit var userName: String
    lateinit var userGender: String
    lateinit var userEmail: String
    lateinit var userPhone: String
    lateinit var userAddress: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userNameInput = findViewById(R.id.etName)
        userEmailInput = findViewById(R.id.etEmail)
        userPhoneInput = findViewById(R.id.etPhone)
        genderDropdown = findViewById(R.id.spin_gender)
        userAddressInput = findViewById(R.id.etAddress)
        btnRegister = findViewById(R.id.btnRegister)

        val gender_options_array: Array<String> = arrayOf("Male","Female","other")

        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,gender_options_array)
        genderDropdown.adapter = adapter
        genderDropdown.onItemSelectedListener = object :
        AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                userGender = gender_options_array[p2]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(this@MainActivity,
                    "Please select gender", Toast.LENGTH_SHORT).show()
            }
        }
        btnRegister.setOnClickListener {
            userName = userNameInput.text.toString()
            userEmail = userEmailInput.text.toString()
            userPhone = userPhoneInput.text.toString()
            userAddress = userAddressInput.text.toString()

            val isChecked = checkInputs()
            if (isChecked) {
                val userDetails = Bundle()
                userDetails.putString("username",userName)
                userDetails.putString("useremail",userEmail)
                userDetails.putString("userphone",userPhone)
                userDetails.putString("usergender",userGender)
                userDetails.putString("useraddress",userAddress)
                val intent = Intent(this,UserPage::class.java)
                intent.putExtras(userDetails)
                startActivity(intent)
            }



        }
    }

    private fun checkInputs(): Boolean {
        when{
            userName.isEmpty() -> {
                userNameInput.requestFocus()
                userNameInput.error = "Enter Your Name"
                return false
            }
            (userEmail.isEmpty() ||  !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) -> {
                userEmailInput.requestFocus()
                userEmailInput.error = "Enter valid Email"
                return false
            }
            userPhone.isEmpty() -> {
                userPhoneInput.requestFocus()
                userPhoneInput.error = "Enter 10-digit Phone number"
                return false
            }
            userGender.isEmpty() ->  {
                return false
            }
            userAddress.isEmpty() -> {
                userAddressInput.requestFocus()
                userAddressInput.error = "Enter your Address"
                return false
            }
        }

        return true
    }


}