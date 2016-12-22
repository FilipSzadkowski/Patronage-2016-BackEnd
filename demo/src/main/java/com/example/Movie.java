package com.example;

import java.util.ArrayList;

/**
 * Created by Filip on 2016-12-22.
 */
public class Movie {
        private final long id;

        private String title;
        private int year;
        private ArrayList<Actor> actors;

        public Movie(long id, String title, int year, ArrayList<Actor> actors) {
            this.id = id;
            this.title = title;
            this.year=year;
            this.actors=actors;
        }
        public long getId() {
            return id;
        }
        public int getYear() {
            return year;
        }
        public String getTitle() {
            return title;
        }
        public ArrayList<Actor> getActors() {
        return actors;
    }
        public void newYear(int newYear){this.year=newYear;}
        public void newTitle(String newTitle){this.title=newTitle;}
        public void newActors(ArrayList<Actor> newActors){this.actors=newActors;}
    }