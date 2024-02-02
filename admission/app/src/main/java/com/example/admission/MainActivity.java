package com.example.admission;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    private EditText courseNameInput;
    private Spinner departmentSpinner;
    private Button applyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        courseNameInput = findViewById(R.id.course_name_input);
        departmentSpinner = findViewById(R.id.department_spinner);
        applyButton = findViewById(R.id.apply_button);

        // Populate the department spinner with department names
        ArrayAdapter<String> departmentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Department.getDepartmentNames());
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentSpinner.setAdapter(departmentAdapter);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courseName = courseNameInput.getText().toString();
                String departmentName = departmentSpinner.getSelectedItem().toString();

                Department department = Department.getDepartmentByName(departmentName);
                Course course = new Course(courseName, department);

                // Submit the application
                submitApplication(course);
            }
        });
    }

    private void submitApplication(Course course) {
        // Implement the logic to submit the application to the university
        Toast.makeText(this, "Application submitted for course: " + course.getName(), Toast.LENGTH_SHORT).show();
    }
}
