package com.example.registration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.registration.models.Course


class CoursesAdapter(var courseList:List<Course>):RecyclerView.Adapter<CourseViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        var itemView=LayoutInflater.from(parent.context)
            .inflate(R.layout.course_list_item,parent,false)
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        var currentCourse=courseList.get(position)
        holder.tvCoursename.text=currentCourse.courseName
        holder.tvDesc.text=currentCourse.description
        holder.tvCode.text=currentCourse.courseCode
        holder.tvInstructor.text=currentCourse.instructor

    }

    override fun getItemCount(): Int {
        return courseList.size
    }
}

class CourseViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    var tvCoursename=itemView.findViewById<TextView>(R.id.tvCoursename)
    var tvDesc=itemView.findViewById<TextView>(R.id.tvDesc)
    var tvCode=itemView.findViewById<TextView>(R.id.tvCode)
    var tvInstructor=itemView.findViewById<TextView>(R.id.tvInstructor)


}