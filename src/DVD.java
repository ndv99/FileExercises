/**
 * Class to store details about a DVD and display them.
 */
public class DVD {

    private String title;
    private String director;
    private int runTime; // in minutes
    private float cost; // in Pounds

    /**
     * Constructor method for DVD class. Objects created in this way should use setters to set field values.
     */
    public DVD() {
        title = "";
        director = "";
        runTime = 0;
        cost = 0;
    }

    /**
     * Second constructor method for DVD class. This one takes four args - one for each field.
     *
     * @param title    String for the title of the film.
     * @param director String for the director of the film.
     * @param runTime  int for film runtime (minutes).
     * @param cost     float for the cost of the film (pounds).
     */
    public DVD(String title, String director, int runTime, float cost) {
        this.title = title;
        this.director = director;
        this.runTime = runTime;
        this.cost = cost;
    }

    /**
     * Accessor for title.
     *
     * @return title string.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Mutator for title.
     *
     * @param title title string.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Accessor for director
     *
     * @return director string.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Mutator for director.
     *
     * @param director director string.
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Accessor for runTime.
     *
     * @return runTime int.
     */
    public int getRunTime() {
        return runTime;
    }

    /**
     * Mutator for runTime
     *
     * @param runTime runTime int.
     */
    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    /**
     * Accessor for cost.
     *
     * @return cost float.
     */
    public float getCost() {
        return cost;
    }

    /**
     * Mutator for cost
     *
     * @param cost cost float.
     */
    public void setCost(float cost) {
        this.cost = cost;
    }

    /**
     * Sets values of all fields in class.
     *
     * @param title    String for the title of the film
     * @param director String for the director of the film.
     * @param runTime  int for film runtime (minutes).
     * @param cost     float for the cost of the film (pounds).
     */
    public void setAll(String title, String director, int runTime, float cost) {
        setTitle(title);
        setDirector(director);
        setRunTime(runTime);
        setCost(cost);
    }

    /**
     * Display method for DVD details.
     */
    public void displayDetails() {
        System.out.println("Title:" + title);
        System.out.println("Directed by" + director);
        System.out.println("Running time: " + runTime + " minutes");
        System.out.println("Cost: £" + cost);
    }
}
