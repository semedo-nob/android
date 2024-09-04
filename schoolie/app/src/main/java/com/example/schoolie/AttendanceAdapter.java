package com.example.schoolie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {
    private List<Attendance> attendanceList;
    private List<Attendance> originalList;
    private Context context;

    public AttendanceAdapter(List<Attendance> attendanceList, Context context) {
        this.attendanceList = attendanceList;
        this.originalList = new ArrayList<>(attendanceList);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_attendance, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Attendance attendance = attendanceList.get(position);
        holder.dateTextView.setText(attendance.getDate());
        holder.statusTextView.setText(attendance.getStatus());
    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    public void filter(String status) {
        attendanceList.clear();
        if (status.equals("All")) {
            attendanceList.addAll(originalList);
        } else {
            for (Attendance attendance : originalList) {
                if (attendance.getStatus().equals(status)) {
                    attendanceList.add(attendance);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void sort() {
        Collections.sort(attendanceList, new Comparator<Attendance>() {
            @Override
            public int compare(Attendance a1, Attendance a2) {
                return a1.getDate().compareTo(a2.getDate());
            }
        });
        notifyDataSetChanged();
    }

    public void sortDescending() {
        Collections.sort(attendanceList, new Comparator<Attendance>() {
            @Override
            public int compare(Attendance a1, Attendance a2) {
                return a2.getDate().compareTo(a1.getDate());
            }
        });
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView statusTextView;

        ViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
        }
    }
}

