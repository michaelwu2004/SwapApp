package com.example.swapapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.swapapp.db.DBHelper;
import com.example.swapapp.db.DBItemHelper;
import com.example.swapapp.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.swapapp.databinding.ActivityMainBinding;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public static String user_id = "";

    public static User otherUser = new User("123", "123", "testinglol1234567890");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initOtherUser();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_transaction, R.id.navigation_profile, R.id.navigation_find)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public void initOtherUser(){
        DBHelper DB = new DBHelper(MainActivity.this);
        String otherUUID = UUID.randomUUID().toString();
        DB.insertDummyData(otherUser, otherUUID);
    }

}