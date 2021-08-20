package com.example.registration
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.registration.API.APIClient
import com.example.registration.API.APIInterface
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
class MainActivity : AppCompatActivity() {
     lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSpinner()
        btnRegister()

    }
    fun setupSpinner() {
        var nationalities= arrayOf("KENYAN","RWANDAN","SOUTH SUDANESE","UGANDAN")
        var nationalityadapter = ArrayAdapter(baseContext,android.R.layout.simple_spinner_item,nationalities)
        nationalityadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spNationality.adapter = nationalityadapter
    }

    fun btnRegister() {
        binding.btnLogin.setOnClickListener {
            var intent= Intent(baseContext, LoginActivity::class.java)
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
                    PhoneNumber = phoneNumber,
                    email = email,
                    dateOfBirth = dob,
                    nationality = nationality,
                    password = password
                )

                var retrofit= ApiClient.buildApiClient(ApiInterface::class.java)
                var request= retrofit.registerStudent(lrgRequest)
                request.enqueue(object : Callback<RegistrationResponse?> {
                    override fun onResponse(
                        call: Call<RegistrationResponse?>,
                        response: Response<RegistrationResponse?>
                    ) {
                        binding.pbRegistration.visibility=View.GONE
                        if (response.isSuccessful){
                            Toast.makeText(baseContext,"Registration successful", Toast.LENGTH_LONG).show()
                        }
                        else{
                            Toast.makeText(baseContext, response.errorBody()?.string(), Toast.LENGTH_LONG).show()
                        }
                    }
                    override fun onFailure(call: Call<RegistrationResponse?>, t: Throwable) {
                        binding.pbRegistration.visibility=View.GONE
                        Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }
}