package com.example.tomo.zbrajalo;

/**
 * Created by Tomo on 08/09/2015.
 */
public class UkupnoDijeljenje {
    private int ukupnaIgra = 162;

    public void ukupnaIgraPlus(){
        this.ukupnaIgra +=10;
    }
    public void ukupnaIgraMinus(){
        if (this.ukupnaIgra > 162)
            this.ukupnaIgra -= 10;
    }

    public int getUkupnaIgra() {
        return ukupnaIgra;
    }
    public void setUkupnaIgra(int ukupnaIgra){this.ukupnaIgra = ukupnaIgra;}

}
