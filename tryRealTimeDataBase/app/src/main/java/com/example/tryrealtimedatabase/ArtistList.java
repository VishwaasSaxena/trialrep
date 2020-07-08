package com.example.tryrealtimedatabase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ArtistList extends ArrayAdapter<Artist> {

    public Activity context;
    TextView textname,textgenre;
    public List<Artist> artistList;

    public ArtistList(Activity context,List<Artist>artistList){
        super(context,R.layout.list_layout, artistList);
        this.context=context;
        this.artistList=artistList;


    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listView=inflater.inflate(R.layout.list_layout,null,false);
        textname= listView.findViewById(R.id.artistname);
        textgenre=listView.findViewById(R.id.musicgenre);
        Artist artist=artistList.get(position);
        textname.setText(artist.getArtistName());
        textgenre.setText(artist.getArtistGenre());
        return listView;


    }
}
