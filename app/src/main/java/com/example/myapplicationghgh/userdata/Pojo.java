package com.example.myapplicationghgh.userdata;

import java.util.concurrent.locks.Condition;

public class Pojo {
    String Name, Age, Email, Password, Condition, Goal,Id, Weight;
    String Bmi;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getGoal() {
        return Goal;
    }

    public void setGoal(String goal) {
        Goal = goal;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public Pojo(String weight, String condition, String goal, String id){
        Weight=weight;
        Condition=condition;
        Goal=goal;
Id=id;
    }


    public Pojo(String name, String age, String email, String password, String condition, String bmi, String goal) {
        Name = name;
        Age = age;
        Email = email;
        Password = password;
        Bmi=bmi;
Condition=condition;
Goal=goal;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }

    public String getBmi() {
        return Bmi;
    }

    public void setBmi(String bmi) {
        Bmi = bmi;
    }
}