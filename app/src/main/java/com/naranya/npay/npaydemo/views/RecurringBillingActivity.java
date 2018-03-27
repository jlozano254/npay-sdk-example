package com.naranya.npay.npaydemo.views;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import com.naranya.npay.NPay;
import com.naranya.npay.interfaces.OnRecurringBillingServices;
import com.naranya.npay.models.datasets.RecurringBillingResponse;
import com.naranya.npay.models.service.InfoService;
import com.naranya.npay.npaydemo.R;
import com.naranya.npay.npaydemo.adapters.RecurringBillingAdapter;
import com.naranya.npay.npaydemo.models.ItemSubscription;
import com.naranya.npay.utils.LanguageUtils;

import org.buraktamturk.loadingview.LoadingView;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Company name: Naranya Corp,
 * Company email: npaydevs@naranya.com
 */
public class RecurringBillingActivity extends BaseActivity {
    private RecyclerView recycler;
    private ArrayList<ItemSubscription> items;
    private RecurringBillingAdapter adapter;
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

        adapter = new RecurringBillingAdapter(this, items);
        recycler.setAdapter(adapter);

        if( type == 0 ) {
            toolbar.setTitle(getString(R.string.title_activity_recurringbilling));
            new NPay.NPayBuilder().getRecurringBillingServices(new OnRecurringBillingServices() {
                @Override
                public void getRecurrentBillingServices(RecurringBillingResponse result, String jsonResponse) {
                    loadingView.setLoading(false);

                    for( int i = 0; i < result.getData().getData().size(); i++ ) {
                        InfoService info = LanguageUtils.getDefaultRecurringBillingInformation( result.getData().getData().get(i).getInfo() );

                        items.add( new ItemSubscription(info.getName(), info.getDescription(),
                                result.getData().getData().get(i).getIdService(),
                                result.getData().getData().get(i).getHubDetails().getCountry()));
                    }

                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onError(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    loadingView.setLoading(false);
                    Toast.makeText(RecurringBillingActivity.this, responseString, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            toolbar.setTitle(getString(R.string.title_activity_recurringbilling_local));
            new NPay.NPayBuilder().getLocalRecurringBillingServices(this, new OnRecurringBillingServices() {
                @Override
                public void getRecurrentBillingServices(RecurringBillingResponse result, String jsonResponse) {
                    loadingView.setLoading(false);

                    for( int i = 0; i < result.getData().getData().size(); i++ ) {
                        InfoService info = LanguageUtils.getDefaultRecurringBillingInformation( result.getData().getData().get(i).getInfo() );

                        items.add( new ItemSubscription(info.getName(), info.getDescription(),
                                result.getData().getData().get(i).getIdService(),
                                result.getData().getData().get(i).getHubDetails().getCountry()));
                    }

                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onError(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    loadingView.setLoading(false);
                    Toast.makeText(RecurringBillingActivity.this, responseString, Toast.LENGTH_LONG).show();
                }
            });
        }

    }
}
