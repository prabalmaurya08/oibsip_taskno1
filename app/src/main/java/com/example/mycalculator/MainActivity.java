package com.example.mycalculator;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MaterialButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,badd,bsub,bmul,bdiv,bon,boff,bdot,bequal,bac,bc;
    TextView toutput;
    TextView   twrite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setid(b0,R.id.btn_0);
        setid(b1,R.id.btn_1);
        setid(b2,R.id.btn_2);
        setid(b3,R.id.btn_3);
        setid(b4,R.id.btn_4);
        setid(b5,R.id.btn_5);
        setid(b6,R.id.btn_6);
        setid(b7,R.id.btn_7);
        setid(b8,R.id.btn_8);
        setid(b9,R.id.btn_9);
        setid(badd,R.id.op_add);
        setid(bsub,R.id.op_sub);
        setid(bmul,R.id.op_mul);
        setid(bdiv,R.id.op_div);
        setid(bon,R.id.on);
        setid(boff,R.id.off);
        setid(bequal,R.id.op_equal);
        setid(bac,R.id.all_clear);
        setid(bc,R.id.cancel);
        setid(bdot,R.id.btn_dot);



        toutput=findViewById(R.id.textView2);
        twrite=findViewById(R.id.textView);

    }
    void setid(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        MaterialButton button=(MaterialButton) v;
        String buttontext=button.getText().toString();
         String caldata=twrite.getText().toString();
         if(buttontext.equals("AC")){
             twrite.setText("");
             toutput.setText("0");
             return;
         }

         if(buttontext.equals("=")){
            twrite.setText(toutput.getText());
            return;
         }
         if(buttontext.equals("C")){
             caldata=caldata.substring(0,caldata.length()-1);

         }else{
             caldata=caldata+buttontext;
         }

         twrite.setText(caldata);
         String finalresult=getresult(caldata);
         if(!finalresult.equals("Error")){
             toutput.setText(finalresult);
         }
    }
    String getresult(String data){
        try{
            Context context=Context.enter();
           context.setOptimizationLevel(-1);
           Scriptable scriptable=context.initStandardObjects();
           String finalresult=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
           return finalresult;


        }
        catch (Exception e){
            return "Error";
        }
    }
}