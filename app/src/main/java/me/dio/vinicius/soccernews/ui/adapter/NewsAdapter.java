package me.dio.vinicius.soccernews.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.dio.vinicius.soccernews.R;
import me.dio.vinicius.soccernews.databinding.NewsItemBinding;
import me.dio.vinicius.soccernews.domain.News;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<News> news;
    private final FavoriteListener favoriteListener;

    public NewsAdapter(List<News> news, FavoriteListener favoriteListener) {
        this.news = news;
        this.favoriteListener = favoriteListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        News news = this.news.get(position);
        holder.binding.tvTitle.setText(news.getTitle());
        holder.binding.tvDescription.setText(news.getDescription());

        Context context = holder.itemView.getContext();

        Picasso.get().load(news.getImage()).fit().into(holder.binding.ivNews);
        //Implementação da funcionalidade de "Abrir links"
        holder.binding.btOpenLink.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(news.getLink()));
            context.startActivity(i);
        });

        //Implementação da funcionalidade de "Compartilhar"
        holder.binding.ivShare.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, news.getTitle());
            i.putExtra(Intent.EXTRA_TEXT, news.getLink());
            context.startActivity(Intent.createChooser(i, "Share"));
        });
        // Implementação da funcionalidade de "Favoritar"
        holder.binding.ivFavorite.setOnClickListener(view -> {
            news.setFavorite(!news.getFavorite());
            this.favoriteListener.onFavorite(news);
            notifyItemChanged(position);
        });

        int favoriteColor = news.getFavorite() ? R.color.pink_200 : R.color.gray;
        holder.binding.ivFavorite.setColorFilter(context.getResources().getColor(favoriteColor));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public interface FavoriteListener {
        void onFavorite(News news);
    }
}
