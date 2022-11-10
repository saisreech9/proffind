package com.example.proffind;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        Spinner displayProfDetails = (Spinner) view.findViewById(R.id.spinnerLastName);
        DatabaseConnect db = new DatabaseConnect();
        ArrayList<String> profNameDetails = db.fillSpinner();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item,profNameDetails);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        displayProfDetails.setAdapter(dataAdapter);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),profNameDetails,R.layout.fragment_home);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        displayProfDetails.setAdapter(adapter);

//        ArrayAdapter<String> array = new ArrayAdapter(this.getActivity(),R.layout.fragment_home,R.id.spinnerLastName,profNameDetails);
//        array.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
//        displayProfDetails.setAdapter(array);
        return view;

        //For fragments we use view.findViewById
    }

    public void fillSpinner()
    {
        try{


        }
        catch(Exception e)
        {e.printStackTrace();}
    }
}