package com.example.kjfastfood;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.kjfastfood.Models.DbHelper;
import com.example.kjfastfood.databinding.ActivityDetailBinding;

public class Detail_Activity extends AppCompatActivity {

    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final DbHelper helper = new DbHelper(this);
        if(getIntent().getIntExtra("type",0) == 1) {
            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");

             binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.parseInt(binding.detailQuantity.getText().toString());
                    count++;
                    if(count>=5)
                        count=5;
                        binding.detailQuantity.setText(String.valueOf(count));
                }
            });
             binding.minus.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     int count = Integer.parseInt(binding.detailQuantity.getText().toString());
                     count--;
                     if(count<=0)
                         count=1;
                     binding.detailQuantity.setText(String.valueOf(count));
                 }
             });

            final int quantity = Integer.parseInt(binding.detailQuantity.getText().toString());

            binding.detailImage.setImageResource(image);
            binding.detailTotal.setText(String.format("%d", price));
            binding.foodBox.setText(name);
            binding.detailDescription.setText(description);



            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = helper.insertOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,
                            image,
                            name,
                            description,
                            quantity
                    );

                    if (isInserted)
                        Toast.makeText(Detail_Activity.this, "Successfully inserted..", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Detail_Activity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            int id = getIntent().getIntExtra("id",0);
            Cursor cursor = helper.getOrderById(id);
           // Toast.makeText(this,cursor.getString(2),Toast.LENGTH_SHORT).show();

          /* final int image = cursor.getInt(4);
            binding.detailImage.setImageResource(cursor.getInt(4));
            binding.detailTotal.setText(String.format("%d",cursor.getInt(3)));
            binding.foodBox.setText(cursor.getString(6));
            binding.detailDescription.setText(cursor.getString(7));
            binding.detailQuantity.setText(cursor.getString(5));
            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.insertBtn.setText("Update Now");*/

            final int image = cursor.getInt(4);
            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));

            binding.detailImage.setImageResource(cursor.getInt(4));
            binding.detailTotal.setText(String.format("%d",cursor.getInt(3)));
            binding.detailQuantity.setText(cursor.getString(5));
            binding.foodBox.setText(cursor.getString(6));
            binding.detailDescription.setText(cursor.getString(7));
            binding.insertBtn.setText("Update Now");

            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.parseInt(binding.detailQuantity.getText().toString());
                    count++;
                    if(count>=5)
                        count=5;
                    binding.detailQuantity.setText(String.valueOf(count));
                }
            });
            binding.minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int count = Integer.parseInt(binding.detailQuantity.getText().toString());
                    count--;
                    if(count<=0)
                        count=1;
                    binding.detailQuantity.setText(String.valueOf(count));
                }
            });

            final int quantity = Integer.parseInt(binding.detailQuantity.getText().toString());





            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                       boolean isUpdated = helper.updateOrder(
                               binding.nameBox.getText().toString(),
                               binding.phoneBox.getText().toString(),
                               Integer.parseInt(binding.detailTotal.getText().toString()),
                               image,
                               binding.detailDescription.getText().toString(),
                               binding.foodBox.getText().toString(),
                               quantity,
                               id
                       );

                       if(isUpdated)
                           Toast.makeText(Detail_Activity.this,"Updated",Toast.LENGTH_SHORT).show();
                       else
                           Toast.makeText(Detail_Activity.this,"Error",Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
}