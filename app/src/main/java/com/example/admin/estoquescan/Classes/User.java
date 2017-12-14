package com.example.admin.estoquescan.Classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;


public class User {
    private String username, image;
    private int id;
    private int cargo;
    private static User savedUser = null;

    public static User getSavedUser(){
        if(savedUser == null)
            savedUser = new User();
        return savedUser;
    }

    public static void setSavedUser(User user){
        savedUser = user;
    }

    public Drawable getImage(Context context) {
        return decode(image, context);
    }

    public String getImageString(){ return image; }

    public void setImage(String image) {
        this.image = image;
    }

    private static Drawable decode(String code, Context context){
        byte[] decodeString = Base64.decode(code,Base64.DEFAULT);
        Bitmap decodeByte = BitmapFactory.decodeByteArray(decodeString,0,decodeString.length);
        return new BitmapDrawable(context.getResources(), decodeByte);
    }

    public int getCargo() {return cargo;}

    public void setCargo(int cargo) {this.cargo = cargo;}

    public String getUsername() {return username;}

    public void setUsername(String user) {this.username = user;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}
}
