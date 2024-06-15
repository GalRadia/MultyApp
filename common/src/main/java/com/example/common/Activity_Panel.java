package com.example.common;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.R;
import com.example.common.adapters.MoneyAdapter;
import com.example.common.databinding.ActivityPanelBinding;
import com.example.common.interfaces.MoneyCallbacks;
import com.example.common.models.Money;
import com.example.common.utils.DataManagerBase;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Activity_Panel extends AppCompatActivity {
   // protected  DataManagerBase dataManagerBase;
    private ActivityPanelBinding binding;
    private MaterialButton addBtn;
    private MaterialTextView title, avg;
    private EditText money, desc, date;
    private RecyclerView rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataManagerBase.init(this,getString(R.string.DB_name),getString(R.string.table_name));
        binding = ActivityPanelBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        findViews();
        initViews();

    }

    private void findViews() {
        addBtn = binding.BTNAddMoney;
        title = binding.TXTTitle;
        avg = binding.TXTAvg;
        money = binding.editTextNumber;
        desc = binding.editTextDescription;
        date = binding.editTextDate;
        rcv = binding.recyclerView;
    }

    private void initViews() {
        rcv.setLayoutManager(new LinearLayoutManager(this));
        MoneyAdapter adapter = new MoneyAdapter(new ArrayList<>(), this);
        adapter.putMoneyList(DataManagerBase.getInstance().getMoneyList());
        rcv.setAdapter(adapter);
        adapter.setMoneyCallbacks(money ->{ adapter.removeMoney(money);
        refreshAdapter();}
        );
        addBtn.setOnClickListener(v -> {
            if(validate()){
                Money m = new Money(Double.parseDouble(money.getText().toString()), desc.getText().toString(), date.getText().toString());
                adapter.addMoney(m);
                DataManagerBase.getInstance().putMoneyList(((MoneyAdapter)rcv.getAdapter()).getMoneyList());
                refreshAdapter();
            }
        });

    }

    private void refreshAdapter() {
        MoneyAdapter adapter = (MoneyAdapter) rcv.getAdapter();
        setAverage();
        adapter.notifyDataSetChanged();
    }
    private boolean validate(){
        if(money.getText().toString().isEmpty()){
            money.setError("Please enter a valid amount");
            return false;
        }
        if(desc.getText().toString().isEmpty()){
            desc.setError("Please enter a description");
            return false;
        }
        if(date.getText().toString().isEmpty()){
            date.setError("Please enter a date");
            return false;
        }
        return true;
    }


    public  abstract void setAverage();


}