package com.naranya.npay.npaydemo.views;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.naranya.npay.NPay;
import com.naranya.npay.interfaces.OnSingleBillingServices;
import com.naranya.npay.models.catalogservice.Consumable;
import com.naranya.npay.models.catalogservice.Detail;
import com.naranya.npay.models.catalogservice.NonConsumable;
import com.naranya.npay.npaydemo.R;
import com.naranya.npay.npaydemo.adapters.SingleBillingAdapter;
import com.naranya.npay.npaydemo.models.ItemSingle;
import com.naranya.npay.purchases.PurchaseType;
import com.naranya.npay.utils.LanguageUtils;

import org.buraktamturk.loadingview.LoadingView;
import java.util.ArrayList;
import java.util.List;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Anselmo Hernández Bazaldúa. on 8/9/16.
 * ----------------------------------------------------------
 * Additional Information
 * ----------------------------------------------------------
 * Company name: Naranya Corp,
 * Company email: anselmo.hernandez@naranya.com,
 * Personal email: chemo.baza@gmail.com,
 * Phone: +520448119163771,
 * Skype: chemo.baza,
 * ----------------------------------------------------------
 * Happy Coding :)
 */
public class SingleBillingActivity extends BaseActivity {
    private RecyclerView recycler;
    private ArrayList<ItemSingle> items;
    private SingleBillingAdapter adapter;
    private Toolbar toolbar;
    private LoadingView loadingView;
    private int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getInt("TYPE");
        }

        toolbar = getToolbar();
        toolbar.setNavigationIcon(R.mipmap.ic_up);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        items = new ArrayList<>();

        loadingView = (LoadingView) findViewById(R.id.loadingView);
        loadingView.setLoading(true);

        recycler = (RecyclerView) findViewById(R.id.recycler);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        adapter = new SingleBillingAdapter(this, items);
        recycler.setAdapter(adapter);

        if( type == 0 ) {
            toolbar.setTitle(getString(R.string.title_activity_singlebilling));

            new NPay.NPayBuilder().getSingleBillingServices(new OnSingleBillingServices() {

                @Override
                public void getSingleBillingServicesResponse(String jsonResponse, List<Consumable> consumableList, List<NonConsumable> nonConsumableList) {
                    loadingView.setLoading(false);

                    for( int i = 0; i < consumableList.size(); i++ ) {
                        Detail detail = LanguageUtils.getDefaultSingleBillingInformation(consumableList.get(i).getDetails());
                        items.add( new ItemSingle(detail.getName(), detail.getDescription(), consumableList.get(i).getSku(), PurchaseType.CONSUMABLE));
                    }

                    for( int i = 0; i < nonConsumableList.size(); i++ ) {
                        Detail detail = LanguageUtils.getDefaultSingleBillingInformation(nonConsumableList.get(i).getDetails());
                        items.add( new ItemSingle(detail.getName(), detail.getDescription(), nonConsumableList.get(i).getSku(), PurchaseType.NO_CONSUMABLE));
                    }
                }

                @Override
                public void onError(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    loadingView.setLoading(false);
                }
            });
        } else {
            toolbar.setTitle(getString(R.string.title_activity_singlebilling_local));

            new NPay.NPayBuilder().getLocalSingleBillingServices(this, new OnSingleBillingServices() {

                @Override
                public void getSingleBillingServicesResponse(String jsonResponse, List<Consumable> consumableList, List<NonConsumable> nonConsumableList) {
                    loadingView.setLoading(false);

                    for( int i = 0; i < consumableList.size(); i++ ) {
                        Detail detail = LanguageUtils.getDefaultSingleBillingInformation(consumableList.get(i).getDetails());
                        items.add( new ItemSingle(detail.getName(), detail.getDescription(), consumableList.get(i).getSku(), PurchaseType.CONSUMABLE));
                    }

                    for( int i = 0; i < nonConsumableList.size(); i++ ) {
                        Detail detail = LanguageUtils.getDefaultSingleBillingInformation(nonConsumableList.get(i).getDetails());
                        items.add( new ItemSingle(detail.getName(), detail.getDescription(), nonConsumableList.get(i).getSku(), PurchaseType.NO_CONSUMABLE));
                    }
                }

                @Override
                public void onError(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    loadingView.setLoading(false);
                }
            });
        }
    }
}