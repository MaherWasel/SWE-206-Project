package com.example.swe206project;

public class Record {
    private String tournamentName;
    private int place;
    private Student participant;

    public Record(Student participant, String tournamentName, int place) {
        this.tournamentName = tournamentName;
        this.place = place;
        this.participant = participant;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void changeTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public int getPlace() {
        return place;
    }

    public void changePlace(int place) {
        this.place = place;
    }
    public Student getparticipant(){
        return participant;
    } 

    @Override
    public String toString() {
        return "TournamentName: " + this.tournamentName + " ParticipantName: " + this.participant.getName()
                + " Place: " + this.place+"\n";
    }

}
