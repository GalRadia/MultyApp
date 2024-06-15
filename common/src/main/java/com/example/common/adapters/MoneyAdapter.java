package com.example.common.adapters;

// MoneyAdapter.java

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.R;
import com.example.common.interfaces.MoneyCallbacks;
import com.example.common.models.Money;
import com.example.common.utils.DataManagerBase;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MoneyAdapter extends RecyclerView.Adapter<MoneyAdapter.MoneyViewHolder> {

    private List<Money> moneyList;
    private Context context;
    private MoneyCallbacks moneyCallbacks;

    public MoneyAdapter(List<Money> moneyList, Context context) {
        this.moneyList = moneyList;
        this.context = context;
    }
    public void setMoneyCallbacks(MoneyCallbacks moneyCallbacks) {
        this.moneyCallbacks = moneyCallbacks;
    }

    public List<Money> getMoneyList() {
        return moneyList;
    }
    public void addMoney(Money money) {
        moneyList.add(money);
        notifyDataSetChanged();
    }
    public void putMoneyList(List<Money> money) {
        moneyList.addAll(money);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoneyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.money_item, parent, false);
        return new MoneyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoneyViewHolder holder, int position) {
        Money money = moneyList.get(position);
        holder.tvMoney.setText(String.format("$%.2f", money.getAmount()));
        holder.tvDescription.setText(money.getDescription());
        holder.tvDate.setText(money.getDate());
        if (moneyCallbacks != null) {
            holder.btnDelete.setOnClickListener(v -> moneyCallbacks.onDeleteItem(money));
        }
    }
    public void removeMoney(Money money) {
        moneyList.remove(money);
        DataManagerBase.getInstance().removeFromList(money);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return moneyList.size();
    }

    static class MoneyViewHolder extends RecyclerView.ViewHolder {
        TextView tvMoney, tvDescription, tvDate;
        FloatingActionButton btnDelete;

        public MoneyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMoney = itemView.findViewById(R.id.tvMoney);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvDate = itemView.findViewById(R.id.tvDate);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
