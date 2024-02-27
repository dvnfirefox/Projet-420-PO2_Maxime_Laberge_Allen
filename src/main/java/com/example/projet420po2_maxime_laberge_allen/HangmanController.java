package com.example.projet420po2_maxime_laberge_allen;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HangmanController {
  @FXML private Label word;
  @FXML private Label attemp;
  @FXML private Label score;
  @FXML private Label winningState;
  @FXML private Label nameLabel;
  @FXML private TextField nameTextfield;
  @FXML private Button continueButton;
  private String name;
  private boolean gameComplete;
  Word session = new Word();
  private List<Event> buttonList = new ArrayList<>();


    @FXML
    protected void start() throws IOException { // start a new game and reset the score and choose a new word
       word.setText(session.wordRandom());
        for(Event test: buttonList){
            ((Control) test.getSource()).setDisable(false);
        }
        buttonList.clear();
        session.setAttempt(0);
        session.setScore(0);
        attemp.setText("0");
        score.setText("0");
        winningState.setText("");
        continueButton.setOpacity(0);
        continueButton.setDisable(true);
        gameComplete = false;
                                                        //create the name prompt dialog
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("nameDialog.fxml"));
        DialogPane nameDialog = fxmlLoader.load();
        HangmanController hangmanController = fxmlLoader.getController();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(nameDialog);
        dialog.setTitle("New game");
        Optional<String> result = dialog.showAndWait();

    }
   @FXML
    protected void letterClick(Event buttonId){ // when a letter is choosen it will disable the coresponding letter and check is the letter is in the word or not
        if(gameComplete||session.getCurrentWord() == null) {
            return;
        }
        String check = session.wordCheck(((Control)buttonId.getSource()).getId());
       word.setText(check);
       buttonList.add(buttonId);
        ((Control) buttonId.getSource()).setDisable(true);
        if(!check.contains("*")){
            System.out.println("you win");
            session.setScore(session.getScore() + 5);
            winningState.setText("You win");
            continueButton.setOpacity(1);
            continueButton.setDisable(false);
            gameComplete = true;
        }
        if(session.getAttempt() >= 7){
            gameComplete = true;
            winningState.setText("You lose");
            word.setText(session.getCurrentWord());
        }

       score.setText(String.valueOf(session.getScore())); // refresh the score and attemps
       attemp.setText(String.valueOf(session.getAttempt()));

    }
    @FXML
    protected void newWord(){ // continue game with a new word
        word.setText(session.wordRandom());
        for(Event test: buttonList){
            ((Control) test.getSource()).setDisable(false);
        }
        buttonList.clear();
        session.setAttempt(0);
        attemp.setText("0");
        winningState.setText("");
        continueButton.setOpacity(0);
        continueButton.setDisable(true);
        gameComplete = false;
    }


}