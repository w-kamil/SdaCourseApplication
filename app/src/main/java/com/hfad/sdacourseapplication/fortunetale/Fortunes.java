package com.hfad.sdacourseapplication.fortunetale;

import java.util.ArrayList;
import java.util.List;



public class Fortunes {

    private List <String> fortunes = new ArrayList<>();{
        fortunes.add("Jestes zwyciezca");
        fortunes.add("Bedziesz szczesliwy");
        fortunes.add("Bedziesz zdrowy");
    }

    public String getFortune(int fortuneNr){
        return fortunes.get(fortuneNr);
    }

    public int getCount(){
        return fortunes.size();
    }
}
