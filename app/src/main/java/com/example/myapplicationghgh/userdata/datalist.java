package com.example.myapplicationghgh.userdata;

public class datalist {
    String title, Discription, suger,calories,fat, cholistrol;


    public datalist(String title, String discription, String suger, String calories, String fat, String cholistrol) {
        this.title = title;
        Discription = discription;
        this.suger = suger;
        this.calories = calories;
        this.fat = fat;
        this.cholistrol=cholistrol;
    }

    public String getCholistrol() {
        return cholistrol;
    }

    public void setCholistrol(String cholistrol) {
        this.cholistrol = cholistrol;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }

    public String getSuger() {
        return suger;
    }

    public void setSuger(String suger) {
        this.suger = suger;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }
}
