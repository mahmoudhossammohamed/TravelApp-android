package com.example.finalproject;

public class Model {
    private String cityName , cityPrice ;
            String cityDesc="Egypt's second-largest city with a population of around four million, Alexandria is the country's largest seaport and the center of much of its maritime activity. It is also one of the oldest cities in Egypt and lies around 225 kilometers northwest of Cairo.";
    private int cityImg;

    public Model(String cityName, String cityPrice,int cityImg,String cityDesc) {
        this.cityName = cityName;
        this.cityPrice = cityPrice;
        this.cityImg = cityImg;
        this.cityDesc= cityDesc;
    }

    public String getCityDesc() {
        return cityDesc;
    }

    public void setCityDesc(String cityDesc) {
        this.cityDesc = cityDesc;
    }

    public int getCityImg() {
        return cityImg;
    }

    public void setCityImg(int cityImg) {
        this.cityImg = cityImg;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityPrice() {
        return cityPrice;
    }

    public void setCityPrice(String cityPrice) {
        this.cityPrice = cityPrice;
    }
}
