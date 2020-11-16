import edu.duke.FileResource;
import org.apache.commons.csv.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class FirstRatings {
    private static ArrayList<Movie> csvmethod(CSVParser parser){
        ArrayList<Movie> MovieData = new ArrayList<Movie>();
        for(CSVRecord record : parser){
            Movie currMovie = new Movie(record.get("id"),
                    record.get("title"),
                    record.get("year"),
                    record.get("genre"),
                    record.get("director"),
                    record.get("country"),
                    record.get("poster"),
                    Integer.parseInt(record.get("minutes")));
            MovieData.add(currMovie);
        }
        return MovieData;
    }

    public static ArrayList<Movie> loadMovies(String filename){
        String file = "data/" + filename;
        FileResource fr = new FileResource(file);

        CSVParser parser = fr.getCSVParser();
        ArrayList<Movie> MovieData = csvmethod(parser);
        return MovieData;
    }


    public static void testLoadMovies(){
        ArrayList<Movie> MovieData = loadMovies("ratedmoviesfull.csv");
        int count = 0;
        int comedy = 0;
        int minutesLong = 0;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int largestNumMovies = 0;
        for(Movie move : MovieData){
            count++;
            System.out.println(move.getCountry());
            System.out.println(move.toString());
            if (move.getGenres().contains("Comedy")){
                comedy++;
            }
            if(move.getMinutes() > 150){
                minutesLong++;
            }
            String[] directors = (move.getDirector().trim() + ",").split(",");
            for(String dir : directors) {
                if(map.containsKey(dir)) {
                    map.put(dir, map.get(dir) + 1);
                } else {
                    map.put(dir, 1);
                }
            }
        }
        System.out.println("Total movies = " + count);
        System.out.println("Total comedies = " + comedy);
        System.out.println("Movies longer than 150min = " + minutesLong);


        HashSet<String> directorsWithMaxMovies = new HashSet<String>();
        for(String key : map.keySet()) {
            if(map.get(key) > largestNumMovies) {
                largestNumMovies = map.get(key);
                directorsWithMaxMovies.clear();
                directorsWithMaxMovies.add(key);
            } else if (map.get(key) == largestNumMovies) {
                directorsWithMaxMovies.add(key);
            }
        }
        System.out.println("Max Number of Movies By Any Director Is: " + largestNumMovies);
        System.out.println(directorsWithMaxMovies.size() + " directors have directed " + largestNumMovies + ". They are: ");
        for(String dir : directorsWithMaxMovies) {
            System.out.println(dir);
        }


    }
    private ArrayList<Rater> csvRater(CSVParser parser){
        ArrayList<Rater> loadRaters = new ArrayList<Rater>();
        for(CSVRecord rate : parser){
            Rater newRater = new EfficientRater(rate.get("rater_id"));
            loadRaters.add(newRater);
            newRater.addRating(rate.get("movie_id"),Double.parseDouble(rate.get("rating")));
        }
        return loadRaters;
    }

    public ArrayList<Rater> csvMethod2(String filename){
        FileResource f = new FileResource("data/" + filename);
        CSVParser parser = f.getCSVParser();
        ArrayList<Rater> loadRaters = csvRater(parser);
        return loadRaters;
    }

    private static HashMap<String, HashMap<String,Rating>> csvLoadRaters(CSVParser parser){
        HashMap<String, HashMap<String,Rating>> idAndRatingsMap = new HashMap<String, HashMap<String,Rating>>();
        for(CSVRecord rate : parser){
            Rater newRater = new EfficientRater(rate.get("rater_id"));
            newRater.addRating(rate.get("movie_id"),Double.parseDouble(rate.get("rating")));
            for(String hey : newRater.getaRating().keySet()){
                if(!idAndRatingsMap.containsKey(newRater.getID())){
                    idAndRatingsMap.put(newRater.getID(),newRater.getaRating());
                }
                else{
                    idAndRatingsMap.get(newRater.getID()).put(hey,newRater.getaRating().get(hey));
                }
            }
        }
        return idAndRatingsMap;
    }

    public static HashMap<String, HashMap<String,Rating>> loadRaters(String filename){
        FileResource f = new FileResource("data/" + filename);
        CSVParser parser = f.getCSVParser();
        HashMap<String, HashMap<String,Rating>> loadRaters = csvLoadRaters(parser);
        return loadRaters;
    }

    public static void testLoadRaters(){
        String filename = "ratings.csv";
        HashMap<String, HashMap<String,Rating>> loadRaters = loadRaters(filename);
        for(HashMap<String,Rating> rating : loadRaters.values()){
            for(Rating rat : rating.values()){
            }
        }
        System.out.println("Total raters = " + loadRaters.size());
        String specficRater = "193";
        for(HashMap<String,Rating> rating : loadRaters.values()){
            for(Rating rat : rating.values()){
                if(rat.equals(specficRater)){
                    System.out.println("The user with the specific ID " + rat + " voted " + loadRaters.get(rat).size() + " movies");
                }
            }
        }
        int max = 0;
        for(String s : loadRaters.keySet()){
            if(max < loadRaters.get(s).size()){
                max = loadRaters.get(s).size();
            }
        }
        System.out.println("Maximum number of movies rated per user: " + max);
        System.out.println("And they were voted by the users ID: ");
        int countMaxRaters = 0;
        for(String s : loadRaters.keySet()){
            if(loadRaters.get(s).size() == max){
                System.out.println(s);
                countMaxRaters++;
            }
        }
        System.out.println("Amount of TOP1 user/s by no. of votes: " + countMaxRaters);
        String movie_id = "1798709";
        int timesVoted = 0;
        for(HashMap<String,Rating> rating : loadRaters.values()){
            for(Rating eachMovie : rating.values()){
                String moviesID = eachMovie.getItem();
                if(movie_id.equals(moviesID)){
                    timesVoted++;
                }
            }

        }
        System.out.println("The movie with the ID: " + movie_id + " has been voted "  + timesVoted + " times");
        ArrayList<String> list = new ArrayList<String>();
        for(HashMap<String,Rating> rating : loadRaters.values()){
            for(Rating eachMovie : rating.values()){
                String moviesID = eachMovie.getItem();
                if(!list.contains(moviesID)){
                    list.add(moviesID);
                }
            }
        }
        System.out.println("Number of total different movies rated: " + list.size());
    }
    public static void main(String[] args)  {
//        loadMovies("ratedmoviesfull.csv");
//        loadRaters("ratings.csv");
//        testLoadRaters();
//       testLoadMovies();


    }

}