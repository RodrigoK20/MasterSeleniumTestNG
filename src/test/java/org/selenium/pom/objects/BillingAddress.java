package org.selenium.pom.objects;

public class BillingAddress {

    private String firstName;
    private String lastName;
    private String addresssLineOne;
    private String city;
    private String postalCode;
    private String email;
    private String country;
    private String state;

    //default constructor
    public BillingAddress(){

    }

    //Override constructor
    public BillingAddress(String firstName, String lastName, String addresssLineOne, String city, String postalCode, String email){

        this.firstName = firstName;
        this.lastName = lastName;
        this.addresssLineOne = addresssLineOne;
        this.city = city;
        this.postalCode = postalCode;
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }

    public BillingAddress setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BillingAddress setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddresssLineOne() {
        return addresssLineOne;
    }

    public BillingAddress setAddresssLineOne(String addresssLineOne) {
        this.addresssLineOne = addresssLineOne;
        return this;
    }

    public String getCity() {
        return city;
    }

    public BillingAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public BillingAddress setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BillingAddress setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}
