package com.example.dicv2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class DictionaryController {
    @FXML
    private TextField myTextInPut;
    private ListView listView;

    String word;
    public void Search(ActionEvent event) {
        word = myTextInPut.getText();
        WordAdvanced wordSearch = (WordAdvanced) WordList.search(word);

    }




    public void Add(ActionEvent event) {

    }
}