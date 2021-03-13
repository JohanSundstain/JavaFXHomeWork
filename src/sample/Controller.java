package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.fileloader.FileLoader;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    private ObservableList<FileLoader> data = FXCollections.observableArrayList();

    private boolean rbClick;
    private FileLoader loader;
    private  ArrayList<FileLoader> li;
    //Кнопки
    @FXML
    private Button button, button1;

    @FXML
    private RadioButton javaButton, fxmlButton;

    @FXML
    private TableView<FileLoader> table;

    @FXML
    private TableColumn<FileLoader,Integer> number;

    @FXML
    private TableColumn<FileLoader,String> name;

    @FXML
    private TableColumn<FileLoader,String> modules;

    @FXML
    private TextField textField;



    public void onClick(ActionEvent actionEvent) {
        data.clear();
    }


    public void onClick_1(ActionEvent actionEvent) {
        data.clear();
        init();
        number.setCellValueFactory(new PropertyValueFactory<FileLoader, Integer>("number"));
        name.setCellValueFactory(new PropertyValueFactory<FileLoader, String>("name"));
        modules.setCellValueFactory(new PropertyValueFactory<FileLoader, String>("modules"));
        table.setItems(data);

    }

    private void init() {
        loader = new FileLoader(textField.getText());
        li = loader.getList(rbClick);
        for (FileLoader f : li){
            data.add(f);
        }
        li.clear();
    }

    public void clickRb1(ActionEvent actionEvent) {
        if (javaButton.isSelected())
        {
            rbClick = true;
            fxmlButton.setSelected(false);
        }
    }

    public void clickRb2(ActionEvent actionEvent) {
        if (fxmlButton.isSelected())
        {
            rbClick = false;
            javaButton.setSelected(false);
        }
    }

    public void onClick_2(ActionEvent actionEvent) throws IOException {
        for (FileLoader f : li){
            DataBaseLoader.insert(f.getName(),f.getModules());
        }
        data.clear();
        li.clear();

    }
}
