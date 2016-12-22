package com.example;

/**
 * Created by Filip on 2016-12-20.
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ActorsController {
    private final AtomicLong counter = new AtomicLong();
    public List<Actor> lista = new ArrayList<Actor>();
    private final AtomicLong countermovie = new AtomicLong();
    public List<Movie> listamovie = new ArrayList<Movie>();



    @RequestMapping("/Actors/Show")
    public List<Actor> ActorShow() {
        return lista;
    }
    @RequestMapping("/Actors/Add")
    public Actor ActorAdd(@RequestParam(value="FirstName", required=true) String FirstName,
                          @RequestParam(value="LastName", required=true) String LastName,
                          @RequestParam(value="Age",required=true) int Age) {
        Actor tmp=new Actor(counter.incrementAndGet(),Age,FirstName,LastName);
        lista.add(tmp);
        return tmp;
    }

    @RequestMapping("/Actors/Search")
    public List<Actor> ActorSearch(@RequestParam(value="FirstName", required=false) String FirstName,
                             @RequestParam(value="LastName", required=false) String LastName,
                             @RequestParam(value="Age", required=false) int Age) {
         List<Actor> lista2 = new ArrayList<Actor>();
        for (Actor p : lista) {
            if (    p.getFirstName().equals(FirstName) ||
                    p.getLastName().equals(LastName) ||
                    p.getAge() == Age ) {
                lista2.add(p);
            }
        }

        return lista2;
    }
    @RequestMapping("/Actors/Remove")
    public String ActorRemove(@RequestParam(value="FirstName", required=true) String FirstName,
                                   @RequestParam(value="LastName", required=true) String LastName,
                                   @RequestParam(value="Age", required=true) int Age) {

        for (Actor p : lista) {
            if (    p.getFirstName().equals(FirstName) &&
                    p.getLastName().equals(LastName) &&
                    p.getAge() == Age ) {
                lista.remove(p);
                return "Actor removed";
            }
        }

        return "Actor not found";
    }
    @RequestMapping("/Actors/Edit")
    public Actor ActorEdit(     @RequestParam(value="FirstName", required=true) String FirstName,
                                @RequestParam(value="LastName", required=true) String LastName,
                                @RequestParam(value="Age", required=true) int Age,
                                @RequestParam(value="nFirstName", defaultValue = "none") String nFirstName,
                                @RequestParam(value="nLastName", defaultValue = "none") String nLastName,
                                @RequestParam(value="nAge", defaultValue = "0") int nAge) {

        for (Actor p : lista) {
            if (    p.getFirstName().equals(FirstName) &&
                    p.getLastName().equals(LastName) &&
                    p.getAge() == Age ) {
                if(nFirstName != "none") p.newFName(nFirstName);
                if(nLastName != "none") p.newLName(nLastName);
                if(nAge != 0) p.newAge(nAge);
                return p;
            }
        }

        return null;
    }

    @RequestMapping("/Movies/Show")
    public List<Movie> MovieShow() {
        return listamovie;
    }

    @RequestMapping("/Movies/Add")
    public Movie MovieAdd(@RequestParam(value="Title", required=true) String Title,
                          @RequestParam(value="Year",required=true) int Year,
                          @RequestParam(value="IDs",required=true) ArrayList<Integer>IDs)
    {
        ArrayList<Actor> lista2 = new ArrayList<Actor>();
        for (int id : IDs) {
        for (Actor p : lista) {
            if (p.getId() == id) lista2.add(p);
            }
        }
        Movie tmp=new Movie(countermovie.incrementAndGet(),Title,Year,lista2);
        listamovie.add(tmp);
        return tmp;
    }

    public int SearchForActor(Movie movie, ArrayList<Integer>IDs){
        for (int id : IDs) {
            for (Actor p : movie.getActors()) {
                if (p.getId() == id) return 1;
            }
        }
        return 0;
    }

    @RequestMapping("/Movies/Search")
    public List<Movie> MovieSearch(@RequestParam(value="Title", required=true) String Title,
                                   @RequestParam(value="Year",required=true) int Year,
                                   @RequestParam(value="IDs",required=true) ArrayList<Integer>IDs) {
        List<Movie> lista2 = new ArrayList<Movie>();

        for (Movie p : listamovie) {
            if (    p.getTitle().equals(Title) ||
                    p.getYear() == Year ||
                    SearchForActor(p,IDs) == 1 ) {
                lista2.add(p);
            }
        }

        return lista2;
    }
    @RequestMapping("/Movies/Remove")
    public String MovieRemove(@RequestParam(value="Title", required=true) String Title,
                                   @RequestParam(value="Year",required=true) int Year,
                                   @RequestParam(value="IDs",required=true) ArrayList<Integer>IDs) {
        for (Movie p : listamovie) {
            if (    p.getTitle().equals(Title) &&
                    p.getYear() == Year &&
                    SearchForActor(p,IDs) == 1 ) {
                listamovie.remove(p);
                return "Movie removed";
            }
        }

        return "Movie not found";
    }
    @RequestMapping("/Movies/Edit")
    public Movie MovieEdit(     @RequestParam(value="Title", required=true) String Title,
                                @RequestParam(value="Year",required=true) int Year,
                                @RequestParam(value="IDs",required=true) ArrayList<Integer>IDs,
                                @RequestParam(value="Title", defaultValue = "none") String nTitle,
                                @RequestParam(value="Year",defaultValue = "0") int nYear,
                                @RequestParam(value="IDs",defaultValue = "0") ArrayList<Integer>nIDs) {

        for (Movie p : listamovie) {
            if (    p.getTitle().equals(Title) &&
                    p.getYear()== Year &&
                    SearchForActor(p,IDs) == 1 ) {
                if(nTitle != "none") p.newTitle(nTitle);
                if(nYear != 0) p.newYear(nYear);
                if(nIDs != null) {
                    ArrayList<Actor> lista2 = new ArrayList<Actor>();
                    for (int id : IDs) {
                        for (Actor q : lista) {
                            if (q.getId() == id) lista2.add(q);
                        }
                    }
                    p.newActors(lista2);
                };
                return p;
            }
        }

        return null;
    }

}
