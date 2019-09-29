package com.igormeira.comics.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.igormeira.comics.R;
import com.igormeira.comics.model.Comic;
import com.igormeira.comics.util.Currency;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter para listagem de HQs (comics)
 */
public class ListComicsAdapter extends RecyclerView.Adapter<ListComicsAdapter.ViewHolder> {

    private final OnItemClickListener onItemClickListener;
    private final Context context;
    private final List<Comic> comics = new ArrayList<>();

    /**
     * Construtor da classe
     *
     * @param onItemClickListener
     * @param context
     */
    public ListComicsAdapter(OnItemClickListener onItemClickListener, Context context) {
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View createdView = LayoutInflater.from(context).inflate(R.layout.comic_item, parent, false);
        return new ViewHolder(createdView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListComicsAdapter.ViewHolder holder, int position) {
        Comic comic = comics.get(position);
        holder.attach(comic);
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    /**
     * Atualiza a lista mostrada
     *
     * @param comics
     */
    public void updateListComics(List<Comic> comics) {
        notifyItemRangeRemoved(0, this.comics.size());
        this.comics.clear();
        this.comics.addAll(comics);
        this.notifyItemRangeInserted(0, this.comics.size());
    }

    /**
     * ViewHolder do adapter.
     * Atribui os valores aos itens presentes na tela.
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleField;
        private final TextView priceField;
        private Comic comic;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleField = itemView.findViewById(R.id.comic_item_nome);
            priceField = itemView.findViewById(R.id.comic_item_preco);
            configuraItemClique(itemView);
        }

        private void configuraItemClique(@NonNull View itemView) {
            itemView.setOnClickListener(v -> onItemClickListener
                    .onItemClick(getAdapterPosition(), comic));
        }

        void attach(Comic comic) {
            this.comic = comic;
            titleField.setText(comic.getTitle());
            priceField.setText(new Currency().currencyFormat(comic.getPrice()));
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position, Comic comic);
    }

}
