package com.example.myrv2;

public class contact_Model {
    int img;
    String name,number;

    contact_Model(int img,String name,String number){
        this.img = img;
        this.name = name;
        this.number = number;
    }

    contact_Model(String name,String number){

        this.name = name;
        this.number = number;
    }
}
