package com.example.kjfastfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.kjfastfood.Adapters.OrderAdapter;
import com.example.kjfastfood.Models.DbHelper;
import com.example.kjfastfood.Models.OrderModel;
import com.example.kjfastfood.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class Order_Activity extends AppCompatActivity {

    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        DbHelper helper = new DbHelper(this);
        ArrayList<OrderModel>list = helper.getOrders();


        OrderAdapter adapter = new OrderAdapter(list,this);
        binding.orderRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(layoutManager);

    }
}