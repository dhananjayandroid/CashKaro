package com.dhananjay.cashkaro_poc.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dhananjay.cashkaro_poc.R;
import com.dhananjay.cashkaro_poc.models.Deal;
import com.dhananjay.cashkaro_poc.utils.listeners.RecyclerViewItemClickListener;

import java.util.ArrayList;

/**
 * This class extends @{@link RecyclerView.Adapter<>} and
 * serves as an adapter for the Deals list
 *
 * @author Dhananjay Kumar
 */
public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.MyViewHolder> {
    private ArrayList<Deal> deals;

    /**
     * Instantiates a new Deals adapter.
     *
     * @param deals the deals
     */
    public DealsAdapter(ArrayList<Deal> deals) {
        this.deals = deals;
    }

    /**
     * ViewHolder class for {@link DealsAdapter}
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivDeal;
        private CheckBox cbFavorite;
        private Button btnGetOffer;
        private ImageButton ibShare;
        private TextView tvDealDesc, tvDealExtra, tvMore;
        private RecyclerView rvDeal;

        public MyViewHolder(View view) {
            super(view);
            ivDeal = (ImageView) view.findViewById(R.id.iv_deal);
            cbFavorite = (CheckBox) view.findViewById(R.id.cb_favorite);
            ibShare = (ImageButton) view.findViewById(R.id.ib_share);
            tvDealDesc = (TextView) view.findViewById(R.id.tv_deal_desc);
            tvDealExtra = (TextView) view.findViewById(R.id.tv_deal_extra);
            btnGetOffer = (Button) view.findViewById(R.id.btn_get_offer);
            tvMore = (TextView) view.findViewById(R.id.tv_more);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_deal, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Deal deal = deals.get(position);
        holder.ivDeal.setImageResource(deal.getDealIconDrawable());
        holder.tvDealDesc.setText(deal.getDealDescription());
        holder.tvDealExtra.setText(deal.getDealExtra());
        holder.tvMore.setText(String.format(holder.tvMore.getContext().getResources().getString(R.string.view_all_offers), deal.getOffersCount()));
    }

    @Override
    public int getItemCount() {
        return deals.size();
    }

    public void changeContent(ArrayList<Deal> deals) {
        this.deals = deals;
        notifyDataSetChanged();
    }
}