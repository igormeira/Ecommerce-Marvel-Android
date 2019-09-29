package com.igormeira.comics.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.igormeira.comics.R;
import com.igormeira.comics.model.Comic;
import com.igormeira.comics.util.Utils;

import java.util.ArrayList;
import java.util.List;


public class ListShopAdapter extends RecyclerView.Adapter<ListShopAdapter.ViewHolder> {

    private final Context context;
    private final List<Comic> comics = new ArrayList<>();

    public ListShopAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View createdView = LayoutInflater.from(context).inflate(R.layout.shop_item, parent, false);
        return new ViewHolder(createdView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListShopAdapter.ViewHolder holder, int position) {
        Comic comic = comics.get(position);
        holder.attach(comic);
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    public void updateListShop(List<Comic> newComics) {
        new Utils(context).sharedUpdateComics(newComics);
        notifyItemRangeRemoved(0, this.comics.size());
        this.comics.clear();
        this.comics.addAll(newComics);
        this.notifyItemRangeInserted(0, this.comics.size());
    }

    private void remove(Comic comic) {
        List<Comic> comics;
        Gson gson = new Gson();
        String response = new Utils(context).sharedGetComics();
        if (response != null) {
            comics = gson.fromJson(response,
                    new TypeToken<List<Comic>>(){}.getType());
            if (comics.size() > 0) {
                for (int i = 0; i < comics.size(); i++) {
                    if (comics.get(i).equals(comic)) {
                        comics.remove(i);
                        updateListShop(comics);
                    }
                }
            }
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleField, priceField;
        private Button removeButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleField = itemView.findViewById(R.id.shop_item_name);
            priceField = itemView.findViewById(R.id.shop_item_price);
            removeButton = itemView.findViewById(R.id.remove_button);
        }

        void attach(Comic comic) {
            titleField.setText(comic.getTitle());
            priceField.setText(new Utils().currencyFormat(comic.getPrice()));
            removeButton.setOnClickListener(v -> remove(comic));
        }

    }
}
