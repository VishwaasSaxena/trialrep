package com.example.tryrealtimedatabase;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String Artist_Name="artistname";
    public static final String Artist_ID="artistid";
    EditText artistname;
    ListView displayList;
    Button addartist;
    Spinner genre;
    DatabaseReference databaseReference,databaseReference1;
    List artistList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference=FirebaseDatabase.getInstance().getReference("artists");
        //databaseReference1=FirebaseDatabase.getInstance().getReference("chidren");
        setContentView(R.layout.activity_main);
        artistname=findViewById(R.id.artistname);
        addartist=findViewById(R.id.firebasedata);
        artistList=new ArrayList<>();
        genre=findViewById(R.id.genre);
        displayList=findViewById(R.id.listeview);
        addartist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArtist();

            }
        });
        displayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Artist artist= (Artist) artistList.get(position);

                Intent intent=new Intent(getApplicationContext(),AddTrackActivity.class);
                intent.putExtra(Artist_ID,artist.getArtistId());
                intent.putExtra(Artist_Name,artist.getArtistName());
                startActivity(intent);

            }
        });






    }
    public void addArtist(){
        String name=artistname.getText().toString().trim();
        String option=genre.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)){
            String id=databaseReference.push().getKey();
            Artist artist=new Artist(id,option,name);
            databaseReference.child(id).setValue(artist);
           // databaseReference1.child(id).setValue(artist);
            Toast.makeText(this, "Added Successfully",Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this,"Please enter name",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                artistList.clear();
                if(dataSnapshot!=null) {
                    for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {

                        artistList.add(artistSnapshot.getValue(Artist.class));

                    }
                    ArtistList adapter = new ArtistList(MainActivity.this, artistList);
                    displayList.setAdapter(adapter);
                }else{
                    Log.d("data falied","Null");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
