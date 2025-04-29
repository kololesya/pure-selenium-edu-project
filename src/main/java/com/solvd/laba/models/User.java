package com.solvd.laba.models;

public class User {
    private String name;
    private String email;
    private String password;
    private String birthDay;
    private String birthMonth;
    private String birthYear;
    private String lastName;
    private String company;
    private String address1;
    private String address2;
    private String country;
    private String state;
    private String city;
    private String zipcode;
    private String mobilePhone;

    private User(UserBuilder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.birthDay = builder.birthDay;
        this.birthMonth = builder.birthMonth;
        this.birthYear = builder.birthYear;
        this.lastName = builder.lastName;
        this.company = builder.company;
        this.address1 = builder.address1;
        this.address2 = builder.address2;
        this.country = builder.country;
        this.state = builder.state;
        this.city = builder.city;
        this.zipcode = builder.zipcode;
        this.mobilePhone = builder.mobile;
    }

    public static class UserBuilder {
        private String name;
        private String email;
        private String password;
        private String birthDay;
        private String birthMonth;
        private String birthYear;
        private String lastName;
        private String company;
        private String address1;
        private String address2;
        private String country;
        private String state;
        private String city;
        private String zipcode;
        private String mobile;

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setBirthDay(String birthDay) {
            this.birthDay = birthDay;
            return this;
        }

        public UserBuilder setBirthMonth(String birthMonth) {
            this.birthMonth = birthMonth;
            return this;
        }

        public UserBuilder setBirthYear(String birthYear) {
            this.birthYear = birthYear;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setCompany(String company) {
            this.company = company;
            return this;
        }

        public UserBuilder setAddress1(String address1) {
            this.address1 = address1;
            return this;
        }

        public UserBuilder setAddress2(String address2) {
            this.address2 = address2;
            return this;
        }

        public UserBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public UserBuilder setState(String state) {
            this.state = state;
            return this;
        }

        public UserBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public UserBuilder setZipcode(String zipcode) {
            this.zipcode = zipcode;
            return this;
        }

        public UserBuilder setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getBirthDay() { return birthDay; }
    public String getBirthMonth() { return birthMonth; }
    public String getBirthYear() { return birthYear; }
    public String getLastName() { return lastName; }
    public String getCompany() { return company; }
    public String getAddress1() { return address1; }
    public String getAddress2() { return address2; }
    public String getCountry() { return country; }
    public String getState() { return state; }
    public String getCity() { return city; }
    public String getZipcode() { return zipcode; }
    public String getMobilePhone () { return mobilePhone; }
}
