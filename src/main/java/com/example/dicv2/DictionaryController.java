package com.example.dicv2;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.io.IOException;
import java.util.Map;

public class DictionaryController {
    @FXML
    private TextField myTextInPut;
    @FXML
    private TextFlow show;
    @FXML
    private Button delete;
    @FXML
    private Button useAPI;
    @FXML
    private Button search;
    @FXML
    private Button spell;
    @FXML
    private Button add;
    ObservableList<WordAdvanced> wordShow = FXCollections.observableArrayList();
    @FXML
    private ListView<WordAdvanced> listView = new ListView<WordAdvanced>(wordShow);
    private WordList a = new WordList();

    private void FormatshowText(Text text, int i) {
        //Text word
        if (i == 4) {
            text.setFont(Font.font("Roboto Blacak", FontWeight.BOLD, 20));
            text.setFill(Color.BLACK);
            text.setStroke(Color.BLACK);
            text.setStrokeWidth(0.7);
        }
        //Text pronunce
        if (i == 3) {
            text.setFont(Font.font("Roboto Blacak", 12));
            text.setFill(Color.BLUE);
            text.setStroke(Color.BLACK);
            text.setStrokeWidth(0);
        }
        //Text kind
        if (i == 2) {
            text.setFont(Font.font("Roboto Blacak", FontWeight.BOLD, 15));
            text.setFill(Color.RED);
            text.setStroke(Color.BLACK);
            text.setStrokeWidth(0.1);
        }
        //Text explain
        if (i == 1) {
            text.setFont(Font.font("Roboto Blacak", 13));
            text.setFill(Color.RED);
            text.setStroke(Color.BLACK);
            text.setStrokeWidth(0);
        }
        //Text example
        if (i == 0) {
            text.setFont(Font.font("Roboto Blacak", FontPosture.ITALIC, 12));
            text.setFill(Color.RED);
            text.setStroke(Color.BLACK);
            text.setStrokeWidth(0);
        }
    }

    public DictionaryController() throws IOException {
    }

    public void InputText(ActionEvent event) {
        wordShow.removeAll(wordShow);
        String word = myTextInPut.getText();
        for (Map.Entry<Integer, WordAdvanced> entry : a.Words.entrySet())
            if (entry.getValue().getWord().startsWith(word)) {
                wordShow.add(entry.getValue());
            }

        listView.setItems(wordShow);
        listView.setCellFactory(param -> new ListCell<WordAdvanced>() {
            @Override
            protected void updateItem(WordAdvanced item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getWord() == null) {
                    setText(null);
                } else {
                    setText(item.getWord());
                }
            }
        });
    }

    public void Choose(ActionEvent event) {
        show.getChildren().clear();
        WordAdvanced temp = listView.getSelectionModel().getSelectedItem();
        Text word = new Text("  " + temp.getWord());
        Text pronunce;
        Text kind = new Text();
        Text explain = new Text();
        Text example = new Text();
        FormatshowText(word, 4);
        if (temp.getPronunce() != "") {
            pronunce = new Text("\n  " + temp.getPronunce());
            FormatshowText(pronunce, 3);
        } else {
            pronunce = new Text(temp.getPronunce());
        }

        show.getChildren().addAll(word, pronunce);
        for (int i = 0; i < temp.explains.size(); i++) {
            kind = new Text(temp.explains.get(i).getKind());
            explain = new Text(temp.explains.get(i).getExplain());
            example = new Text(temp.explains.get(i).getExample());
            if (temp.explains.get(i).getKind() != "") {
                kind = new Text("\n *" + temp.explains.get(i).getKind());

            }
            if (temp.explains.get(i).getExplain() != "") {
                explain = new Text("\n   " + (i+1) + ". " + temp.explains.get(i).getExplain());
            }
            if(temp.explains.get(i).getExample() != "") {
                example = new Text("\n     + " + temp.explains.get(i).getExample());
            }
            FormatshowText(explain, 1);
            FormatshowText(kind, 2);
            FormatshowText(example, 0);
            show.getChildren().addAll(kind, explain, example);
        }
    }

    public void DeleteButton(ActionEvent event) {
        listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
        show.getChildren().clear();
        //delete in Words (WordList)
    }

    public void SpellButton(ActionEvent event) {
        // Normal voice.
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");


        Voice voice = VoiceManager.getInstance().getVoice("kevin16");

        if (voice != null) {
            voice.allocate();
            voice.speak(listView.getSelectionModel().getSelectedItem().getWord());
            voice.deallocate();
        }
    }

    public void UseAPI(ActionEvent event) throws IOException {
        String target = myTextInPut.getText();
        Text API = new Text("\n   Use API: " + GoogleTranslate.translate("vi", target));
        API.setFont(Font.font("Roboto Blacak", FontWeight.BOLD, 15));
        API.setFill(Color.GREEN);
        show.getChildren().add(API);
    }

    public void AddButton(ActionEvent event) {
    }
}