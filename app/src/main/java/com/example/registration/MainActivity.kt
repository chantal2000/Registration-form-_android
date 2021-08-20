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
        btnRegister()
        castViews()
    }
    fun castViews(){
        var nationality= arrayOf("select nationality","Kenyan","Rwandan","South Sudan","Uganda")
        val nationalityAdapter=ArrayAdapter(baseContext,android.R.layout)
    }
    fun btnRegister() {
        binding.btnRegister.setOnClickListener {
            var error=false
            var name=binding.etname.toString()
            if(name.isEmpty()){
                error=true
                
            }
            binding.pbRegistration.visibility=View.GONE

            var error=false
                var name = binding.text.toString()
                if (name.isEmpty()) {
                    binding.etname.setError("Name is required")
                }
                var dob = binding.etname.text.toString()
                var idNo = binding.etDob.text.toString()
                var nationality = binding.etNaationality.text.toString()
                var phone = binding.etPhone.text.toString()
                var email = binding.etEmail.text.toString()
            var password=binding.trPass.text.toString()
            if(!error){
                var regRequest=RegistrationRequest(name = name,
                phoneNumber = phone,email = email,dateofbirth = dob,nationality = nationality.toUpperCase(),password = password)
            }
            var retrofit=APIClient.buildApiClient(APIInterface::class.java)
            val request=retrofit.registerStudent(regRequest)
            request.enqueue(object :Call<RegistrationResponse>,t:Throwable){
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            })
                var student = Student(name, dob, idNo, nationality, phone, email)
                Toast.makeText(baseContext, student.toString(), Toast.LENGTH_LONG).show()
                val intent= Intent(baseContext,CoursesActivity::class.java)
                startActivity(intent)
            }
        }
    }
data class Student(
    var name: String,
    var dob: String,
    var idNo: String,
    var nationality: String,
    var phone: String,
    var email: String
)
