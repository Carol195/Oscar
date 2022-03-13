package com.company;

import java.awt.*;

public class MenuIntem {

    private String name;
    private String movie;
    private String year;
    private Integer age;

    public MenuIntem(){

    }

    private MenuIntem(String name, int age, String movie, String year){
        this.name=name;
        this.age=age;
        this.movie=movie;
        this.year=year;
    }


    public static MenuIntem fromLine(String line) {
        String[] split= line.split(";\\s");
        return new MenuIntem(split[3],
                Integer.parseInt(split[2]),
                split[4],
                split[1]);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return "Name: "+name+ " | Idade: "+ age + " | Filme: "+ movie +" | Ano: "+year;

    }
}
