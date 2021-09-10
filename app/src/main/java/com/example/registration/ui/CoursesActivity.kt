
package com.example.registration.ui
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.registration.Constants
import com.example.registration.R
import com.example.registration.models.Course
class CoursesActivity : AppCompatActivity() {
    lateinit var binding: ActivityCourseBinding
    val courseViewModel: CourseViewModel by viewModels()
    lateinit var sharedPrefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefs= getSharedPreferences(Constants.REGISTRATION_PREFS, Context.MODE_PRIVATE)
        var accessToken = sharedPrefs.getString(Constants.ACCESS_TOKEN, Constants.EMPTY_STRING)!!
        if (accessToken.isNotEmpty())
            courseViewModel.getCourses(accessToken)
    }


    override fun onResume() {
        super.onResume()
        courseViewModel.courseLiveData.observe(this,{courses->
            var coursesList = listOf(
                Course("P101","Python","20","Backend","James Mwai"),
                Course("J102","JavaScript","80","Frontend","Purity Maina"),
            )
            var coursesAdapter = CoursesRvAdapter(coursesList)
            binding.rvCourses.layoutManager = LinearLayoutManager(baseContext)
            binding.rvCourses.adapter = coursesAdapter


            Toast.makeText(this, "fetched ${courses.size} courses", Toast.LENGTH_LONG).show()
        })
        courseViewModel.errorLiveData.observe(this,{ error->
            Toast.makeText(this,error,Toast.LENGTH_LONG).show()
        })
    }

}