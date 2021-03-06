package com.example.registration.repositories

import android.media.session.MediaSession
import com.example.registration.api.ApiClient
import com.example.registration.api.ApiInterface
import com.example.registration.models.Course
import retrofit2.Response

class CourseRepository {
    var apiInterface = ApiClient.buildApiClient(ApiInterface::class.java)
    var retrofit= ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun  fetchCourses(accesToken:String): Response<List<Course>> =
        withContext(Dispatchers.IO){
            return@withContext retrofit.fetchCourses(accesToken)
        }
    suspend fun enrol(accessToken: String): Response<EnrolmentResponse> =
        withContext(Dispatchers.IO){
            var enrol = apiInterface.enrol(accessToken)
            return@withContext enrol
        }
}