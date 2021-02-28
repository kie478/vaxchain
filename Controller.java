package main;

import BlockChainData.Block;
import BlockChainData.BlockChain;
import BlockChainData.VaccinationData;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.w3c.dom.Text;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.net.URL;
import java.rmi.server.ExportException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller{

    BlockChain bChain = new BlockChain();

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField age;

    @FXML
    private TextField vaccinationHash;

    @FXML
    private TextField vaccinationID;

    @FXML
    private TextField vaccinationID2;

    @FXML
    private TextField demoFirstName;

    @FXML
    private TextField demoLastName;

    @FXML
    private GridPane gridPane;

    @FXML
    private TextField result;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextArea displayArea;

    @FXML
    private ListView listView;

    @FXML
    private TableView<BlockData> tableView;

    @FXML
    private TableColumn<BlockData, String> firstNameColumn;

    @FXML
    private TableColumn<BlockData, SimpleStringProperty> lastNameColumn;

    @FXML
    private TableColumn<BlockData, Integer> ageColumn;

    @FXML
    private TableColumn<BlockData, SimpleStringProperty> previousHashColumn;

    @FXML
    private TableColumn<BlockData, SimpleStringProperty> hashColumn;

    @FXML
    public void changeDetails(ActionEvent e){
        Button button = (Button) e.getSource();
        String sumbit = button.getText();

        if(sumbit.equals("Change")){
            try {
                if (Integer.parseInt(vaccinationID2.getText()) < 0 || Integer.parseInt(vaccinationID2.getText()) > bChain.chain.size()) {
                    throw new Exception("Vaccination ID does not exist!");
                }
                for (int i = 0; i < vaccinationID2.getText().length(); i++) {
                    if (Character.isLetter(vaccinationID2.getText().charAt(i))) {
                        throw new Exception("Invalid iD");
                    }
                }

                if (demoFirstName.getText().contentEquals("")) {
                    throw new Exception("First Name is blank, please enter a valid First Name!");
                }
                for (int i = 0; i < demoFirstName.getLength(); i++) {
                    if (!(Character.isLetter(demoLastName.getText().charAt(i)))) {
                        throw new Exception("Invalid First Name");
                    }
                }
                if (demoLastName.getText().contentEquals("")) {
                    throw new Exception("Last Name is blank, please enter a valid Last Name!");
                }
                for (int i = 0; i < demoLastName.getLength(); i++) {
                    if (!(Character.isLetter(demoLastName.getText().charAt(i)))) {
                        throw new Exception("Invalid Last Name");
                    }
                }

                int index = Integer.parseInt(vaccinationID2.getText());
                bChain.getBlock(index).getVaccData().setFirstName(demoFirstName.getText());
                bChain.getBlock(index).getVaccData().setLastName(demoLastName.getText());
                bChain.getBlock(index).calculateHash();
                vaccinationID.setText("");
                demoFirstName.setText("");
                demoLastName.setText("");
            } catch (Exception d) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(d.getMessage());
                alert.showAndWait();
            }
        }

        if(sumbit.equals("Check Validity")){
            if(bChain.isChainValid())
                result.setText("Chain Valid!!!");
            else
                result.setText("Chain Invalid!!!");
        }
    }

    @FXML
    public void onMouseClick(ActionEvent e) throws FileNotFoundException {
        Button button = (Button) e.getSource();
        String sumbit = button.getText();

        if(sumbit.equalsIgnoreCase("Submit")) {
            try {
                if (firstName.getText().contentEquals("")) {
                    throw new Exception("First Name is blank, please enter a valid First Name!");
                }
                for (int i = 0; i < firstName.getLength(); i++) {
                    if (!(Character.isLetter(firstName.getText().charAt(i)))) {
                        throw new Exception("Invalid First Name");
                    }
                }
                if (lastName.getText().contentEquals("")) {
                    throw new Exception("Last Name is blank, please enter a valid Last Name!");
                }
                for (int i = 0; i < lastName.getLength(); i++) {
                    if (!(Character.isLetter(lastName.getText().charAt(i)))) {
                        throw new Exception("Invalid Last Name");
                    }
                }
                for (int i = 0; i < age.getLength(); i++) {
                    if ((Character.isLetter(age.getText().charAt(i)))) {
                        throw new Exception("Invalid Age");
                    }
                }
                if (age.getText().contentEquals("")) {
                    throw new Exception("Age is blank, please enter a valid Age!");
                }
                if ((Integer.valueOf(age.getText()) > 110) || (Integer.valueOf(age.getText()) < 0)) {
                    throw new Exception("Invalid Age");
                }

                bChain.addBlock(new Block(new VaccinationData(firstName.getText(), lastName.getText(), Integer.parseInt(age.getText())), ""));

                firstName.setText("");
                lastName.setText("");
                age.setText("");
            } catch (Exception d) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(d.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void onDisplayCLick(ActionEvent e) {
        for(int i = 0; i < bChain.chain.size();i++) {
            System.out.println("----------------------------------------------------------------");
            System.out.println("Block [" + (i+1) + "]:");
            System.out.println("First Name: " + bChain.chain.get(i).getVaccData().getFirstName());
            System.out.println("Last Name: " + bChain.chain.get(i).getVaccData().getLastName());
            System.out.println("Age: " + bChain.chain.get(i).getVaccData().getAge());
            System.out.println("Previous Hash: " + bChain.chain.get(i).getPreviousHash());
            System.out.println("Hash: " + bChain.chain.get(i).getHash());
            System.out.println("----------------------------------------------------------------\n");
        }

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        previousHashColumn.setCellValueFactory(new PropertyValueFactory<>("previousHash"));
        hashColumn.setCellValueFactory(new PropertyValueFactory<>("Hash"));

        tableView.setItems(getBlockData());
    }

    @FXML
    public void validateIdInput(ActionEvent e){
        Button button = (Button) e.getSource();
        String word = button.getText();

        if(word.equalsIgnoreCase("Validate")){
            int vaccID = Integer.parseInt(vaccinationID.getText());
            String vaccHash = vaccinationHash.getText();
            if(bChain.getBlock(vaccID).getHash().equals(vaccHash)){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Valid!");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Invalid!");
                alert.showAndWait();
            }
        }
    }

    public ObservableList<BlockData> getBlockData() {
        ObservableList<BlockData> blockdata = FXCollections.observableArrayList();


        for(int i = 0; i < bChain.chain.size();i++)
        {
            blockdata.add(new BlockData(bChain.chain.get(i).getVaccData().getFirstName(),
                    bChain.chain.get(i).getVaccData().getLastName(),
                    String.valueOf(bChain.chain.get(i).getVaccData().getAge()),
                    bChain.chain.get(i).getPreviousHash(),
                    bChain.chain.get(i).getHash()));
        }

        return blockdata;
    }
}
