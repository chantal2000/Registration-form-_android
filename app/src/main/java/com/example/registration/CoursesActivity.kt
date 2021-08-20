package com.example.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class CoursesActivity : AppCompatActivity() {
    lateinit var rvCourses:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)
    }
fun displayCourses(){
    var coursesList= listOf<Course>(
        Course("Android","AND 101","Mobile development is the best","John"),
        Course("JS","JS 102","javascript is the frontend ","Maina"),
        Course("Python","Py 103","Python is the backend","James"),
        Course("IOT","IOT 101","IOT is the best","Barre"),
        )
    rvCourses=findViewById(R.id.rvCourses)
    var coursesAdapter=CoursesAdapter(coursesList)
    rvCourses.layoutManager=LinearLayoutManager(baseContext)
    rvCourses.adapter=coursesAdapter
}

}

