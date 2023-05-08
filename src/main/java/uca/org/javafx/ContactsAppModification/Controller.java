package uca.org.javafx.ContactsAppModification;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Controller {

    @FXML
    private ListView<Contact> contactsListView;
    private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField, pathField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField surnameField;

    @FXML
    private ImageView profilePic;

    public void initialize() throws FileNotFoundException {
        try {
            contacts.add(new Contact("Firuz", "Azizbekov", "934002790", "firuz.azizbekov18@gmail.com", "1.jpg"));
            contacts.add(new Contact("k1zlt_z", "", "934002791", "firuz.azizbekov18@gmail.com", "2.jpg"));
            contacts.add(new Contact("John", "Smith", "934002792", "firuz.azizbekov18@gmail.com", "3.jpg"));
            contacts.add(new Contact("Emily", "Johnson", "934002793", "firuz.azizbekov18@gmail.com", "4.jpg"));
            contacts.add(new Contact("David", "Lee", "934002794", "firuz.azizbekov18@gmail.com", "5.jpg"));
        } catch (Exception e) {
            System.out.println(e);
        }
        contactsListView.setItems(contacts);
        contactsListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Contact>() {
                    @Override
                    public void changed(ObservableValue<? extends Contact> observable, Contact oldValue, Contact newValue) {
                        nameField.setText(newValue.getName());
                        surnameField.setText(newValue.getSurname());
                        phoneNumberField.setText(newValue.getPhoneNumber());
                        emailField.setText(newValue.getEmail());
                        pathField.setText(newValue.getShortProfilePic());
                        if (!newValue.getShortProfilePic().equals("")) {
                            try {
                                profilePic.setImage(new Image(new FileInputStream(newValue.getProfilePic())));
                            } catch (FileNotFoundException e) {
                                System.out.println(e);
                            }
                        }
                    }
                }
        );
    }

    @FXML
    void saveBtnClick(MouseEvent event) {
//        for (Contact c: contacts) {
//            if (c.getPhoneNumber().equals(phoneNumberField.getText())) {
//                System.out.println("This Phone Number already exists.");
//                return;
//            }
//        }


        try {
            contacts.add(new Contact(nameField.getText(), surnameField.getText(), phoneNumberField.getText(), emailField.getText(), pathField.getText()));
            Contact selectedItem = contactsListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                contacts.remove(selectedItem);
            }
        } catch (Exception e) {
            nameField.setText("Name");
            phoneNumberField.setText("XXXXXXXXX");
            return;
        }
        nameField.setText("");
        surnameField.setText("");
        phoneNumberField.setText("");
        emailField.setText("");
        pathField.setText("");
        profilePic.setImage(null);
    }
}
