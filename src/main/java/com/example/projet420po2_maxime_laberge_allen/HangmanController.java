package com.example.projet420po2_maxime_laberge_allen;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class HangmanController {
  @FXML private Label word;
  @FXML private Label attempt;
  @FXML private Label score;
  @FXML private Label winningState;
  @FXML private Label nameLabel;
  @FXML private Button continueButton;
  @FXML private Button a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z;
  @FXML private  List<Button> buttonList = new ArrayList<>();
  private boolean gameComplete;
  private boolean firstRun;
  Word session = new Word();
    @FXML
    protected void start() { // start a new game and reset the score and choose a new word
        if(!firstRun){
            createButtonlist();
            firstRun = true;
        }

       word.setText(session.wordRandom());
        for(Button test: buttonList){
            test.setDisable(false);
        }
        session.setAttempt(0);
        session.setScore(0);
        attempt.setText("0");
        score.setText("0");
        winningState.setText("");
        continueButton.setOpacity(0);
        continueButton.setDisable(true);
        gameComplete = false;
        askName();




    }
   @FXML
    protected void letterClick(Event buttonId){ // when a letter is chosen it will disable the corresponding letter and check is the letter is in the word or not
        if(gameComplete||session.getCurrentWord() == null) {
            return;
        }
        String check = session.wordCheck(((Control)buttonId.getSource()).getId());
       word.setText(check);
        ((Control) buttonId.getSource()).setDisable(true);
        if(!check.contains("*")){
            System.out.println("you win");
            session.setScore(session.getScore() + 5);
            winningState.setText("You win");
            continueButton.setOpacity(1);
            continueButton.setDisable(false);
            gameComplete = true;
        }
        if(session.getAttempt() >= 6){
            gameComplete = true;
            winningState.setText("You lose");
            word.setText(session.getCurrentWord());
        }

       score.setText(String.valueOf(session.getScore())); // refresh the score and attempts
       attempt.setText(String.valueOf(session.getAttempt()));

    }
    @FXML
    protected void nextWord(){ // continue game with a new word
        word.setText(session.wordRandom());
        for(Button test: buttonList){
            test.setDisable(false);
        }
        session.setAttempt(0);
        attempt.setText("0");
        winningState.setText("");
        continueButton.setOpacity(0);
        continueButton.setDisable(true);
        gameComplete = false;
    }
    public void askName(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setContentText("Name");
        dialog.setTitle("New game");
        dialog.setHeaderText("Enter your name");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(s -> nameLabel.setText(s));
    }
    @FXML
    protected void hint() {
        if(gameComplete||!firstRun) return;
       boolean letterCheck = false;
        String hintLetter = session.randomLetter();
        System.out.println(hintLetter);
        for(Button hintButton: buttonList){
            if(Objects.equals(hintButton.getId(), hintLetter)){
                hintButton.setDisable(true);
                String check = session.wordCheck(hintLetter);
                word.setText(check);
                if(!check.contains("*")){
                    System.out.println("you win");
                    session.setScore(session.getScore() + 5);
                    winningState.setText("You win");
                    continueButton.setOpacity(1);
                    continueButton.setDisable(false);
                    gameComplete = true;
                }
            }
        }

    }

    public  void createButtonlist(){
        buttonList.add(a);
        buttonList.add(b);
        buttonList.add(c);
        buttonList.add(d);
        buttonList.add(e);
        buttonList.add(f);
        buttonList.add(g);
        buttonList.add(h);
        buttonList.add(i);
        buttonList.add(j);
        buttonList.add(k);
        buttonList.add(l);
        buttonList.add(m);
        buttonList.add(n);
        buttonList.add(o);
        buttonList.add(p);
        buttonList.add(q);
        buttonList.add(r);
        buttonList.add(s);
        buttonList.add(t);
        buttonList.add(u);
        buttonList.add(v);
        buttonList.add(w);
        buttonList.add(x);
        buttonList.add(y);
        buttonList.add(z);
        System.out.println(buttonList.size());
    }


}