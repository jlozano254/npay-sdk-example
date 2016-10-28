package com.naranya.npay.npaydemo.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.naranya.npay.NPay;
import com.naranya.npay.interfaces.OnCheckout;
import com.naranya.npay.models.checkout.NPayCheckoutResponse;
import com.naranya.npay.npaydemo.R;
import com.naranya.npay.npaydemo.models.ItemSingle;
import com.naranya.npay.purchases.PurchaseType;
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
public class SingleBillingAdapter extends RecyclerView.Adapter<SingleBillingAdapter.ViewHolder> implements PopupMenu.OnMenuItemClickListener {
    private Activity context;
    private List<ItemSingle> items;
    private int position;

    public SingleBillingAdapter(Activity context, List<ItemSingle> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public SingleBillingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_single, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(SingleBillingAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ItemSingle item = items.get(position);

        viewHolder.name.setText(item.getName());
        viewHolder.description.setText(item.getDescription());
        viewHolder.sku.setText(item.getSku());

        switch ( item.getType() ) {
            case 0: viewHolder.type.setText(context.getString(R.string.consumable)); break;
            case 1: viewHolder.type.setText(context.getString(R.string.no_consumable)); break;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView description;
        public TextView tag_name;
        public TextView tag_description;
        public TextView tag_sku;
        public TextView sku;
        public TextView type;
        public TextView tag_type;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.title_name_single);
            description = (TextView) itemView.findViewById(R.id.title_description_single);
            tag_name = (TextView) itemView.findViewById(R.id.tag_name_single);
            tag_description = (TextView) itemView.findViewById(R.id.tag_description_single);
            tag_sku = (TextView) itemView.findViewById(R.id.tag_sku);
            sku = (TextView) itemView.findViewById(R.id.title_sku);
            type = (TextView) itemView.findViewById(R.id.title_type);
            tag_type = (TextView) itemView.findViewById(R.id.tag_type);

            name.setTypeface(EasyFonts.robotoLight(context));
            description.setTypeface(EasyFonts.robotoLight(context));
            tag_name.setTypeface(EasyFonts.robotoBlack(context));
            tag_description.setTypeface(EasyFonts.robotoBlack(context));
            tag_sku.setTypeface(EasyFonts.robotoBlack(context));
            sku.setTypeface(EasyFonts.robotoLight(context));
            tag_type.setTypeface(EasyFonts.robotoBlack(context));
            type.setTypeface(EasyFonts.robotoLight(context));

            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            position = getLayoutPosition();

            PopupMenu popupMenu = new PopupMenu(context, view);
            popupMenu.setOnMenuItemClickListener(SingleBillingAdapter.this);
            popupMenu.inflate(R.menu.popup_single);
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
            case R.id.item_purchase:
                if( items.get(position).getType() == PurchaseType.CONSUMABLE ) {
                    new NPay.NPayBuilder().purchase(context, items.get(position).getSku(), PurchaseType.CONSUMABLE, new OnCheckout() {
                        @Override
                        public void onCheckoutResponse(NPayCheckoutResponse nPayCheckoutResponse, String jsonResponse) {

                        }

                        @Override
                        public void onDialogCancel() {}

                        @Override
                        public void onDialogAccept() {}

                        @Override
                        public void onError(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            Toast.makeText(context, responseString, Toast.LENGTH_LONG).show();
                        }
                    });
                } else if( items.get(position).getType() == PurchaseType.NO_CONSUMABLE ) {
                    new NPay.NPayBuilder().purchase(context, items.get(position).getSku(), PurchaseType.NO_CONSUMABLE, new OnCheckout() {
                        @Override
                        public void onCheckoutResponse(NPayCheckoutResponse nPayCheckoutResponse, String jsonResponse) {

                        }

                        @Override
                        public void onDialogCancel() {}

                        @Override
                        public void onDialogAccept() {}

                        @Override
                        public void onError(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            Toast.makeText(context, responseString, Toast.LENGTH_LONG).show();
                        }
                    });
                }

                return true;
        }
        return true;
    }
}