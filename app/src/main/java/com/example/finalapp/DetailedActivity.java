package com.example.finalapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public class DetailedActivity extends AppCompatActivity {

    TextView quantity;
    int totalQuantity=1;

    int totalPrice=0;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    ImageView detailedImg;
    TextView price,rating,description;
    Button addToCart;

    ImageView addItem,removeItem;

    Toolbar toolbar;

    ViewAllModel viewAllModel = null;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detailed);

        //toolbar
        toolbar=findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        //fireStore initializing


        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

       //getting data
        final Object object=getIntent().getSerializableExtra("detail");
        if(object instanceof ViewAllModel){
            viewAllModel=(ViewAllModel) object;

        }


         //initialing variables
        quantity=findViewById(R.id.quantity);
        detailedImg=findViewById(R.id.detailed_img);
        addItem=findViewById(R.id.add_item);
        removeItem=findViewById(R.id.remove_item);
        price=findViewById(R.id.detailed_price);
        rating=findViewById(R.id.detailed_rating);
        description=findViewById(R.id.detailed_description);

        //loading data from fireStore storage

        if(viewAllModel!=null){
            Picasso.get().load(viewAllModel.getImage_url()).into(detailedImg);
            rating.setText(viewAllModel.getRating());
            description.setText(viewAllModel.getDescription());
            price.setText("Price :$"+viewAllModel.getPrice()+"/kg");


            totalPrice=viewAllModel.getPrice()*totalQuantity;


         //using if conditions for eggs and milk
            if(viewAllModel.getType().equals("egg"))
            {
               price.setText("Price :$"+viewAllModel.getPrice()+" /dozen");
                totalPrice=viewAllModel.getPrice()*totalQuantity;


            }
            if(viewAllModel.getType().equals("milk"))
            {
               price.setText("Price :$"+viewAllModel.getPrice()+" /litre");
                totalPrice=viewAllModel.getPrice()*totalQuantity;

            }
        }

        addToCart=findViewById(R.id.add_to_cart);

        // button for adding items into cart
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();



            }
        });

        //add item to cart button
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(totalQuantity<10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice=viewAllModel.getPrice()*totalQuantity;
                }

            }
        });

        //remove item from cart button
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(totalQuantity > 1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice=viewAllModel.getPrice()*totalQuantity;
                }

            }
        });




    }

    private void addedToCart() {

        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate =Calendar.getInstance();

        SimpleDateFormat currentDate =new SimpleDateFormat("MM dd,yyyy");
        saveCurrentDate= currentDate.format(calForDate.getTime());


        SimpleDateFormat currentTime =new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime= currentTime.format(calForDate.getTime());


        final HashMap<String,Object> cartMap= new HashMap<>();

        cartMap.put("productName", viewAllModel.getName());
        cartMap.put("productPrice", price.getText().toString());
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice);


        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailedActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                        finish();
                        
                    }
                });

    }
}