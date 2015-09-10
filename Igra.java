package com.example.tomo.zbrajalo;

import com.example.tomo.zbrajalo.Dijeljenje;
import java.util.Stack;

/**
 * Created by Tomo on 08/09/2015.
 */
public class Igra {
    UkupnoDijeljenje ud;
    Stack stog;
    Stack stog_za_redo;
    int mi_ukupno=0, vi_ukupno=0;
    {
        ud = new UkupnoDijeljenje();
        stog = new Stack();
        stog_za_redo = new Stack();
    }

    public int getMi_ukupno() {
        return mi_ukupno;
    }

    public void setMi_ukupno(int mi_ukupno) {
        this.mi_ukupno += mi_ukupno;
    }

    public int getVi_ukupno() {
        return vi_ukupno;
    }

    public void setVi_ukupno(int vi_ukupno) {
        this.vi_ukupno += vi_ukupno;
    }

    public void setMi_UkupnoMinus(int mi_ukupno){this.mi_ukupno -= mi_ukupno;}
    public void setVi_UkupnoMinus(int vi_ukupno){this.vi_ukupno -= vi_ukupno;}

}
