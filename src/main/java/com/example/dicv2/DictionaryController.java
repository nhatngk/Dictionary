package com.example.dicv2;

import Object.*;
import Control.*;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class DictionaryController {
    private DictionaryApplication main;

    @FXML
    private TextField myTextInPut;
    @FXML
    private TextFlow show;
    ObservableList<WordAdvanced> wordShow = FXCollections.observableArrayList();
    @FXML
    private ListView<WordAdvanced> listView = new ListView<WordAdvanced>(wordShow);

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

    public void InputText(ActionEvent event) {
        wordShow.removeAll(wordShow);
        String word = myTextInPut.getText();
        List<String> f = WordList.trie.autocomplete(word);
        for (String element : f) {
            Integer temp = element.hashCode();
            if (WordList.Words.get(temp) != null) {
                wordShow.add(WordList.Words.get(temp));
            }
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

    public WordAdvanced Chosen() {
        WordAdvanced temp = listView.getSelectionModel().getSelectedItem();
        return temp;
    }

    public void Show() {
        show.getChildren().clear();
        WordAdvanced temp = Chosen();
        Text word = new Text("  " + temp.getWord());
        Text pronounce;
        Text kind = new Text();
        Text explain = new Text();
        Text example = new Text();
        FormatshowText(word, 4);
        if (temp.getPronounce() != "") {
            pronounce = new Text("\n  " + temp.getPronounce());
            FormatshowText(pronounce, 3);
        } else {
            pronounce = new Text(temp.getPronounce());
        }
        show.getChildren().addAll(word, pronounce);
        for (int i = 0; i < temp.explains.size(); i++) {
            kind = new Text(temp.explains.get(i).getKind());
            explain = new Text(temp.explains.get(i).getExplain());
            example = new Text(temp.explains.get(i).getExample());
            if (temp.explains.get(i).getKind() != "") {
                kind = new Text("\n *" + temp.explains.get(i).getKind());
            }
            if (temp.explains.get(i).getExplain() != "") {
                explain = new Text("\n   " + (i + 1) + ". " + temp.explains.get(i).getExplain());
            }
            if (temp.explains.get(i).getExample() != "") {
                example = new Text("\n     + " + temp.explains.get(i).getExample());
            }
            FormatshowText(explain, 1);
            FormatshowText(kind, 2);
            FormatshowText(example, 0);
            show.getChildren().addAll(kind, explain, example);
        }
    }

    public void UseAPI(MouseEvent event) throws IOException {
        String target;
        if (listView.getSelectionModel().isEmpty()) {
            target = myTextInPut.getText();
        } else {
            target = Chosen().getWord();
        }
        Text API = new Text("\n   Use API: " + GoogleTranslate.translate("vi", target));
        API.setFont(Font.font("Roboto Blacak", FontWeight.BOLD, 15));
        API.setFill(Color.GREEN);
        show.getChildren().add(API);
    }

    public void SearchButton(ActionEvent event) {
        Show();
    }

    public void DeleteButton(ActionEvent event) {
        WordAdvanced temp = Chosen();
        listView.getItems().remove(temp);
        show.getChildren().clear();
        WordList.delete(temp.getWord());
    }

    public void SpellButton(ActionEvent event) {
        // Normal voice.
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
            voice.speak(Chosen().getWord());
            voice.deallocate();
        }
    }

    public void AddButton(ActionEvent event) throws IOException {
        main.addStage();
        WordList.add(AddControl.wordAdd);
    }

    public void FixButton() throws IOException {
        FixControl.wordFix = Chosen();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DictionaryApplication.class.getResource("Fix.fxml"));
        AnchorPane x = loader.load();
        Scene scene = new Scene(x);
        stage.setTitle("Fix");
        stage.setScene(scene);
        stage.show();
    }
}