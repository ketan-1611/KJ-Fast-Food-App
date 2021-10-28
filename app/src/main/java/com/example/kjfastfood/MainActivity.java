package com.example.kjfastfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.kjfastfood.Adapters.MainAdapter;
import com.example.kjfastfood.Models.MainModel;
import com.example.kjfastfood.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#f4d300"));


        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.dosa,"Dosa", "5","Dosa is with sambhar and chatni"));
        list.add(new MainModel(R.drawable.idli,"Idli","4","Idli is our World famous  and given with narial chatni"));
        list.add(new MainModel(R.drawable.materpanner,"Mater Paneer","10","Mater Panner and Tanduri roti is special"));
        list.add(new MainModel(R.drawable.pasta,"Pasta","5"," Arabin Fast food Pasta is Available"));
        list.add(new MainModel(R.drawable.pestri,"Pestri","8","We have all type of Pestri fror Birthday Parties"));
        list.add(new MainModel(R.drawable.rotisbji,"Roti Sbji","12","We provide you to dal roti salad and Complete thali"));
        list.add(new MainModel(R.drawable.samosa,"Samosa", "4","We have India all type of samosa`s"));


        MainAdapter adapter = new MainAdapter(list,this);
        binding.recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.orders:
                startActivity(new Intent(MainActivity.this, Order_Activity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}