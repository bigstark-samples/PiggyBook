package com.bigstark.piggybook;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class HomeActivity extends AppCompatActivity {

    private AmountFragment amountFragment;
    private WishlistFragment wishlistFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        amountFragment = (AmountFragment) getSupportFragmentManager().findFragmentById(R.id.amount_fragment);
        wishlistFragment = (WishlistFragment) getSupportFragmentManager().findFragmentById(R.id.wishlist_fragment);

        getSupportFragmentManager().beginTransaction()
                .show(amountFragment)
                .hide(wishlistFragment)
                .commit();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        getSupportFragmentManager().beginTransaction()
                                .show(amountFragment)
                                .hide(wishlistFragment)
                                .commit();
                        return true;
                    case R.id.navigation_dashboard:
                        getSupportFragmentManager().beginTransaction()
                                .show(wishlistFragment)
                                .hide(amountFragment)
                                .commit();
                        return true;
                }
                return false;
            }
        });
    }

}
