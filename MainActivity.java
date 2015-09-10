package com.example.tomo.zbrajalo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.os.Handler;
import android.widget.Toast;

import java.util.Stack;


public class MainActivity extends AppCompatActivity {

    EditText mi, vi;

    Igra prva;
    {
        prva = new Igra();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mi = (EditText)findViewById(R.id.editText3);
        vi = (EditText)findViewById(R.id.editText4);

        mi.setFilters(new InputFilter[]{ new InputFilterMinMax(0, prva.ud.getUkupnaIgra())});
        vi.setFilters(new InputFilter[]{ new InputFilterMinMax(0, prva.ud.getUkupnaIgra())});


        mi.addTextChangedListener(watch_mi);
        vi.addTextChangedListener(watch_vi);
    }

    TextWatcher watch_mi = new TextWatcher(){

        @Override
        public void afterTextChanged(Editable arg0) {
            vi.addTextChangedListener(watch_vi);
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            vi.removeTextChangedListener(watch_vi);
        }

        @Override
        public void onTextChanged(CharSequence s, int a, int b, int c) {
            String str = s.toString();
            int mi_int,vi_int;
            if(TextUtils.isEmpty(str)){
                mi_int = 0;
            }
            else {
                mi_int = Integer.parseInt(str);
            }
            if (mi_int > prva.ud.getUkupnaIgra()){
                mi_int = prva.ud.getUkupnaIgra();
            }
            vi_int = prva.ud.getUkupnaIgra()-mi_int;
            vi.setText(Integer.toString(vi_int));
        }};

    TextWatcher watch_vi = new TextWatcher(){

        @Override
        public void afterTextChanged(Editable arg0) {
            mi.addTextChangedListener(watch_mi);
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            mi.removeTextChangedListener(watch_mi);
        }

        @Override
        public void onTextChanged(CharSequence s, int a, int b, int c) {
            String str = s.toString();
            int mi_int, vi_int;
            if(TextUtils.isEmpty(str)){
                vi_int = 0;
            }
            else {
                vi_int = Integer.parseInt(str);
            }
            if (vi_int > prva.ud.getUkupnaIgra()){
                vi_int = prva.ud.getUkupnaIgra();
            }
            mi_int = prva.ud.getUkupnaIgra()-vi_int;
            mi.setText(Integer.toString(mi_int));
        }};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickNovaIgra(View v){
        prva = new Igra();
        EditText et1;

        mi.removeTextChangedListener(watch_mi);
        vi.removeTextChangedListener(watch_vi);

        et1 = (EditText) findViewById((R.id.editText3));
        et1.setText("");
        et1 = (EditText) findViewById((R.id.editText4));
        et1.setText("");
        et1 = (EditText) findViewById((R.id.editText5));
        et1.setText("");
        et1 = (EditText) findViewById((R.id.editText6));
        et1.setText("");
        et1 = (EditText) findViewById((R.id.editText7));
        et1.setText("162");

        mi.addTextChangedListener(watch_mi);
        vi.addTextChangedListener(watch_vi);

    }

    public void novaIgra(){
        prva = new Igra();
        EditText et1;

        mi.removeTextChangedListener(watch_mi);
        vi.removeTextChangedListener(watch_vi);

        et1 = (EditText) findViewById((R.id.editText3));
        et1.setText("");
        et1 = (EditText) findViewById((R.id.editText4));
        et1.setText("");
        et1 = (EditText) findViewById((R.id.editText5));
        et1.setText("");
        et1 = (EditText) findViewById((R.id.editText6));
        et1.setText("");
        et1 = (EditText) findViewById((R.id.editText7));
        et1.setText("162");

        mi.addTextChangedListener(watch_mi);
        vi.addTextChangedListener(watch_vi);

    }

    public void onClickPlusDeset(View v){
        prva.ud.ukupnaIgraPlus();

        mi.setFilters(new InputFilter[]{ new InputFilterMinMax(0, prva.ud.getUkupnaIgra())});
        vi.setFilters(new InputFilter[]{new InputFilterMinMax(0, prva.ud.getUkupnaIgra())});

        EditText et = (EditText)findViewById((R.id.editText7));
        et.setText(Integer.toString(prva.ud.getUkupnaIgra()));
    }

    public void onClickMinuDeset(View v){
        prva.ud.ukupnaIgraMinus();

        mi.setFilters(new InputFilter[]{ new InputFilterMinMax(0, prva.ud.getUkupnaIgra())});
        vi.setFilters(new InputFilter[]{new InputFilterMinMax(0, prva.ud.getUkupnaIgra())});

        EditText et = (EditText)findViewById((R.id.editText7));
        et.setText(Integer.toString(prva.ud.getUkupnaIgra()));
    }

