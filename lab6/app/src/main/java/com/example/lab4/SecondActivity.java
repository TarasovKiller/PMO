package com.example.lab4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.lab4.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivitySecondBinding binding;
    private DBManager dbManager;

    public static final String txtData = "dima_tarasov";
    private Handler nHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        dbManager = new DBManager(this);
        dbManager.open();
        dbManager.insert("Dima","VIP");
//        Log.i("LOG_TAG", "ROW" + id + "HAS UNAME" + name);
        AlertDia();
    }

    public void AlertDia(){
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        alt_bld.setMessage("Выполнять вход?")
                .setCancelable(false)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alert = alt_bld.create();
        alert.setTitle("Вход");
        alert.show();
    }

    public void takeData(View view) {
        Cursor cursor = dbManager.fetch();
        if (cursor == null) return;
        while (cursor.moveToNext()) {
            String subject = (cursor.getString(1));
            String desc = (cursor.getString(2));
            Toast.makeText(this, subject + "  " + desc, Toast.LENGTH_LONG).show();
        }
//        Log.i("something",cursor.toString());

    }
}
