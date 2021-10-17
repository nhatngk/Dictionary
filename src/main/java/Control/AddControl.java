package Control;

import Object.WordAdvanced;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddControl {
    @FXML
    TextField word;
    @FXML
    TextField kind;
    @FXML
    TextField explain;
    @FXML
    TextField pronounce;
    @FXML
    TextArea example;
    public static WordAdvanced wordAdd;

    public String getValue(TextField x) {
        if (!x.getText().isEmpty()) {
            return (String) x.getText();
        }
        return "";
    }

    public void Confirm(ActionEvent event) {
        String wordS = getValue(word);
        String explainS = getValue(explain);
        String exampleS = example.getText().isEmpty() ? "" : example.getText();
        String pronounceS = getValue(pronounce);
        String kindS = getValue(kind);
        if (wordS != "" || explainS!= "") {
            wordAdd = new WordAdvanced(wordS, pronounceS);
            wordAdd.addExplain(kindS, explainS, exampleS);
        }
    }
}
