package com.example.registration.ui
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.example.registration.API.APIClient
import com.example.registration.API.APIInterface
import com.example.registration.Constants
import com.example.registration.LoginProject
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import com.example.registration.viewModel.userViewModel

class MainActivity : AppCompatActivity() {
     lateinit var binding:ActivityMainBinding
     val userViewModel:userViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSpinner()
        btnRegister()
        redirectUser()
    }
    override fun onResume() {
        super.onResume()
        userViewModel.registrationLiveData.observe(this,{RegistrationResponse->
            if (!RegistrationResponse.studentId.isNullOrEmpty(){
                Toast.makeText(baseContext,"Registration successful",Toast.LENGTH_LONG).show()
                }
        })
            userViewModel.errorLiveData.observe(this,{ error->
                Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                binding.pbRegistration.visibility=View.GONE
            })
    }
    fun setupSpinner() {
        var nationalities= arrayOf("KENYAN","RWANDAN","SOUTH SUDANESE","UGANDAN")
        var nationalityadapter = ArrayAdapter(baseContext,android.R.layout.simple_spinner_item,nationalities)
        nationalityadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spNationality.adapter = nationalityadapter
    }

    fun btnRegister() {
        binding.btnLogin.setOnClickListener {
            var intent= Intent(baseContext, LoginProject::class.java)
            startActivity(intent)
        }
        var error = false
        binding.btnRegister.setOnClickListener {
            var name = binding.etName.text.toString()
            if (name.isEmpty()) {
                error = true
                binding.etName.setError("this field required")
            }
            var dob =binding.etDob.text.toString()
            if (dob.isEmpty()) {
                error = true
                binding.etDob.setError("this field required")
            }
            var nationality=""
            if (binding.spNationality.selectedItemPosition!=0){
                nationality = binding.spNationality.selectedItem.toString()
            }
            else{
                error = true
                Toast.makeText(baseContext,"select Nationality", Toast.LENGTH_LONG).show()
            }
            var phoneNumber = binding.etPhone.text.toString()
            if (phoneNumber.isEmpty()) {
                error = true
                binding.etPhone.setError("this field required")
            }
            var email = binding.etEmail.text.toString()
            if (email.isEmpty()) {
                error = true
                binding.etEmail.setError("this field required")
            }
            var password= binding.etPassword.text.toString()
            if (password.isEmpty()) {
                error = true
                binding.etPassword.setError("this field required")
            }
            if (!error){
                binding.pbRegistration.visibility=View.VISIBLE
                var lrgRequest= RegistrationRequest(
                    name = name,
                    phoneNumber = phoneNumber,
                    email = email,
                    dateofbirth = dob,
                    nationality = nationality.toUpperCase(),
                    password = password
                )
userViewModel.registerStudent(RegistrationRequest)
            }
        }
    }
    fun redirectUser(){
        var accessToken=sharedPrefs.getString(Constants.ACCESS_TOKEN,Constants.EMPTY_STRING)!!
        if(accessToken.isNotEmpty() && accessToken.isNotBlank()){
           startActivity(Intent(this,CoursesActivity::class.java))
        }
        else{
            startActivity(this,LoginProject::class.java)
        }
    }
    fun logout(){
        var editor=sharedPrefs.
                editor
    }
}