package com.example.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.common.models.Money;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class  DataManagerBase {
        private static String table;
        private static volatile DataManagerBase instance = null;
        private static  String DB_FILE;
        private SharedPreferences sharedPreferences;
        public DataManagerBase(Context context, String dbFile, String tableName) {
                DB_FILE = dbFile;
                table = tableName;
                this.sharedPreferences = context.getSharedPreferences(DB_FILE, Context.MODE_PRIVATE);
        }
        public void removeFromList(Money money) {
                ArrayList<Money> moneyList = getMoneyList();
                moneyList.remove(money);
                putMoneyList(moneyList);
        }
        public static void init(Context context, String dbFile, String tableName) {
                synchronized (DataManagerBase.class) {
                        if (instance == null)
                                instance = new DataManagerBase(context, dbFile, tableName);
                }
        }
        public void putMoneyList(List<Money> money) {
                String moneyJson = new Gson().toJson(money);
                sharedPreferences.edit().putString(table, moneyJson).apply();
        }
        public static DataManagerBase getInstance() {
                return instance;
        }
        public ArrayList<Money> getMoneyList() {
                String result = sharedPreferences.getString(table, "");
                Type listType = new com.google.gson.reflect.TypeToken<List<Money>>() {
                }.getType();
                if (result.equals("")) {
                        return new ArrayList<>();
                }
                return new Gson().fromJson(result, listType);

        }
}