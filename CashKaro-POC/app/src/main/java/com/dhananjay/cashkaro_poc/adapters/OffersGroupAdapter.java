package com.dhananjay.cashkaro_poc.adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dhananjay.cashkaro_poc.R;
import com.dhananjay.cashkaro_poc.models.Deal;
import com.dhananjay.cashkaro_poc.models.OfferGroup;

import java.util.ArrayList;


/**
 * This class extends @{@link RecyclerView.Adapter<>} and
 * serves as an adapter for the vertical Deals group
 *
 * @author Dhananjay Kumar
 */
public class OffersGroupAdapter extends RecyclerView.Adapter<OffersGroupAdapter.MyViewHolder> {
    private ArrayList<OfferGroup> offerGroups;

    /**
     * Instantiates a new Offers group adapter.
     *
     * @param offerGroups the offer groups
     */
    public OffersGroupAdapter(ArrayList<OfferGroup> offerGroups) {
        this.offerGroups = offerGroups;
    }

    /**
     * ViewHolder class for {@link OffersGroupAdapter}
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitleGroup;
        private RecyclerView rvDeal;

        /**
         * Instantiates a new My view holder.
         *
         * @param view the view
         */
        public MyViewHolder(View view) {
            super(view);
            tvTitleGroup = (TextView) view.findViewById(R.id.tv_title_group);
            rvDeal = (RecyclerView) view.findViewById(R.id.rv_deal);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_offer_group, parent, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, true);
        layoutManager.setStackFromEnd(true);
        ((RecyclerView) itemView.findViewById(R.id.rv_deal)).setLayoutManager(layoutManager);
        ((RecyclerView) itemView.findViewById(R.id.rv_deal)).setAdapter(new DealsAdapter(new ArrayList<Deal>()));
        itemView.findViewById(R.id.rv_deal).setNestedScrollingEnabled(false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OfferGroup offerGroup = offerGroups.get(position);
        holder.tvTitleGroup.setText(offerGroup.getOfferTitle());
        ((DealsAdapter) holder.rvDeal.getAdapter()).changeContent(offerGroup.getDeals());
    }

    @Override
    public int getItemCount() {
        return offerGroups.size();
    }

    /**
     * Update content.
     *
     * @param offerGroups the offer groups
     */
    public void updateContent(ArrayList<OfferGroup> offerGroups) {
        this.offerGroups.clear();
        this.offerGroups.addAll(offerGroups);
        notifyDataSetChanged();
    }
}