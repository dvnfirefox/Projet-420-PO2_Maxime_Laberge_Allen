package com.example.projet420po2_maxime_laberge_allen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Word {
    String[] wordBank = new String[] {"amazon", "thriller", "hangman", "design", "valley", "bugs", "paradox", "eccentric", "nostalgia", "euphoria", "green"};
    Random random = new Random();
    private String currentWord;
    private int score = 0;
    private int attempt = 0;
    private List<String> guess = new ArrayList<>();
    //private ArrayList<String> guess = new ArrayList<>();


    public String wordRandom(){ //choose a new word in the word bank to use in the current game
        currentWord = wordBank[random.nextInt(10)];
        guess.clear();
        return (wordHiding());


    }
    public String wordHiding(){ // check the word to find if a letter has been already found and if not it insert a * for a missing letter
        StringBuilder hided = new StringBuilder();
        for(char s: currentWord.toCharArray()){

            if (guess.contains(String.valueOf(s))){
                hided.append(s);
            }else{
                hided.append("*");
            }
        }
        return hided.toString();
    }
    public String wordCheck(String input){ // check if the selected letter is in the word and then add to score or to attempt accordingly
        guess.add(input);
        if(currentWord.contains(input)){
            score ++;

        }else{
            attempt++;
        }
        return wordHiding();

    }
    public String randomLetter(){
        String letter;
        do{
            letter = String.valueOf(currentWord.charAt(random.nextInt(currentWord.length())));
        }while(guess.contains(letter));

        return letter;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    public String getCurrentWord() {return currentWord;}

    public void setCurrentWord(String currentWord) {this.currentWord = currentWord;}

    public List<String> getGuess() {
        return guess;
    }

    public void setGuess(List<String> guess) {
        this.guess = guess;
    }
}


