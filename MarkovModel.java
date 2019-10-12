//Import package to use Random and ArrayList
import java.util.*;
public class MarkovModel {
    //Contains the first words of every line of each song
    private ArrayList<String> initDist;
    //Instance of the Graph class
    private Graph transMtx;
    public MarkovModel() {
        //Initializes instance variables
        initDist = new ArrayList<String>();
        transMtx = new Graph();
    }
    //Adds the elements in the parameter to the initDist instance variable
    public void addToInitDist(ArrayList<String> firstWords) {
        initDist.addAll(firstWords);    //Uses ArrayList's addAll() method
    }
    //Adds every word of every song to the Graph
    public void addDirectedEdges(ArrayList<String> allWords) {
        //Creates edges between each vertex. The String is converted to Vertex
        for(int i = 0; i < allWords.size() - 1; i++) {
            //Uses this method from Graph class
            transMtx.addDirectedEdge(allWords.get(i), allWords.get(i + 1), 0);
        }
    }
    //Randomly walks the Graph to generate a song
    public ArrayList<String> randomWalk(int lgth, long seed) {
        //ArrayList containing all of the randomly chosen lyrics
        ArrayList<String> c = new ArrayList<String>();
        //Declare and initialize a variable of the Random class
        Random rn = new Random(seed);
        //Picks a random first word
        String s = initDist.get(rn.nextInt(initDist.size()));
        //Iteration variable for while loop
        int i = 0;
        //Adds the first word into the lyrics ArrayList
        c.add(s);
        //For a chosen amount of words in the song....
        while(i < lgth - 1) {
            //This ArrayList is a list of all of the neighbors of s
            System.out.println(s);
            ArrayList<String> y = transMtx.getNeighbors(transMtx.getVertex(s));
            System.out.println("y = " + y);
            //If there are no neigbors, end loop early
            if(y.size() == 0) {
                //Return the lyrics
                return c;
            } else {
                //Else choose a random neighbor
                s = y.get(rn.nextInt(y.size()));
                //And add it to the lyrics ArrayList
                c.add(s);
            }
            //Go through next iteration of loop
            i++;
        }
        //Return the final set of lyrics
        return c;
    }
}
