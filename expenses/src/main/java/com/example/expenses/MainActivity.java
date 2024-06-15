package com.example.expenses;

import android.os.Bundle;

import com.example.common.Activity_Panel;
import com.example.common.models.Money;
import com.example.common.utils.DataManagerBase;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class MainActivity extends Activity_Panel {
    MaterialTextView TXT_AVG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TXT_AVG=findViewById(com.example.common.R.id.TXT_avg);
        setAverage();

    }

    @Override
    public void setAverage() {
        TXT_AVG.setText(getAverage(DataManagerBase.getInstance().getMoneyList()));
    }

    private String getAverage(ArrayList<Money> moneyList) {
        if (moneyList.size()==0)
            return "0";
        double sum=0;
        for (Money money:moneyList){
            sum+=money.getAmount();
        }
        return String.valueOf(sum/moneyList.size());
    }
}