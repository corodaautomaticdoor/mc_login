package com.coroda.mclogin.model.thirtparthy;

import lombok.Data;

@Data
public class Person {

    private long idPerson;
    private String typeDocument;
    private String typePerson;
    private long numberDocument;
    private String name;
    private String lastName1;
    private String lastName2;
    private String socialReason;
    private String address;
    private String email;
    private long phone;

    public void PersonDniCarnet(long idPerson, String typeDocument, String typePerson, long numberDocument, String socialReason, String address, String email, long phone) {
        this.idPerson = idPerson;
        this.typeDocument = typeDocument;
        this.typePerson = typePerson;
        this.numberDocument = numberDocument;
        this.socialReason = socialReason;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public void PersonRuc(long idPerson, String typeDocument, String typePerson, long numberDocument, String name, String lastName1, String lastName2, String address, String email, long phone) {
        this.idPerson = idPerson;
        this.typeDocument = typeDocument;
        this.typePerson = typePerson;
        this.numberDocument = numberDocument;
        this.name = name;
        this.lastName1 = lastName1;
        this.lastName2 = lastName2;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
}
