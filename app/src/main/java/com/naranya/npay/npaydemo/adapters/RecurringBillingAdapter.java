package com.naranya.npay.npaydemo.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.naranya.npay.NPay;
import com.naranya.npay.interfaces.OnQuickSubscriptionInfo;
import com.naranya.npay.interfaces.OnSubscriptionCancel;
import com.naranya.npay.interfaces.OnSubscriptionCreated;
import com.naranya.npay.models.datasets.CheckSubscriptionResponse;
import com.naranya.npay.models.datasets.SubscriptionCancelResponse;
import com.naranya.npay.models.datasets.SubscriptionCreatedResponse;
import com.naranya.npay.npaydemo.R;
import com.naranya.npay.npaydemo.models.ItemSubscription;
import com.naranya.npay.utils.EasyFonts;

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
public class RecurringBillingAdapter extends RecyclerView.Adapter<RecurringBillingAdapter.ViewHolder> implements PopupMenu.OnMenuItemClickListener {
    private Activity context;
    private List<ItemSubscription> items;
    private int position;

    public RecurringBillingAdapter(Activity context, List<ItemSubscription> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public RecurringBillingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_subscription, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(RecurringBillingAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ItemSubscription item = items.get(position);

        viewHolder.name.setText( item.getTitle() );
        viewHolder.description.setText( item.getDescription() );
        viewHolder.country.setText( item.getCountry() );
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView description;
        public TextView tag_name;
        public TextView tag_description;
        public TextView tag_country;
        public TextView country;

        public ViewHolder(View itemView) {
            super(itemView);

            name        = (TextView) itemView.findViewById(R.id.title_name_subscription);
            description = (TextView) itemView.findViewById(R.id.title_description_subscription);
            tag_name = (TextView) itemView.findViewById(R.id.tag_name_subscription);
            tag_description = (TextView) itemView.findViewById(R.id.tag_description_subscription);
            tag_country = (TextView) itemView.findViewById(R.id.tag_country_subscription);
            country = (TextView) itemView.findViewById(R.id.title_country_subscription);

            name.setTypeface(EasyFonts.robotoLight(context));
            description.setTypeface(EasyFonts.robotoLight(context));
            tag_name.setTypeface(EasyFonts.robotoBlack(context));
            tag_description.setTypeface(EasyFonts.robotoBlack(context));
            tag_country.setTypeface(EasyFonts.robotoBlack(context));
            country.setTypeface(EasyFonts.robotoLight(context));

            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            position = getLayoutPosition();

            PopupMenu popupMenu = new PopupMenu(context, view);
            popupMenu.setOnMenuItemClickListener(RecurringBillingAdapter.this);
            popupMenu.inflate(R.menu.popup_menu);
            popupMenu.setGravity(Gravity.RIGHT);
            popupMenu.show();
        }
    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_create_subscription:
                new NPay.NPayBuilder().createSubscription(context, items.get(position).getIdService(), new OnSubscriptionCreated() {

                    @Override
                    public void onDialogAccept() {}

                    @Override
                    public void onDialogCancel() {}

                    @Override
                    public void onSubscriptionCreatedResponse(SubscriptionCreatedResponse result, String jsonResponse) {

                    }

                    @Override
                    public void onError(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Toast.makeText(context, responseString, Toast.LENGTH_LONG).show();
                    }
                });
                return true;

            case R.id.item_cancel_subscription:
                new NPay.NPayBuilder().getQuickSubscriptionInformation(context, items.get(position).getIdService(), new OnQuickSubscriptionInfo() {

                    @Override
                    public void onDialogAccept() {}

                    @Override
                    public void onDialogCancel() {}

                    @Override
                    public void onFinishCheckResponse(CheckSubscriptionResponse result, String jsonResponse, String idSubscription, String currentStatus) {
                        new NPay.NPayBuilder().cancelSubscription(context, items.get(position).getIdService(), idSubscription, new OnSubscriptionCancel() {

                            @Override
                            public void onDialogAccept() {}

                            @Override
                            public void onDialogCancel() {}

                            @Override
                            public void onSubscriptionCancelResponse(SubscriptionCancelResponse result, String jsonResponse) {
                                Log.i("JSON_CREATE", jsonResponse);
                            }

                            @Override
                            public void onError(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                Toast.makeText(context, responseString, Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onError(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                    }
                });
                return true;

            case R.id.item_check_subscription:
                new NPay.NPayBuilder().getQuickSubscriptionInformation(context, items.get(position).getIdService(), new OnQuickSubscriptionInfo() {

                    @Override
                    public void onDialogCancel() {}

                    @Override
                    public void onDialogAccept() {}

                    @Override
                    public void onFinishCheckResponse(CheckSubscriptionResponse result, String jsonResponse, String idSubscription, String currentStatus) {
                        Toast.makeText(context, "ID SUBSCRIPTION: " + idSubscription + " - " + "Status: " + currentStatus, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Toast.makeText(context, responseString, Toast.LENGTH_LONG).show();
                    }
                });
                return true;
        }
        return true;
    }
}