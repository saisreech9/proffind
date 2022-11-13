package com.example.proffind;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class HomeFragment extends Fragment {

    Button timeSlot1, timeSlot2, timeSlot3, timeSlot4;

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


        timeSlot1 = (Button) view.findViewById(R.id.timeSlot1);
        timeSlot2 = (Button) view.findViewById(R.id.timeSlot2);
        timeSlot3 = (Button) view.findViewById(R.id.timeSlot3);
        timeSlot4 = (Button) view.findViewById(R.id.timeSlot4);

        timeSlot1.setVisibility(View.GONE);
        timeSlot2.setVisibility(View.GONE);
        timeSlot3.setVisibility(View.GONE);
        timeSlot4.setVisibility(View.GONE);
        Button selectProfessor = (Button) view.findViewById(R.id.selectProfessorButton);
        selectProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedProfessor = displayProfDetails.getSelectedItem().toString();
                System.out.println(selectedProfessor);
                int getProfId = db.getProfessorId(selectedProfessor);
                System.out.println(getProfId);

                timeSlot1.setVisibility(View.VISIBLE);
                timeSlot1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        int timeId = 1;
                        DatabaseConnect db = new DatabaseConnect();
                        int getProfId = db.getProfessorId(selectedProfessor);
                        String isScheduled = db.timeSlotAvailabity(timeId,getProfId);
                        String professorEmailAddress = db.getProfessorEmailAddress(getProfId);
                        if(isScheduled.equals("1"))
                        {
                            Toast.makeText(getContext(), "This slot is taken, please find another slot", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            String emailMessageProfessor = "Hello Professor\n\nAn appointment is scheduled on Monday at 12:00" +
                                    "PM-1:00PM with "+saveLoginDetails.getInstance().getFirstName()+" "+saveLoginDetails.getInstance().getLastName();
                            System.out.println(emailMessageProfessor);
                            String emailMessageStudent = "Hello\n\nYour appointment is scheduled with " +
                                    "Professor "+selectedProfessor+"\n\nDate & Time: Monday, 12:00PM-1:00PM";
                            db.updateSchedule(timeId,getProfId);
                            sendEmail(emailMessageProfessor,emailMessageStudent,professorEmailAddress);
                        }
                    }
                });
                timeSlot2.setVisibility(View.VISIBLE);
                timeSlot3.setVisibility(View.VISIBLE);
                timeSlot4.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

    public void sendEmail(String emailMessageProfessor, String emailMessageStudent,String professorEmailAddress)
    {
        String senderEmail = "testcis634@gmail.com";
        String recieverEmailStudent = saveLoginDetails.getInstance().getEmailAddress();
        String senderPassword = "ztjjbsieyumsqpjn";

        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth","true");

        javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        MimeMessage mimeMessage1 = new MimeMessage(session);
        try {
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(recieverEmailStudent));
            mimeMessage.setSubject("Confirmation Email");
            mimeMessage.setText(emailMessageStudent);
            mimeMessage1.addRecipient(Message.RecipientType.TO,new InternetAddress(professorEmailAddress));
            mimeMessage1.setSubject("Appointment Details");
            mimeMessage1.setText(emailMessageProfessor);

            Thread thread =  new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                        Transport.send(mimeMessage1);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}