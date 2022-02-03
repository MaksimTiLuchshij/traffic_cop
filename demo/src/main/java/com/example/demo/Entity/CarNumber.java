package com.example.demo.Entity;


import java.util.Arrays;
import java.util.List;

public class CarNumber {
    private static final List<Character> lettersList = Arrays.asList('А','Е','Т','О','Р','Н','У','К','Х','С','В','М');
    private String carNumberStr;
    private String carSymbolStr;
    private static final String region = " 116 RUS";

    public CarNumber() {
    }

    public CarNumber(String carNumberStr, String carSymbolStr) {
        this.carNumberStr = carNumberStr;
        this.carSymbolStr = carSymbolStr;
    }

    public void setCarNumberStr(String carNumberStr) {
        this.carNumberStr = carNumberStr;
    }

    public void setCarSymbolStr(String carSymbolStr) {
        this.carSymbolStr = carSymbolStr;
    }

    public String getCarNumberStr() {
        return carNumberStr;
    }

    public static List<Character> getLettersList() {
        return lettersList;
    }

    public static String getRegion() {
        return region;
    }

    public String getCarSymbolStr() {
        return carSymbolStr;
    }
    @Override
    public String toString() {
        return new StringBuilder(carSymbolStr).insert(carSymbolStr.length()-2, carNumberStr).toString() + region;
    }
}
