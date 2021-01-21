package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
  public User() {
  };

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "name")
  private String name;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "birthday")
  private Date birthday;

  @Column(name = "email")
  private String email;

  @Column(name = "postal_code")
  private String postalCode;

  @Column(name = "address")
  private String address;

  @Column(name = "number_of_purchases")
  private int numberOfPurchases;

  @Column(name = "last_purchase_date")
  private Date lastPurchaseDate;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getNumberOfPurchases() {
    return numberOfPurchases;
  }

  public void setNumberOfpurchases(int numberOfPurchases) {
    this.numberOfPurchases = numberOfPurchases;
  }

  public Date getLastPurchaseDate() {
    return lastPurchaseDate;
  }

  public void setLastPurchaseDate(Date lastPurchaseDate) {
    this.lastPurchaseDate = lastPurchaseDate;
  }

  public User(String id, String name, String phoneNumber, Date birthday, String email, String postalCode,
      String address, int numberOfPurchases, Date lastPurchaseDate) {
    super();
    setId(id);
    setName(name);
    setPhoneNumber(phoneNumber);
    setBirthday(birthday);
    setPostalCode(postalCode);
    setAddress(address);
    setNumberOfpurchases(numberOfPurchases);
    setLastPurchaseDate(lastPurchaseDate);
  }
}