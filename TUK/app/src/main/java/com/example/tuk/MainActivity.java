package com.example.tuk;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a list of courses
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Computer Science", "Computer Science Department"));
        courses.add(new Course("Electrical Engineering", "Engineering Department"));
        // Add more courses as needed

        // Populate the Spinner with course names
        Spinner spinnerCourses = findViewById(R.id.spinnerCourses);
        ArrayAdapter<Course> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourses.setAdapter(adapter);

        // Set a listener to handle course selection
        spinnerCourses.setOnItemSelectedListener(new CourseItemSelectedListener());
    }

    // Inner class to handle course selection
    private class CourseItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            // Retrieve the selected course
            Course selectedCourse = (Course) parentView.getSelectedItem();

            // Set values in the layout based on the selected course
            TextView textViewDepartmentValue = findViewById(R.id.textViewDepartmentValue);
            textViewDepartmentValue.setText(selectedCourse.getDepartment());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
            // Handle case when nothing is selected (if needed)
        }
    }
}
