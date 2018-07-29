package com.br.natanfelipe.teste;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String[] countries;
    ArrayList<Country> countriesList;
    ItemAdapter adapter;
    int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String you = "pale";
        String yuo = "bakes";

        recyclerView = findViewById(R.id.recyclerView);

        /*boolean typo = isTypo(you,yuo);

        System.out.println(" "+typo);*/

        countries = new String[]{"Australia","Bolivia","Congo","Denamark","Sweden"};
        countriesList = new ArrayList<>();
        for(String country:countries){
            countriesList.add(new Country(country,count));
            count++;
        }

        adapter = new ItemAdapter(countriesList);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    private boolean isTypo(String word1, String word2) {

        boolean result = true;

        int count = 0;
        int addTypo = word1.length()+1;
        int removeTypo = word1.length()-1;
        if(word1.length() == word2.length()){
            for(int i = 0; i < word1.length(); i++){
                if(word1.charAt(i) != word2.charAt(i))
                    count++;
            }
            if(count != 1)
                result = false;

        } else {
            if(word2.length() == addTypo)
                result = true;
            else if(word2.length() == removeTypo)
                result = true;
            else
                result = false;
        }

        return result;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        search(searchView);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                android.util.Log.d("TESTE", "onQueryTextChange: "+newText);
                return true;
            }
        });
    }
}
