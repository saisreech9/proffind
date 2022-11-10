package com.example.proffind;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**

 */
public class ProfileFragment extends Fragment {

    EditText profileFirstName,profileLastName,profileEmailAddress,profileGender,profileAddress;
    String editedFirstName,editedLastName,editedEmailAddress,editedGender,editedAddress;
    String enteredFirstName,enteredLastName, enteredEmailAddress, enteredGender, enteredAddress;

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

        Button update = (Button) view.findViewById(R.id.updateProfile);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                profileFirstName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        enteredFirstName = profileFirstName.getText().toString();
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        editedFirstName = profileFirstName.getText().toString();
                    }
                    @Override
                    public void afterTextChanged(Editable editable) {
                        if(!(enteredFirstName.equals(editedFirstName)))
                        {
                            DatabaseConnect db = new DatabaseConnect();
                            db.updateFirstName(editedFirstName,enteredFirstName);
                            Toast.makeText(getContext(), "FirstName Updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                profileLastName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        enteredLastName = profileLastName.getText().toString();
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        editedLastName = profileLastName.getText().toString();
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                profileAddress.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        enteredAddress = profileAddress.getText().toString();
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        editedAddress = profileAddress.getText().toString();
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                profileGender.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        enteredGender = profileGender.getText().toString();
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        editedGender = profileGender.getText().toString();
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                profileEmailAddress.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        enteredEmailAddress = profileEmailAddress.getText().toString();
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        editedEmailAddress = profileEmailAddress.getText().toString();
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        });

        return view;

    }
}