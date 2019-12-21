package com.example.coding_hackaton_guwahati;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class DataFragment extends Fragment
{

    private String proeject_id = Prevalent.project_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_data, container, false);

        if(v != null)
        {
            final TextView project_name = v.findViewById(R.id.fragmentData_name);
            final TextView project_description = v.findViewById(R.id.fragmentData_description);
            final TextView contractor_name = v.findViewById(R.id.fragmentData_contr_name);
            final TextView project_status = v.findViewById(R.id.fragmentData_contr_status);

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference doc_ref = db.collection("projects").document(Prevalent.project_id);

            doc_ref.get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
                    {
                        @Override
                        public void onSuccess(DocumentSnapshot snapshot)
                        {
                            if(snapshot.exists())
                            {

                                project_name.setText("Project Name: " + snapshot.getDocumentReference("contractor_ref").toString());
                                project_description.setText("Project Description: " + snapshot.getString("description"));
                                contractor_name.setText("Project Name: " + snapshot.getDocumentReference("contractor_ref"));
                                project_status.setText("Project Status: " + snapshot.getDouble("contractor_status"));


                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(getActivity(),"Internet Not Working Properly", Toast.LENGTH_SHORT).show();
                        }
                    });


        }
        return v;
    }

}
