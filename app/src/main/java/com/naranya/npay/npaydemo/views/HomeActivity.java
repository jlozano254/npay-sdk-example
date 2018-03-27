package com.naranya.npay.npaydemo.views;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.naranya.npay.npaydemo.R;
import com.naranya.npay.npaydemo.adapters.HomeAdapter;
import com.naranya.npay.npaydemo.models.ItemHome;
import java.util.ArrayList;

/**
 * Company name: Naranya Corp,
 * Company email: npaydevs@naranya.com
 */
public class HomeActivity extends BaseActivity {
    private RecyclerView recycler;
    private ArrayList<ItemHome> items;
    private HomeAdapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        toolbar = getToolbar();
        toolbar.setTitle(getString(R.string.app_name));

        items = new ArrayList<>();
        items.add( new ItemHome(getString(R.string.title_activity_recurringbilling), R.mipmap.ic_subscription));
        items.add( new ItemHome(getString(R.string.title_activity_recurringbilling_local), R.mipmap.ic_subscription));
        items.add( new ItemHome(getString(R.string.title_activity_singlebilling), R.mipmap.ic_coins_purchase));
        items.add( new ItemHome(getString(R.string.title_activity_singlebilling_local), R.mipmap.ic_coins_purchase));

        recycler = (RecyclerView) findViewById(R.id.recycler);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        adapter = new HomeAdapter(this, items);
        recycler.setAdapter(adapter);
    }
}