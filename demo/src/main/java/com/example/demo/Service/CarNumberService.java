package com.example.demo.Service;

import com.example.demo.Entity.CarNumber;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarNumberService {
    private ArrayList<CarNumber> oldCarNumbers = new ArrayList<>();
    public String random(){
        String numberStr;
        String symbolStr;
        do{
            numberStr = generateCarNumberStr();
            symbolStr = generateCarSymbolStr();
        }
        while (isRepeat(numberStr, symbolStr));
        return new StringBuilder(symbolStr).insert(symbolStr.length()-2,numberStr).toString() + CarNumber.getRegion();
    }
    private String generateCarNumberStr(){
        String numberStr = "";
        while (numberStr.length() != 3){
            Random rand = new Random();
            numberStr = numberStr + (rand.nextInt(10));
        }
        return numberStr;
    }
    private String generateCarSymbolStr(){
        String symbolStr = "";
        while (symbolStr.length() != 3){
            Random rand = new Random();
            symbolStr = symbolStr + CarNumber.getLettersList().get(rand.nextInt(CarNumber.getLettersList().size()));
        }
        return symbolStr;
    }


    public String next(){
        //Берем CarNumber, который сохранен в списке - последний номер, выданный методом random
        CarNumber carNumber = oldCarNumbers.get(oldCarNumbers.size()-1);
        //Инициализируем все необходимое для будущего номера
        String increaseNumber;
        String increaseSymbolStr = carNumber.getCarSymbolStr();
        CarNumber newCarNumber;
        //Увеличиваем номер до тех пор, пока номер не станет уникальным
        do{
            increaseNumber = increaseNumberStr(carNumber.getCarNumberStr());
            //Если отсчет начался нуля, нужно увеличить строку с буквами
            if(increaseNumber.equals("000")){
                increaseSymbolStr = increaseSymbolStr(carNumber.getCarSymbolStr());
            }
            //Фиксируем изменения в новом номере и добавляем его в список
            newCarNumber = new CarNumber(increaseNumber,increaseSymbolStr);
        }
        while (isRepeat(increaseNumber, increaseSymbolStr));
        return newCarNumber.toString();
    }
    private String increaseSymbolStr(String symbolStr){
        List<Character> lettersList = CarNumber.getLettersList();
        //Вводим переменные min и max чтобы найти самый ближайших и самый дальний символы относительно заменяемого символа
        int min = 1000;
        int max = -10;
        char arrayChar[] = symbolStr.toCharArray();
        for(int i = symbolStr.length()-1; i >= 0; i--){
            for (int j = 0; j < lettersList.size(); j++){
                if(arrayChar[i] < lettersList.get(j) && (min > lettersList.get(j)-arrayChar[i])){
                    min = lettersList.get(j)-arrayChar[i];
                }
                else if(arrayChar[i] > lettersList.get(j) && (max < arrayChar[i] - lettersList.get(j))){
                    max = arrayChar[i] - lettersList.get(j);
                }
            }
            //Если нашелся минимальный, меняем символ
            if(min != 1000){
                arrayChar[i] += min;
                break;
            }
            /*Если нет, значит заменяемый символ -
            последний символ в алфавите, по этому меняем его на самый первый символ в алфавите*/
            else
                arrayChar[i] -= max;
        }
        return new String(arrayChar);
    }
    private String increaseNumberStr(String numberStr){
        numberStr = String.valueOf(Integer.parseInt(numberStr) + 1);
        //Если число превысило 999, начинаем отсчет с нуля
        if(numberStr.equals("1000")){
            return "000";
        }
        //Если число не трехзначное, добавляем нули слева
        if(numberStr.length()!=3)
            numberStr = new String(new char[3-numberStr.length()]).replace("\0", "0") + numberStr;
        return numberStr;
    }
    //Сверяет сгенерированный номер со списком уже существующих номеров: true - номер уже существует, false - номер уникальный
    private boolean isRepeat(String numberStr, String symbolStr){
        CarNumber carNumber = new CarNumber(numberStr, symbolStr);
        for(int i = 0; i < oldCarNumbers.size(); i++){
            if(carNumber.toString().equals(oldCarNumbers.get(i).toString())){
                System.out.println("--------------РАВНЫ-------------");
                return true;
            }
        }
        oldCarNumbers.add(carNumber);
        return false;
    }
}
