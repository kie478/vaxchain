package main;

import javafx.beans.property.SimpleStringProperty;

public class BlockData {
    private SimpleStringProperty firstName, lastName, previousHash, hash, age;

    public BlockData(String firstName, String lastName, String age, String previousHash, String hash)
    {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.age = new SimpleStringProperty(age);
        this.previousHash = new SimpleStringProperty(previousHash);
        this.hash = new SimpleStringProperty(hash);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getPreviousHash() {
        return previousHash.get();
    }

    public String getHash() {
        return hash.get();
    }

    public SimpleStringProperty hashProperty() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash.set(hash);
    }

    public String getAge() {
        return age.get();
    }

    public void setAge(String age) {
        this.age.set(age);
    }

    public SimpleStringProperty previousHashProperty() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash.set(previousHash);
    }
}
