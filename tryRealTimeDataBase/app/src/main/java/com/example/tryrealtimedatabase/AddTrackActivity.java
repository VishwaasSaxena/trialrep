package com.example.tryrealtimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddTrackActivity extends AppCompatActivity {
    EditText artisttrackName;
    TextView artistName;
    SeekBar rating;
    ListView listTracks;
    Button addtrack;
    DatabaseReference databaseTracks;
    List <Track>trackList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track);
        artisttrackName=findViewById(R.id.artisttrackname);
        rating=findViewById(R.id.seekbarRating);
        trackList=new ArrayList<>();
        listTracks=findViewById(R.id.listeviewTracks);
        addtrack=findViewById(R.id.firebasetrackdata);
        artistName=findViewById(R.id.artistName);
        Intent intent=getIntent();
        String id=intent.getStringExtra(MainActivity.Artist_ID);
        String name=intent.getStringExtra(MainActivity.Artist_Name);
        artistName.setText(name);
        databaseTracks= FirebaseDatabase.getInstance().getReference("Tracks").child(id);
        addtrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTrack();
            }
        });


    }
    private void saveTrack(){
        String trackName=artisttrackName.getText().toString().trim();
        int rate=rating.getProgress();
        if(!TextUtils.isEmpty(trackName)){
            String id=databaseTracks.push().getKey();
            Track track=new Track(id,trackName,rate);
            databaseTracks.child(id).setValue(track);
            Toast.makeText(this,"Track Saved",Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(AddTrackActivity.this,"Enter a track name",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseTracks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                trackList.clear();
                for(DataSnapshot trackSnapshot:dataSnapshot.getChildren()){
                    Track track=trackSnapshot.getValue(Track.class);

                    trackList.add(track);
                }
                TrackList adapter=new TrackList(AddTrackActivity.this,trackList);
                Log.d("Data","In list");
                listTracks.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
