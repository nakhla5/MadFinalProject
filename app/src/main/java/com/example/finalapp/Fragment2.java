package com.example.finalapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment2 extends Fragment {

    private static final String TAG = "Fragment2";

    ImageView profileImg;
    EditText name, email, number, address;

    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseDatabase database;

    RecyclerView recyclerView;
    List<NavCategoryModel> categoryModelList;
    NavCategoryAdapter navCategoryAdapter;

    Button update;

    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_2, container, false);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        profileImg = root.findViewById(R.id.profile_img);
        name = root.findViewById(R.id.profile_name);
        email = root.findViewById(R.id.profile_email);
        number = root.findViewById(R.id.profile_number);
        address = root.findViewById(R.id.profile_address);
        update = root.findViewById(R.id.update);

        // Register the activity result launcher
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri profileUri = result.getData().getData();
                        profileImg.setImageURI(profileUri);

                        final StorageReference reference = storage.getReference().child("profile_picture")
                                .child(FirebaseAuth.getInstance().getUid());
                        reference.putFile(profileUri).addOnSuccessListener(taskSnapshot -> {
                            reference.getDownloadUrl().addOnSuccessListener(uri -> {
                                String imageUrl = uri.toString();
                                Log.d(TAG, "Uploaded: " + imageUrl);
                                Toast.makeText(getContext(), "Profile picture uploaded successfully", Toast.LENGTH_SHORT).show();
                            }).addOnFailureListener(e -> Log.e(TAG, "Failed to get download URL", e));
                        }).addOnFailureListener(e -> Log.e(TAG, "Failed to upload file", e));
                    }
                }
        );

        profileImg.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            activityResultLauncher.launch(intent);
        });

        update.setOnClickListener(v -> {
            updateUserProfile();
        });

        return root;
    }

    private void updateUserProfile() {
        String userName = name.getText().toString().trim();
        String userEmail = email.getText().toString().trim();
        String userNumber = number.getText().toString().trim();
        String userAddress = address.getText().toString().trim();

        if (userName.isEmpty() || userEmail.isEmpty() || userNumber.isEmpty() || userAddress.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Display success message
        Toast.makeText(getContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show();

        // Log the entered data for debugging purposes
        Log.d(TAG, "Name: " + userName);
        Log.d(TAG, "Email: " + userEmail);
        Log.d(TAG, "Number: " + userNumber);
        Log.d(TAG, "Address: " + userAddress);

        // Clear input fields
        name.setText("");
        email.setText("");
        number.setText("");
        address.setText("");
    }
}
