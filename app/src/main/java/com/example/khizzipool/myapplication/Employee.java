package com.example.khizzipool.myapplication;

public class Employee {
    private String first_name;
    private String last_name;
    private String password;
    private String phoneNum;
    private String email;
    private String employee_type;
    private String id;

    public Employee()
    {

    }

    public Employee(String first_name, String last_name, String password, String phoneNum, String email, String employee_type,String id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
        this.employee_type = employee_type;
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public String getEmployee_type() {
        return employee_type;
    }

    public String getid() {
        return id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmployee_type(String employee_type) {
        this.employee_type = employee_type;
    }

    public void id(String id) {
        this.id = id;
    }
}
