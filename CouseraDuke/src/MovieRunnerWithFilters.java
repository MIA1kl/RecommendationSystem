import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
    private static int helperMoviesAndRatings(){
        ThirdRatings  MoviesAndRatings = new ThirdRatings("ratings.csv");
        System.out.println("Number of total of Raters: " + MoviesAndRatings.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of total of movies: " + MovieDatabase.size());
        int minimum = 4;
        return minimum;
    }

    public static void printAverageRatings(){
        int minimum = helperMoviesAndRatings();
        ThirdRatings  MoviesAndRatings = new ThirdRatings("ratings.csv");
        ArrayList<Rating> arrayMovies = MoviesAndRatings.getAverageRatings(minimum);
        Collections.sort(arrayMovies);
        for(Rating r : arrayMovies){
            String item = r.getItem();
            String movieTitle = MovieDatabase.getTitle(item);
            System.out.println((double)Math.round(r.getValue() * 10000d) / 10000d+
                    " " + movieTitle);
        }
        System.out.println("Movies with at least " + minimum + " ratings: " +
                arrayMovies.size());
    }

    public static void printAverageRatingsByYear(){
        YearAfterFilter filterYear = new YearAfterFilter(2000);
        int minimum = helperMoviesAndRatings();
        ThirdRatings  MoviesAndRatings = new ThirdRatings("ratings.csv");
        ArrayList<Rating> arrayMovies = MoviesAndRatings.getAverageRatingsByFilter(minimum,filterYear);
        Collections.sort(arrayMovies);
        for(Rating r : arrayMovies){
            String item = r.getItem();
            String movieTitle = MovieDatabase.getTitle(item);
            System.out.println((double)Math.round(r.getValue() * 10000d) / 10000d+
                    " "+ MovieDatabase.getYear(item)+" " + movieTitle);

        }
        System.out.println("Movies returned = " + arrayMovies.size());
    }

    public static void printAverageRatingsByGenre(){
        GenreFilter filterGenre = new GenreFilter("Comedy");
        ThirdRatings  MoviesAndRatings = new ThirdRatings("ratings.csv");
        int minimum = helperMoviesAndRatings();
        ArrayList<Rating> arrayMovies = MoviesAndRatings.getAverageRatingsByFilter(minimum,filterGenre);
        Collections.sort(arrayMovies);
        for(Rating r : arrayMovies){
            String item = r.getItem();
            String movieTitle = MovieDatabase.getTitle(item);
            System.out.println((double)Math.round(r.getValue() * 10000d) / 10000d+
                    " " + movieTitle);
            System.out.println(MovieDatabase.getGenres(item));
        }
        System.out.println("Movies returned = " + arrayMovies.size());
    }

    public static void printAverageRatingsByMinutes(){
        final long startTime = System.currentTimeMillis();
        MinutesFilter filterMinutes = new MinutesFilter(105,135);
        ThirdRatings  MoviesAndRatings = new ThirdRatings("ratings.csv");
        int minimum = helperMoviesAndRatings();
        ArrayList<Rating> arrayMovies = MoviesAndRatings.getAverageRatingsByFilter(minimum,filterMinutes);
        Collections.sort(arrayMovies);
        for(Rating r : arrayMovies){
            String item = r.getItem();
            String movieTitle = MovieDatabase.getTitle(item);
            System.out.println((double)Math.round(r.getValue() * 10000d) / 10000d+
                    " Time: " + MovieDatabase.getMinutes(item) + " " + movieTitle);
        }
        System.out.println("Movies returned = " + arrayMovies.size());
    }

    public static void printAverageRatingsByDirectors(){
        DirectorsFilter filterDirectors = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ThirdRatings  MoviesAndRatings = new ThirdRatings("ratings.csv");
        int minimum = helperMoviesAndRatings();
        ArrayList<Rating> arrayMovies = MoviesAndRatings.getAverageRatingsByFilter(minimum,filterDirectors);
        Collections.sort(arrayMovies);
        for(Rating r : arrayMovies){
            String item = r.getItem();
            String movieTitle = MovieDatabase.getTitle(item);
            System.out.println((double)Math.round(r.getValue() * 10000d) / 10000d+
                    " " + movieTitle + " Director: " + MovieDatabase.getDirector(item));
        }
        System.out.println("Movies returned = " + arrayMovies.size());
    }

    public static void printAverageRatingsByYearAfterAndGenre(){
        YearAfterFilter filterYear = new YearAfterFilter(1990);
        GenreFilter filterGenre = new GenreFilter("Drama");
        ThirdRatings  MoviesAndRatings = new ThirdRatings("ratings.csv");
        int minimum = helperMoviesAndRatings();
        AllFilters yearAndGenre = new AllFilters();
        yearAndGenre.addFilter(filterYear);
        yearAndGenre.addFilter(filterGenre);
        ArrayList<Rating> arrayMovies = MoviesAndRatings.getAverageRatingsByFilter(minimum,yearAndGenre);
        Collections.sort(arrayMovies);
        for(Rating r : arrayMovies){
            String item = r.getItem();
            String movieTitle = MovieDatabase.getTitle(item);
            System.out.println((double)Math.round(r.getValue() * 10000d) / 10000d+
                    " " + movieTitle);
            System.out.println(" Director: " + MovieDatabase.getDirector(item));
            System.out.println(" Genres: "+ MovieDatabase.getGenres(item));
        }
        System.out.println("Movies returned = " + arrayMovies.size());
    }

    public static void printAverageRatingsByDirectorsAndMinutes(){
        MinutesFilter filterMinutes = new MinutesFilter(90,180);
        DirectorsFilter filterDirectors = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        ThirdRatings  MoviesAndRatings = new ThirdRatings("ratings.csv");
        int minimum = helperMoviesAndRatings();
        AllFilters directorsAndMinutes = new AllFilters();
        directorsAndMinutes.addFilter(filterMinutes);
        directorsAndMinutes.addFilter(filterDirectors);
        ArrayList<Rating> arrayMovies = MoviesAndRatings.getAverageRatingsByFilter(minimum,directorsAndMinutes);
        Collections.sort(arrayMovies);
        for(Rating r : arrayMovies){
            String item = r.getItem();
            String movieTitle = MovieDatabase.getTitle(item);
            System.out.println((double)Math.round(r.getValue() * 10000d) / 10000d+
                    " Time: " + MovieDatabase.getMinutes(item) +" " + movieTitle+ " Director: " + MovieDatabase.getDirector(item));

        }
        System.out.println("Movies returned = " + arrayMovies.size());
    }

    public static void main(String[] args) {
//        printAverageRatingsByDirectorsAndMinutes();
//       printAverageRatingsByYearAfterAndGenre();
//        printAverageRatingsByDirectors();
//        printAverageRatingsByMinutes();
//        printAverageRatingsByGenre();
//        printAverageRatingsByYear();
//        printAverageRatings();
    }
}

