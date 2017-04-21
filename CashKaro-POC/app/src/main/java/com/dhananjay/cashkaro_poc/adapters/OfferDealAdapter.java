package com.dhananjay.cashkaro_poc.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dhananjay.cashkaro_poc.R;
import com.dhananjay.cashkaro_poc.models.Deal;
import com.dhananjay.cashkaro_poc.utils.listeners.RecyclerViewItemClickListener;

import java.util.ArrayList;

/**
 * This class extends @{@link RecyclerView.Adapter<>} and
 * serves as an adapter for the Deals
 *
 * @author Dhananjay Kumar
 */
public class OfferDealAdapter extends RecyclerView.Adapter<OfferDealAdapter.MyViewHolder> {
    private ArrayList<Deal> deals;
    private RecyclerViewItemClickListener clickListener;

    /**
     * Instantiates a new Offer deal adapter.
     *
     * @param deals the deals
     */
    public OfferDealAdapter(ArrayList<Deal> deals) {
        this.deals = deals;
    }

    /**
     * Sets click listener.
     *
     * @param clickListener the click listener
     */
    public void setClickListener(RecyclerViewItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    /**
     * ViewHolder class for {@link OfferDealAdapter}
     */
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button btnGetOffer;

        /**
         * Instantiates a new My view holder.
         *
         * @param view the view
         */
        public MyViewHolder(View view) {
            super(view);
            btnGetOffer = (Button) view.findViewById(R.id.btn_get_offer);
            btnGetOffer.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_offer_deal, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 5;
    }

}