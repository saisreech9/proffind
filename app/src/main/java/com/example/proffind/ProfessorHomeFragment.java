package com.example.proffind;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfessorHomeFragment extends Fragment {

    ListView listOfAppointments;
    Button scheduleOne;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_professor_home,container,false);
        DatabaseConnect db = new DatabaseConnect();
        int profId = db.getProfessorId(saveLoginDetails.getInstance().getFirstName()+" "+saveLoginDetails.getInstance().getLastName());
        //System.out.println((saveLoginDetails.getInstance().getFirstName()+" "+saveLoginDetails.getInstance().getLastName()).trim().equals("Weidong Xiong"));
        List<Map<String,Integer>> getScheduleDetails = new ArrayList<Map<String,Integer>>();
        System.out.println(profId);
        getScheduleDetails = db.getScheduleDetails(profId);
        System.out.println(getScheduleDetails);
        System.out.println(getScheduleDetails.size());
        if(getScheduleDetails.size()==1)
        {
            scheduleOne = view.findViewById(R.id.line1);
            scheduleOne.setText("Name:Sai Sree\nTime: Example");
        }
//        //System.out.println("Schedule details "+db.getScheduleDetails());
//        //listOfAppointments = view.findViewById(R.id.listOfAppointments);
//        ArrayList<String> arrayList = new ArrayList<>();
//        for(int i =0;i<getScheduleDetails.size();i++)
//        {
//            //arrayList.add(getScheduleDetails.get(i));
//        }
//        arrayList.add("Name: Sai Sree\nDay: Thursday\nTime: 12:00pm - 1:00pm");
//        arrayList.add("ANDROID");
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1 htr5,arrayList);
//        listOfAppointments.setAdapter(adapter);
        return view;
    }
}