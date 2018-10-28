package com.dinder.dinder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class HomepageActivity extends AppCompatActivity {

    /** List Adapter.*/
    private ArrayAdapter myAdapter;
    /** List of restaurant's names.*/
    private List<String> myRestaurant;
    /** Search View.*/
    private SearchView mySearchView;
    /** List View.*/
    private ListView myListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        myRestaurant = new ArrayList<>();
        myRestaurant.add("Domino");
        myRestaurant.add("Chicken Teriyaki");
        myRestaurant.add("McDonald");

        // create search filter
        createSearchFilter();
    }


    /**
     * Create search filter for restaurant search.
     */
    private void createSearchFilter() {
        myAdapter =  new ArrayAdapter(HomepageActivity.this, android.R.layout.simple_list_item_1,
                myRestaurant);
        myListView = (ListView) findViewById(R.id.restaurant_list);
        mySearchView = (SearchView) findViewById(R.id.search_view);

        myListView.setAdapter(myAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> theParent, final View theView,
                                    final int thePosition, final long theId) {
                // do something
                String url = "https://www.dominos.com/en/pages/order/menu.jsp#!/menu/category/viewAll/";
                Intent menuPage = new Intent(Intent.ACTION_VIEW);
                menuPage.setData(Uri.parse(url));
                startActivity(menuPage);
            }
        });

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String theQuery) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String theNewText) {
                myAdapter.getFilter().filter(theNewText);
                return true;
            }
        });
    }
}
