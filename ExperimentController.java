//Packages needed to read and write files
import java.util.Scanner;
import java.io.IOException;
import java.io.FileReader;
import java.util.*;
import java.io.*;
public class ExperimentController {
    //Contains the first words of each line of each song
    private ArrayList<String> firstWord;
    //Contains all of the words of every song
    private ArrayList<String> allWords;
    public ExperimentController() {
        //Initializes the instance variables
        firstWord = new ArrayList<String>();
        allWords = new ArrayList<String>();
    }
    //Main method that runs the program
    public static void main(String[] args) {
        ExperimentController EC = new ExperimentController();
        EC.run(args);
    }
    //Uses command line arguments
    public void run(String[] m) {
        //Instance of MarkovModel class
        MarkovModel MM = new MarkovModel();
        //A faster way to read through a large number of files
        File folder = new File("lyrics/");
        //Array of files in question
        File[] listOfFiles = folder.listFiles();
        
        //Calls readLyrics for every single .txt file
        for(int j = 0; j < listOfFiles.length; j++) {
            readLyrics(listOfFiles[j].getName());
            //Adds the elements into the MarkovModel class
            MM.addToInitDist(firstWord);
            MM.addDirectedEdges(allWords);
        }
        
        
        try{
            //Write the first song an output file
            PrintWriter a = new PrintWriter("First Song.txt");
            //ArrayList contains the resulting lyrics determined by randomWalk
            ArrayList<String> z = MM.randomWalk(200, Integer.parseInt(m[0]));
            //Prints to an output .txt file
            for(int k = 0; k < z.size(); k++) {
                //Prints word
                a.print(z.get(k));
                //Prints space between those words
                a.print(" ");
            }
            //Close the PrintWriter so the file can be created
            a.close();
            //Write the first song an output file
            PrintWriter b = new PrintWriter("Second Song.txt");
            //ArrayList contains the resulting lyrics determined by randomWalk
            ArrayList<String> y = MM.randomWalk(200, Integer.parseInt(m[1]));
            //Prints to an output .txt file
            for(int k = 0; k < y.size(); k++) {
                //Prints word
                b.print(y.get(k));
                //Prints space between those words
                b.print(" ");
            }
            //Close the PrintWriter so the file can be created
            b.close();
            //Write the first song an output file
            PrintWriter c = new PrintWriter("Third Song.txt");
            //ArrayList contains the resulting lyrics determined by randomWalk
            ArrayList<String> x = MM.randomWalk(200, Integer.parseInt(m[2]));
            //Prints to an output .txt file
            for(int k = 0; k < x.size(); k++) {
                //Prints word
                c.print(x.get(k));
                //Prints space between those words
                c.print(" ");
            }
            //Close the PrintWriter so the file can be created
            c.close();
            //Write the first song an output file
            PrintWriter d = new PrintWriter("Fourth Song.txt");
            //ArrayList contains the resulting lyrics determined by randomWalk
            ArrayList<String> w = MM.randomWalk(200, Integer.parseInt(m[3]));
            //Prints to an output .txt file
            for(int k = 0; k < w.size(); k++) {
                //Prints word
                d.print(w.get(k));
                //Prints space between those words
                d.print(" ");
            }
            //Close the PrintWriter so the file can be created
            d.close();
            //Write the first song an output file
            PrintWriter e = new PrintWriter("Fifth Song.txt");
            //ArrayList contains the resulting lyrics determined by randomWalk
            ArrayList<String> v = MM.randomWalk(200, Integer.parseInt(m[4]));
            //Prints to an output .txt file
            for(int k = 0; k < v.size(); k++) {
                //Prints word
                e.print(v.get(k));
                //Prints space between those words
                e.print(" ");
            }
            //Close the PrintWriter so the file can be created
            e.close();
        } catch(Exception e) {
        }
    }
    //Reads the lyrics of the input file
    public void readLyrics(String pathToFile) {
        //Declares an instance of the Scanner class
        Scanner sc = null;
        firstWord = new ArrayList<String>();
        allWords = new ArrayList<String>();
        try{
            //Reads the input file using Scanner class
            sc = new Scanner(new FileReader("lyrics/" + pathToFile));
            //While there is a next line...
            while(sc.hasNextLine()) {
                //Make the whole line a String
                String line = sc.nextLine();
                //Declare and initialize another scanner to read each word
                Scanner str = new Scanner(line);
                //At the start of each line means the first word will appear
                boolean t = true;
                while(str.hasNextLine()) {
                    //Stores the next word in the line
                    String word = str.next();
                    //Turn the word into a character array
                    char[] c = word.toCharArray();
                    //StringBuilder makes a string out of character array
                    StringBuilder sb = new StringBuilder();
                    //For the length of the character array...
                    for(int i = 0; i < c.length; i++) {
                        //If the letter is a capital letters...
                        if(c[i] > 64 && c[i] < 91) {
                            //Store that letter
                            char g = c[i];
                            //Convert it into its ASCII number
                            int ascii = (int) g;
                            //Make it a lowercase letter
                            ascii = ascii + 32;
                            //Convert it back to a character
                            c[i] = (char) ascii;
                        }
                    }
                    //If the letter is a lowercase letter and not other
                    //special characters...
                    for(int j = 0; j < c.length; j++) {
                        //Add it to the StringBuilder class
                        if(c[j] > 96 && c[j] < 123 && c[j] != ' ') {
                            sb.append(c[j]);
                        }
                    }
                    //Add the word to the allWords ArrayList
                    allWords.add(sb.toString());
                    //If it's a new line....
                    if(t) {
                        //We are dealing with the first word of the line
                        //So add it to the firstWord ArrayList
                        firstWord.add(sb.toString());
                        System.out.println(sb.toString());
                        //No longer first word so it is false
                        t = false;
                    }
                }
            }
        } catch(Exception e){
        }
        System.out.println("AllWords = " + allWords);
        System.out.println("FirstWord = " + firstWord);
    }
}
