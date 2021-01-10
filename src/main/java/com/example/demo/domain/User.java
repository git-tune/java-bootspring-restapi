package com.example.demo.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

  @Id
  private String id;
  private String name;
  private String phoneNumber;
  private Date birthday;
  private String email;
  private String postalCode;
  private String address;
  private int numberOfpurchases;
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

  public int getNumberOfpurchases() {
    return numberOfpurchases;
  }

  public void setNumberOfpurchases(int numberOfpurchases) {
    this.numberOfpurchases = numberOfpurchases;
  }

  public Date getLastPurchaseDate() {
    return lastPurchaseDate;
  }

  public void setLastPurchaseDate(Date lastPurchaseDate) {
    this.lastPurchaseDate = lastPurchaseDate;
  }

  public User(String id, String name, String phoneNumber, Date birthday, String email, String postalCode,
		String address, int numberOfpurchases, Date lastPurchaseDate) {
      super();
      setId(id);
      setName(name);
      setPhoneNumber(phoneNumber);
      setBirthday(birthday);
      setPostalCode(postalCode);
      setAddress(address);
      setNumberOfpurchases(numberOfpurchases);
      setLastPurchaseDate(lastPurchaseDate);
	}
}