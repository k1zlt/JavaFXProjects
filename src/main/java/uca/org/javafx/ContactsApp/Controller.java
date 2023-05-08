package uca.org.javafx.ContactsApp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    private ListView<Contact> contactsListView;
    private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField surnameField;

    public void initialize() {
        try {
            contacts.add(new Contact("Firuz", "Azizbekov", "934002790", "firuz.azizbekov18@gmail.com"));
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
                    }
                }
        );
    }

    @FXML
    void saveBtnClick(MouseEvent event) {
        for (Contact c: contacts) {
            if (c.getPhoneNumber().equals(phoneNumberField.getText())) {
                System.out.println("This Phone Number already exists.");
                return;
            }
        }


        try {
            Contact selectedItem = contactsListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                contacts.remove(selectedItem);
            }
            contacts.add(new Contact(nameField.getText(), surnameField.getText(), phoneNumberField.getText(), emailField.getText()));
        } catch (Exception e) {
            nameField.setText("Name");
            phoneNumberField.setText("XXXXXXXXX");
            return;
        }
        nameField.setText("");
        surnameField.setText("");
        phoneNumberField.setText("");
        emailField.setText("");
    }
}
