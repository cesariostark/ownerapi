package cl.deloitte.api.ownerservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//Modelo de datos
@Entity
public class Owner {

    @Id
    private String ownerId; // primary key

    private String name;
    private String firstLastName;
    private String secondLastName;
    private String licensePlate;
    private String phoneNumber;
    private String email;

    public Owner() {
        //default constructor
    }

    public Owner(String ownerId, String name, String firstLastName, String secondLastName, String licensePlate, String phoneNumber, String email) {
        this.ownerId = ownerId;
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.licensePlate = licensePlate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerId='" + ownerId + '\'' +
                ", name='" + name + '\'' +
                ", firstLastName='" + firstLastName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
