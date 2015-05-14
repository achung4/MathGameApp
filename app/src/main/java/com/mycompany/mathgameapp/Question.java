package com.mycompany.mathgameapp;

import java.util.Random;

/**
 *  This class creates a question depending on what the difficulty is.
 */
public class Question {
    /**
     *  Instant Fields and Constants
     */
    private int firstDigit;
    private int secondDigit;
    private int min;
    private int max;
    private int theAnswer;
    private String theQuestion;
    public static final int LEVEL_ONE = 1;
    public static final int LEVEL_TWO = 2;
    public static final int LEVEL_THREE = 3;

    public Question(int min, int max){
        this.min = min;
        this.max = max;
    }
    /**
     *  This method will generate a question
     *  @param level the level of the game
     */
    public void generateQuestion(int level)
    {
        // addition
        if(level == LEVEL_ONE){
            firstDigit = generateNumber(min, max);
            secondDigit = generateNumber(min, max);

            theAnswer = firstDigit + secondDigit;
            theQuestion = "" + firstDigit + " + " + secondDigit;
        }

        // subtraction
        else if(level == LEVEL_TWO){
            firstDigit = generateNumber(min, max);
            secondDigit = generateNumber(min, max);

            if(firstDigit > secondDigit) {
                theAnswer = firstDigit - secondDigit;
                theQuestion = "" + firstDigit + " - " + secondDigit;
            }
            else {
                theAnswer = secondDigit - firstDigit;
                theQuestion = "" + secondDigit + " - " + firstDigit;
            }
        }

        // multiplication
        else if(level == LEVEL_THREE){
            firstDigit = generateNumber(min, max);
            secondDigit = generateNumber(min, max);

            theAnswer = firstDigit * secondDigit;
            theQuestion = "" + firstDigit + " * " + secondDigit;
        }
    }

    /**
     This method will generate a random number from
     @param min the minimum value
     @param max the maximum value
     @return a random integer
     */
    private int generateNumber(int min, int max){
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    /**
     This method will create the string version of the qestion
     @return the question
     */
    public String getQuestion(){
        return theQuestion;
    }

    /**
     This method will return an answer with the sum or difference of the first digit and second digit
     @return the answer
     */
    public int getAnswer(){
        return theAnswer;
    }
}
