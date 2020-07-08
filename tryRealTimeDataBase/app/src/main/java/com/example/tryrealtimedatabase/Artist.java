package com.example.tryrealtimedatabase;


public class Artist {
    String artistId;
    String artistName;
    String artistGenre;
    public Artist(String artistID,String artistGenre,String artistName){
        this.artistId=artistID;
        this.artistGenre=artistGenre;
        this.artistName=artistName;
    }
    public Artist(){

    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistGenre() {
        return artistGenre;
    }

    public void setArtistGenre(String artistGenre) {
        this.artistGenre = artistGenre;
    }
}