    public void getMiFromApp(View v){

        Dijeljenje di = new Dijeljenje();
        String txt,txt1;
        int miInt, viInt;
        EditText et=(EditText)findViewById(R.id.editText3);
        txt=et.getText().toString();
        et = (EditText)findViewById(R.id.editText4);
        txt1=et.getText().toString();

        if (txt.matches("")){
            miInt = 0;
        }
        else {
            miInt = Integer.parseInt(txt);
        }
        if (txt1.matches("")){
            viInt = 0;
        }
        else{
            viInt = Integer.parseInt(txt1);
        }


        di.setMi_dij(miInt);
        di.setVi_dij(viInt);

        if (di.getMi_dij()!=0 || di.getVi_dij()!=0){
            prva.stog.push(di);
        }

        prva.setMi_ukupno(miInt);
        prva.setVi_ukupno(viInt);

        et = (EditText) findViewById((R.id.editText5));
        et.setText(Integer.toString(prva.getMi_ukupno()));
        et = (EditText) findViewById((R.id.editText6));
        et.setText(Integer.toString(prva.getVi_ukupno()));

        mi.removeTextChangedListener(watch_mi);
        vi.removeTextChangedListener(watch_vi);

        et = (EditText) findViewById((R.id.editText3));
        et.setText("");
        et = (EditText) findViewById((R.id.editText4));
        et.setText("");

        mi.addTextChangedListener(watch_mi);
        vi.addTextChangedListener(watch_vi);

        prva.ud.setUkupnaIgra(162);
        et = (EditText) findViewById((R.id.editText7));
        et.setText(Integer.toString(prva.ud.getUkupnaIgra()));
        mi.setFilters(new InputFilter[]{ new InputFilterMinMax(0, prva.ud.getUkupnaIgra())});
        vi.setFilters(new InputFilter[]{new InputFilterMinMax(0, prva.ud.getUkupnaIgra())});

        if(prva.getMi_ukupno()!=prva.getVi_ukupno()){
            if(prva.getMi_ukupno()>1001&&prva.getMi_ukupno()>prva.getVi_ukupno()){
                krajIgrePobjednikMi();
                novaIgra();
            }
            if(prva.getVi_ukupno()>1001&&prva.getMi_ukupno()<prva.getVi_ukupno()){
                krajIgrePobjednikVi();
                novaIgra();
            }
        }

    }

    public void krajIgrePobjednikMi(){
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Igra je gotova:\nMI: "+prva.getMi_ukupno()+"\nVI: "+prva.getVi_ukupno()+"\nPobijedili ste!");
        dlgAlert.setTitle("Zbrajalo");
        dlgAlert.setPositiveButton("Nova igra", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {}
        });
        dlgAlert.setCancelable(true);
        dlgAlert.show();
    }

    public void krajIgrePobjednikVi(){
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Igra je gotova:\nMI: "+prva.getMi_ukupno()+"\nVI: "+prva.getVi_ukupno()+"\nIzgubili ste!");
        dlgAlert.setTitle("Zbrajalo");
        dlgAlert.setPositiveButton("Nova igra", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.show();
    }

    public void onClickStanjeIgre(View v){
        Dijeljenje d;
        String stanje = new String("");
        Stack pom_stog;
        pom_stog = new Stack();

        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setTitle("Stanje igre");

        while (!prva.stog.isEmpty()){
            d = (Dijeljenje) prva.stog.pop();
            pom_stog.push(d);
        }
        while (!pom_stog.isEmpty()){
            d = (Dijeljenje) pom_stog.pop();
            stanje = stanje.concat(Integer.toString(d.getMi_dij()));
            stanje = stanje.concat(" - ");
            stanje = stanje.concat(Integer.toString(d.getVi_dij()));
            stanje = stanje.concat("\n");
            prva.stog.push(d);
        }
        dlgAlert.setMessage("MI - VI:\n" + stanje);
        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.show();
    }

    public void onClickUndo(View v){
        Dijeljenje d;
        EditText et;
        if (!prva.stog.isEmpty()) {
            d = (Dijeljenje) prva.stog.pop();
            prva.stog_za_redo.push(d);
            prva.setMi_UkupnoMinus(d.getMi_dij());
            prva.setVi_UkupnoMinus(d.getVi_dij());
            et = (EditText) findViewById((R.id.editText5));
            et.setText(Integer.toString(prva.getMi_ukupno()));
            et = (EditText) findViewById((R.id.editText6));
            et.setText(Integer.toString(prva.getVi_ukupno()));

        }
    }
    public void onClickRedo(View v){
        Dijeljenje d;
        EditText et;
        if(!prva.stog_za_redo.isEmpty()){
            d = (Dijeljenje) prva.stog_za_redo.pop();
            prva.stog.push(d);
            prva.setMi_ukupno(d.getMi_dij());
            prva.setVi_ukupno(d.getVi_dij());
            et = (EditText) findViewById((R.id.editText5));
            et.setText(Integer.toString(prva.getMi_ukupno()));
            et = (EditText) findViewById((R.id.editText6));
            et.setText(Integer.toString(prva.getVi_ukupno()));
        }
    }
}
