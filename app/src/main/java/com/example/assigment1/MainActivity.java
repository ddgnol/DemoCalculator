package com.example.assigment1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv;
    Button[] btns;
    float arg1 = 0.0f, arg2 = 0.0f;
    String operator="", show = "0";
    boolean check = false, error = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);
        int[] idBtn = {  R.id.btnCE, R.id.btnC, R.id.btnBs,
                R.id.btnDvX,  R.id.btnDv,
                R.id.btn7  ,  R.id.btn8, R.id.btn9, R.id.btnMul,
                R.id.btn4,  R.id.btn5, R.id.btn6, R.id.btnAdd,
                R.id.btn1,  R.id.btn2, R.id.btn3, R.id.btnMi,
                  R.id.btn0, R.id.btnP, R.id.btnEq,
         };
        for(int id: idBtn){
            findViewById(id).setOnClickListener(this);
        }
    }
    public void assignArg1(){
        if(error){
            return;
        }
        arg1 = Float.parseFloat(tv.getText().toString());
        show = "0";
        if(arg1 == (int) arg1){
            tv.setText(String.valueOf((int) arg1));
        }
        else
            tv.setText(String.valueOf(arg1));
    }
    public void calculate(){
        if(error) {
            tv.setText("Error");
            show = "";
            return;
        }
        float result = 0;
        arg2 = Float.parseFloat(tv.getText().toString());
        Log.d("Arg1","arg1= "+arg1);
        Log.d("Arg2","arg2= "+arg2);
        if(operator=="/"){
            if(arg2==0) {
                error = true;
                tv.setText("Error");
                return;
            }
             result = arg1/arg2;
        }

        else if(operator.equals("-"))
            result = arg1 - arg2;
        else if(operator.equals("*"))
            result = arg1 * arg2;
        else if (operator.equals("+")) result = arg1+arg2;
        else  result = arg2;
        if(result == (int) result){
            tv.setText(String.valueOf((int) result));
        }
        else
            tv.setText(String.valueOf(result));
        arg1 = 0.0f;
        arg2 = 0.0f;
        operator = "";
        show = "0";
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCE:
                if(error)
                    error = false;
                if(operator.isEmpty()){
                    arg1 = 0.0f;
                }
                else {
                    arg2 = 0.0f;
                }
                show = "0";
                tv.setText(show);
                break;
            case  R.id.btnC:
                if(error)
                    error = false;
                arg1 = 0.0f;
                operator = "";
                show = "0";
                tv.setText(show);
                break;
            case R.id.btnAdd:
                if(check&&!operator.isEmpty())
                    calculate();
                operator = "+";
                check = false;
                assignArg1();
                break;
            case R.id.btnMi:
                if(check&&!operator.isEmpty())
                    calculate();
                operator = "-";
                check = false;
                assignArg1();
                break;
            case R.id.btnMul:
                if(check&&!operator.isEmpty())
                    calculate();
                operator = "*";
                check = false;
                assignArg1();
                break;
            case R.id.btnDv:
                if(check&&!operator.isEmpty())
                    calculate();
                operator = "/";
                check = false;
                assignArg1();
                break;
            case R.id.btnEq:
                calculate();

                break;
            case R.id.btnBs:
                if (show.length()==1){
                    show = "0";
                    tv.setText("0");
                }
                else {
                    show = show.substring(0,show.length()-1);
                    tv.setText(show);
                }
                break;
//            case  R.id.btnNorP:
//                if(operator.isEmpty()){
//                    if(arg1 != 0){
//                        arg1 = -arg1;
//                        if(arg1==(int) arg1)
//                            tv.setText(String.valueOf((int)arg1));
//                        else tv.setText(String.valueOf(arg1));
//                    }
//                    show = "-" + show;
//                }
//                else {
//                    if(arg2!=0)
//                        arg2 = -arg2;
//                }
//                break;
            default:
                check = true;
                if(show.equals("0")){
                    show = "";
                }
                show = show +((Button)v).getText().toString();
                tv.setText(show);
        }
    }
}
