package kr.hs.emirim.julianneyi.shining;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class RandomMoneyActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputPersonEt;
    private Button inputPersonNumbtn;
    private LinearLayout random_money_page;
    private Button backInputbtn;
    private Button showResultbtn;
    private ArrayList<String> strList;
    int num = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_money);

        findId();
        strList = new ArrayList<String>();
        inputPersonNumbtn.setOnClickListener(this);
        backInputbtn.setOnClickListener(this);
        showResultbtn.setOnClickListener(this);
    }

    private void findId() {
        random_money_page = findViewById(R.id.random_money_page);
        inputPersonEt = findViewById(R.id.inputPersonNum);
        inputPersonNumbtn = findViewById(R.id.inputPersonNumbtn);
        backInputbtn = findViewById(R.id.backInputbtn);
        showResultbtn = findViewById(R.id.showResultbtn);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inputPersonNumbtn:
                input();
                break;
            case R.id.backInputbtn :
                backInput();
                break;
            case R.id.showResultbtn :
                showResult();
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        backInput();
    }

    private void showResult() {

        if(num>=2) {
            for(int i=0; i<num; i++) {
                EditText et = (EditText) random_money_page.getChildAt(i);
                String text = et.getText().toString();
                if (!TextUtils.isEmpty(text)) {
                    Log.d("test","item text : "+et.getText().toString());
                    strList.add(text);
                }
            }
            Log.d("test","strList : "+strList);
            if(strList == null) {
                Toast.makeText(RandomMoneyActivity.this,"빈칸을 채워주세요!",Toast.LENGTH_SHORT).show();
            } else {
                Intent result = new Intent(RandomMoneyActivity.this,ShowRandomResultActivity.class);
                //putExtra name
                result.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                result.putExtra("num", num);
                result.putStringArrayListExtra("strList", strList);
                startActivity(result);
            }

        }


    }

    private void backInput() {
        random_money_page.removeAllViews();
        if(strList != null) {
            strList.clear();
        }
        num = 0;
        inputPersonEt.setText("");
        inputPersonNumbtn.setVisibility(View.VISIBLE);
        backInputbtn.setVisibility(View.GONE);
        findViewById(R.id.randomtxt).setVisibility(View.GONE);
        findViewById(R.id.scrollView2).setVisibility(View.GONE);
        showResultbtn.setVisibility(View.GONE);
    }

    private void input() {
            num = Integer.parseInt(inputPersonEt.getText().toString());
            Log.d("test","num:"+num);

            if(num>=2) {

                findViewById(R.id.randomtxt).setVisibility(View.VISIBLE);
                findViewById(R.id.scrollView2).setVisibility(View.VISIBLE);
                findViewById(R.id.showResultbtn).setVisibility(View.VISIBLE);

                for(int i=0; i<num; i++) {
                    final EditText et = new EditText(getApplicationContext());
                    LinearLayout.LayoutParams p =
                            new LinearLayout.
                                    LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    et.setLayoutParams(p);
                    et.setId(12345+i);
                    Log.d("test","et id : "+et.getId());
                    et.setText("");
                    random_money_page.addView(et);

                }
            } else {
                Toast.makeText(RandomMoneyActivity.this,"2명 이상 넣어주세요!",Toast.LENGTH_SHORT).show();
            }

        inputPersonNumbtn.setVisibility(View.GONE);
        backInputbtn.setVisibility(View.VISIBLE);

        }
    }