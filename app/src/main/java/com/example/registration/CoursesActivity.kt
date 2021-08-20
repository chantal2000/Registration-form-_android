
package com.example.registration
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.registration.api.ApiClient
import com.example.registration.api.ApiInterface
import com.example.registration.databinding.ActivityMainBinding
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoursesActivity : AppCompatActivity() {
    lateinit var rvCourses: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        displayCourses()
    }
    fun displayCourses(){
        var coursesList = listOf(
            Course("Python","py 101","backend development","James Mwai"),
            Course("Javascript","Js 201","Frontend development","Purity Maina"),
            Course("Kotlin","Kt 101","Mobile develpment development","John Owuoe"),
        )
        rvCourses = findViewById(R.id.rvCourses)
        var coursesAdapter = CoursesRvAdapter(coursesList)
        rvCourses.layoutManager = LinearLayoutManager(baseContext)
        rvCourses.adapter = coursesAdapter
    }
}