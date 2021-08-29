
package com.example.registration.ui
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.registration.R
import com.example.registration.models.Course

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