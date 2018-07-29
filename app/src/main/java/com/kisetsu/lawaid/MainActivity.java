package com.kisetsu.lawaid;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.kisetsu.lawaid.fragments.FragmentHomepage;
import com.kisetsu.lawaid.fragments.FragmentMe;
import com.kisetsu.lawaid.fragments.FragmentPractice;
import com.kisetsu.lawaid.fragments.FragmentRequire;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

//    Toolbar toolbar;
    BottomNavigationView navigationView;

    FragmentHomepage fragmentHomepage =new FragmentHomepage();
    FragmentRequire fragmentRequire =new FragmentRequire();
    FragmentPractice fragmentPractice =new FragmentPractice();
    FragmentMe fragmentMe =new FragmentMe();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        toolbar=findViewById(R.id.toolbar);
//        setActionBar(toolbar);
        navigationView=findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        //找到searchView
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        switch (menuItem.getItemId()){
            case R.id.navigation_homepage:
//                toolbar.setTitle(R.string.homepage);
                transaction.replace(R.id.fragment, fragmentHomepage);
                break;
            case R.id.navigation_require:
//                toolbar.setTitle(R.string.require);
                transaction.replace(R.id.fragment, new FragmentRequire());
                break;
            case R.id.navigation_practice:
//                toolbar.setTitle(R.string.practice);
                transaction.replace(R.id.fragment, FragmentPractice.newInstance(null));
                break;
            case R.id.navigation_me:
//                toolbar.setTitle(R.string.me);
                transaction.replace(R.id.fragment, fragmentMe);
                break;
        }
        transaction.commit();
        return false;
    }
}
