package com.example.proffind;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfessorHomeFragment extends Fragment {

    ListView listOfAppointments;
    Button scheduleOne,scheduleTwo,scheduleThree,scheduleFour;
    public AlertDialog.Builder builder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_professor_home,container,false);
        builder = new AlertDialog.Builder(getContext());
        DatabaseConnect db = new DatabaseConnect();
        int profId = db.getProfessorId(saveLoginDetails.getInstance().getFirstName()+" "+saveLoginDetails.getInstance().getLastName());
        //System.out.println((saveLoginDetails.getInstance().getFirstName()+" "+saveLoginDetails.getInstance().getLastName()).trim().equals("Weidong Xiong"));
        List<Map<String,Integer>> getScheduleDetails = new ArrayList<Map<String,Integer>>();
        System.out.println(profId);
        getScheduleDetails = db.getScheduleDetails(profId);
        System.out.println(getScheduleDetails);
        System.out.println(getScheduleDetails.size());
        scheduleOne = view.findViewById(R.id.line1);
        scheduleTwo = view.findViewById(R.id.line2);
        scheduleThree = view.findViewById(R.id.line3);
        scheduleFour = view.findViewById(R.id.line4);
        if(getScheduleDetails.size()==1)
        {
            scheduleTwo.setVisibility(view.INVISIBLE);
            scheduleThree.setVisibility(view.INVISIBLE);
            scheduleFour.setVisibility(view.INVISIBLE);

            int userId = getScheduleDetails.get(0).get("userId");
            int availableId = getScheduleDetails.get(0).get("availableId");
            int timeId = db.getTimeSlotId(availableId);
            String scheduleDay = db.getDay(availableId);
            String studentName = db.getUserName(userId);
            String timeSlot=null;
            if(timeId==1)
                timeSlot="12:00pm - 1:00pm";
            if(timeId==2)
                timeSlot="1:30pm - 2:30pm";
            if(timeId==3)
                timeSlot="3:00pm - 4:00pm";
            if(timeId==4)
                timeSlot="4:30pm - 5:30pm";


            scheduleOne.setText("Name: "+studentName+"\nTime: "+timeSlot+"\nDay: "+scheduleDay);
            scheduleOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    builder.setMessage("Are you sure?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.deleteSchedule(availableId);
                                    db.updateProfessorAvailability(availableId);
                                    Toast.makeText(getContext(), "Appointment Deleted, refresh to see change", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Delete Appointment");
                    alert.show();
                }
            });
        }
        else if(getScheduleDetails.size()==2)
        {
            scheduleThree.setVisibility(view.INVISIBLE);
            scheduleFour.setVisibility(view.INVISIBLE);

            int userId1 = getScheduleDetails.get(0).get("userId");
            int availableId1 = getScheduleDetails.get(0).get("availableId");
            int timeId1 = db.getTimeSlotId(availableId1);
            String scheduleDay1 = db.getDay(availableId1);
            String studentName1 = db.getUserName(userId1);
            String timeSlot1=null;
            if(timeId1==1)
                timeSlot1="12:00pm - 1:00pm";
            if(timeId1==2)
                timeSlot1="1:30pm - 2:30pm";
            if(timeId1==3)
                timeSlot1="3:00pm - 4:00pm";
            if(timeId1==4)
                timeSlot1="4:30pm - 5:30pm";

            scheduleOne.setText("Name: "+studentName1+"\nTime: "+timeSlot1+"\nDay: "+scheduleDay1);
            scheduleOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    builder.setMessage("Are you sure?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.deleteSchedule(availableId1);
                                    db.updateProfessorAvailability(availableId1);
                                    Toast.makeText(getContext(), "Appointment Deleted, refresh to see change", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Delete Appointment");
                    alert.show();
                }
            });

            int userId2 = getScheduleDetails.get(1).get("userId");
            int availableId2 = getScheduleDetails.get(1).get("availableId");
            int timeId2 = db.getTimeSlotId(availableId2);
            String scheduleDay2 = db.getDay(availableId2);
            String studentName2 = db.getUserName(userId2);
            String timeSlot2=null;
            if(timeId2==1)
                timeSlot2="12:00pm - 1:00pm";
            if(timeId2==2)
                timeSlot2="1:30pm - 2:30pm";
            if(timeId2==3)
                timeSlot2="3:00pm - 4:00pm";
            if(timeId2==4)
                timeSlot2="4:30pm - 5:30pm";

            scheduleTwo.setText("Name: "+studentName2+"\nTime: "+timeSlot2+"\nDay: "+scheduleDay2);
            scheduleTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    builder.setMessage("Are you sure?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.deleteSchedule(availableId2);
                                    db.updateProfessorAvailability(availableId2);
                                    Toast.makeText(getContext(), "Appointment Deleted, refresh to see change", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Delete Appointment");
                    alert.show();
                }
            });
        }
        else if(getScheduleDetails.size()==3)
        {
            scheduleFour.setVisibility(view.INVISIBLE);

            int userId1 = getScheduleDetails.get(0).get("userId");
            int availableId1 = getScheduleDetails.get(0).get("availableId");
            int timeId1 = db.getTimeSlotId(availableId1);
            String scheduleDay1 = db.getDay(availableId1);
            String studentName1 = db.getUserName(userId1);
            String timeSlot1=null;
            if(timeId1==1)
                timeSlot1="12:00pm - 1:00pm";
            if(timeId1==2)
                timeSlot1="1:30pm - 2:30pm";
            if(timeId1==3)
                timeSlot1="3:00pm - 4:00pm";
            if(timeId1==4)
                timeSlot1="4:30pm - 5:30pm";

            scheduleOne.setText("Name: "+studentName1+"\nTime: "+timeSlot1+"\nDay: "+scheduleDay1);
            scheduleOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    builder.setMessage("Are you sure?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.deleteSchedule(availableId1);
                                    db.updateProfessorAvailability(availableId1);
                                    Toast.makeText(getContext(), "Appointment Deleted, refresh to see change", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Delete Appointment");
                    alert.show();
                }
            });


            int userId2 = getScheduleDetails.get(1).get("userId");
            int availableId2 = getScheduleDetails.get(1).get("availableId");
            int timeId2 = db.getTimeSlotId(availableId2);
            String scheduleDay2 = db.getDay(availableId2);
            String studentName2 = db.getUserName(userId2);
            String timeSlot2=null;
            if(timeId2==1)
                timeSlot2="12:00pm - 1:00pm";
            if(timeId2==2)
                timeSlot2="1:30pm - 2:30pm";
            if(timeId2==3)
                timeSlot2="3:00pm - 4:00pm";
            if(timeId2==4)
                timeSlot2="4:30pm - 5:30pm";

            scheduleTwo.setText("Name: "+studentName2+"\nTime: "+timeSlot2+"\nDay: "+scheduleDay2);
            scheduleTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    builder.setMessage("Are you sure?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.deleteSchedule(availableId2);
                                    db.updateProfessorAvailability(availableId2);
                                    Toast.makeText(getContext(), "Appointment Deleted, refresh to see change", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Delete Appointment");
                    alert.show();
                }
            });

            int userId3 = getScheduleDetails.get(2).get("userId");
            int availableId3 = getScheduleDetails.get(2).get("availableId");
            int timeId3 = db.getTimeSlotId(availableId3);
            String scheduleDay3 = db.getDay(availableId3);
            String studentName3 = db.getUserName(userId3);
            String timeSlot3=null;
            if(timeId3==1)
                timeSlot3="12:00pm - 1:00pm";
            if(timeId3==2)
                timeSlot3="1:30pm - 2:30pm";
            if(timeId3==3)
                timeSlot3="3:00pm - 4:00pm";
            if(timeId3==4)
                timeSlot3="4:30pm - 5:30pm";

            scheduleThree.setText("Name: "+studentName3+"\nTime: "+timeSlot3+"\nDay: "+scheduleDay3);
            scheduleThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    builder.setMessage("Are you sure?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.deleteSchedule(availableId3);
                                    db.updateProfessorAvailability(availableId3);
                                    Toast.makeText(getContext(), "Appointment Deleted, refresh to see change", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Delete Appointment");
                    alert.show();
                }
            });
        }
        else if(getScheduleDetails.size()==4)
        {

            int userId1 = getScheduleDetails.get(0).get("userId");
            int availableId1 = getScheduleDetails.get(0).get("availableId");
            int timeId1 = db.getTimeSlotId(availableId1);
            String scheduleDay1 = db.getDay(availableId1);
            String studentName1 = db.getUserName(userId1);
            String timeSlot1=null;
            if(timeId1==1)
                timeSlot1="12:00pm - 1:00pm";
            if(timeId1==2)
                timeSlot1="1:30pm - 2:30pm";
            if(timeId1==3)
                timeSlot1="3:00pm - 4:00pm";
            if(timeId1==4)
                timeSlot1="4:30pm - 5:30pm";

            scheduleOne.setText("Name: "+studentName1+"\nTime: "+timeSlot1+"\nDay: "+scheduleDay1);
            scheduleOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    builder.setMessage("Are you sure?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.deleteSchedule(availableId1);
                                    db.updateProfessorAvailability(availableId1);
                                    Toast.makeText(getContext(), "Appointment Deleted, refresh to see change", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Delete Appointment");
                    alert.show();
                }
            });

            int userId2 = getScheduleDetails.get(1).get("userId");
            int availableId2 = getScheduleDetails.get(1).get("availableId");
            int timeId2 = db.getTimeSlotId(availableId2);
            String scheduleDay2 = db.getDay(availableId2);
            String studentName2 = db.getUserName(userId2);
            String timeSlot2=null;
            if(timeId2==1)
                timeSlot2="12:00pm - 1:00pm";
            if(timeId2==2)
                timeSlot2="1:30pm - 2:30pm";
            if(timeId2==3)
                timeSlot2="3:00pm - 4:00pm";
            if(timeId2==4)
                timeSlot2="4:30pm - 5:30pm";

            scheduleTwo.setText("Name: "+studentName2+"\nTime: "+timeSlot2+"\nDay: "+scheduleDay2);
            scheduleTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    builder.setMessage("Are you sure?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.deleteSchedule(availableId2);
                                    db.updateProfessorAvailability(availableId2);
                                    Toast.makeText(getContext(), "Appointment Deleted, refresh to see change", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Delete Appointment");
                    alert.show();
                }
            });


            int userId3 = getScheduleDetails.get(2).get("userId");
            int availableId3 = getScheduleDetails.get(2).get("availableId");
            int timeId3 = db.getTimeSlotId(availableId3);
            String scheduleDay3 = db.getDay(availableId3);
            String studentName3 = db.getUserName(userId3);
            String timeSlot3=null;
            if(timeId3==1)
                timeSlot3="12:00pm - 1:00pm";
            if(timeId3==2)
                timeSlot3="1:30pm - 2:30pm";
            if(timeId3==3)
                timeSlot3="3:00pm - 4:00pm";
            if(timeId3==4)
                timeSlot3="4:30pm - 5:30pm";

            scheduleThree.setText("Name: "+studentName3+"\nTime: "+timeSlot3+"\nDay: "+scheduleDay3);
            scheduleThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    builder.setMessage("Are you sure?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.deleteSchedule(availableId3);
                                    db.updateProfessorAvailability(availableId3);
                                    Toast.makeText(getContext(), "Appointment Deleted, refresh to see change", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Delete Appointment");
                    alert.show();
                }
            });

            int userId4 = getScheduleDetails.get(3).get("userId");
            int availableId4 = getScheduleDetails.get(3).get("availableId");
            int timeId4 = db.getTimeSlotId(availableId4);
            String scheduleDay4 = db.getDay(availableId4);
            String studentName4 = db.getUserName(userId4);
            String timeSlot4=null;
            if(timeId4==1)
                timeSlot4="12:00pm - 1:00pm";
            if(timeId4==2)
                timeSlot4="1:30pm - 2:30pm";
            if(timeId4==3)
                timeSlot4="3:00pm - 4:00pm";
            if(timeId4==4)
                timeSlot4="4:30pm - 5:30pm";

            scheduleFour.setText("Name: "+studentName4+"\nTime: "+timeSlot4+"\nDay: "+scheduleDay4);
            scheduleFour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    builder.setMessage("Are you sure?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.deleteSchedule(availableId4);
                                    db.updateProfessorAvailability(availableId4);
                                    Toast.makeText(getContext(), "Appointment Deleted, refresh to see change", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.setTitle("Delete Appointment");
                    alert.show();
                }
            });
        }
        else
        {
            scheduleOne.setVisibility(view.INVISIBLE);
            scheduleTwo.setVisibility(view.INVISIBLE);
            scheduleThree.setVisibility(view.INVISIBLE);
            scheduleFour.setVisibility(view.INVISIBLE);
            TextView noAppointment = view.findViewById(R.id.noAppointments);
            noAppointment.setText("No Appointments available");
        }
        return view;
    }
}