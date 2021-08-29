package com.example.registration.api
import com.example.registration.models.LoginRequest
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/students/register")
    fun registerStudent(@Body registrationRequest: RegistrationRequest): Call<RegistrationResponse>

    @POST("/students/login")
    suspend fun loginStudent(@Body registrationRequest: LoginRequest):Response<RegistrationResponse>
}