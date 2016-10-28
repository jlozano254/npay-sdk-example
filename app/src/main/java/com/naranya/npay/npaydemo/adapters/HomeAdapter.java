package com.naranya.npay.npaydemo.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.naranya.npay.npaydemo.R;
import com.naranya.npay.npaydemo.models.ItemHome;
import com.naranya.npay.npaydemo.views.RecurringBillingActivity;
import com.naranya.npay.npaydemo.views.SingleBillingActivity;
import com.naranya.npay.utils.EasyFonts;

import java.util.List;

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
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Activity context;
    private List<ItemHome> items;

    public HomeAdapter(Activity context, List<ItemHome> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_home, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ItemHome item = items.get(position);

        viewHolder.name.setText( item.getTitle() );
        viewHolder.icon.setImageResource(item.getIconResource());

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);

            name        = (TextView) itemView.findViewById(R.id.item_title_home);
            name.setTypeface(EasyFonts.robotoMedium(context));
            name.setTextColor(context.getResources().getColor(R.color.npay_base_color));

            icon = (ImageView) itemView.findViewById(R.id.item_icon_home);

            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();

            switch ( position ) {
                case 0:
                    Intent i = new Intent(context, RecurringBillingActivity.class);
                    i.putExtra("TYPE", 0);
                    context.startActivity(i);
                    break;

                case 1:
                    Intent i2 = new Intent(context, RecurringBillingActivity.class);
                    i2.putExtra("TYPE", 1);
                    context.startActivity(i2);
                    break;

                case 2:
                    Intent i3 = new Intent(context, SingleBillingActivity.class);
                    i3.putExtra("TYPE", 0);
                    context.startActivity(i3);
                    break;

                case 3:
                    Intent i4 = new Intent(context, SingleBillingActivity.class);
                    i4.putExtra("TYPE", 1);
                    context.startActivity(i4);
                    break;

            }
        }
    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return items.size();
    }
}