package com.example.proffind;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileFragment extends Fragment {

    EditText profileFirstName,profileLastName,profileEmailAddress,profileGender,profileAddress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        profileFirstName = (EditText) view.findViewById(R.id.profileFirstName);
        profileLastName = (EditText) view.findViewById(R.id.profileLastName);
        profileEmailAddress = (EditText) view.findViewById(R.id.profileEmailAddress);
        profileGender = (EditText) view.findViewById(R.id.profileGender);
        profileAddress = (EditText) view.findViewById(R.id.profileAddress);

        profileFirstName.setText(saveLoginDetails.getInstance().getFirstName());
        profileLastName.setText(saveLoginDetails.getInstance().getLastName());
        profileEmailAddress.setText(saveLoginDetails.getInstance().getEmailAddress());
        profileGender.setText(saveLoginDetails.getInstance().getGender());
        profileAddress.setText(saveLoginDetails.getInstance().getAddres());

        Button update = (Button) view.findViewById(R.id.updateProfile);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(saveLoginDetails.getInstance().getFirstName().equals(profileFirstName.getText().toString())))
                {
                    DatabaseConnect db = new DatabaseConnect();
                    db.updateFirstName(profileFirstName.getText().toString(),saveLoginDetails.getInstance().getFirstName());
                    Toast.makeText(getContext(), "updated", Toast.LENGTH_SHORT).show();
                }
                if(!(saveLoginDetails.getInstance().getLastName().equals(profileLastName.getText().toString())))
                {
                    DatabaseConnect db = new DatabaseConnect();
                    db.updateLastName(profileLastName.getText().toString(),saveLoginDetails.getInstance().getLastName());
                }
                if(!(saveLoginDetails.getInstance().getEmailAddress().equals(profileEmailAddress.getText().toString())))
                {
                    DatabaseConnect db = new DatabaseConnect();
                    db.updateEmailAddress(profileEmailAddress.getText().toString(),saveLoginDetails.getInstance().getEmailAddress());
                }
                if(!(saveLoginDetails.getInstance().getGender().equals(profileGender.getText().toString())))
                {
                    DatabaseConnect db = new DatabaseConnect();
                    db.updateGender(profileGender.getText().toString(),saveLoginDetails.getInstance().getGender());
                }
                if(!(saveLoginDetails.getInstance().getAddres().equals(profileAddress.getText().toString())))
                {
                    DatabaseConnect db = new DatabaseConnect();
                    db.updateAddress(profileAddress.getText().toString(),saveLoginDetails.getInstance().getAddres());
                }
            }
        });
        return view;

    }
}