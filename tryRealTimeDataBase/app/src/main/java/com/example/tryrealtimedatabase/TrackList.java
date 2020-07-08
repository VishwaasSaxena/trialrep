package com.example.tryrealtimedatabase;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TrackList extends ArrayAdapter<Track> {

    public Activity context;
    TextView trackname,rating;
    public List<Track> trackList;

    public TrackList(Activity context,List<Track>trackList){
        super(context,R.layout.track_layout);
        Log.d("testing","Constructor called");
        this.context=context;
        this.trackList=trackList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d("DATA ACTIVITY","Called");
        LayoutInflater inflater=context.getLayoutInflater();
        View view=inflater.inflate(R.layout.track_layout,null,false);
        trackname=view.findViewById(R.id.trackname);
        rating=view.findViewById(R.id.rating);
        Track track=trackList.get(position);
        trackname.setText(track.getTrackname());
        rating.setText(String.valueOf(track.getTrackrating()));
        return view;
    }
}
