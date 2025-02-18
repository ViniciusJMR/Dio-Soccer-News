package me.dio.vinicius.soccernews.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import me.dio.vinicius.soccernews.R;
import me.dio.vinicius.soccernews.databinding.FragmentNewsBinding;
import me.dio.vinicius.soccernews.ui.adapter.NewsAdapter;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private NewsViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));

        observeNews();

        observeStates();

        binding.srlNews.setOnRefreshListener(viewModel::findNews);

        return root;
    }

    private void observeStates() {
        viewModel.getState().observe(getViewLifecycleOwner(), state -> {
            switch (state){
                case DOING:
                    binding.srlNews.setRefreshing(true);
                    break;
                case DONE:
                    binding.srlNews.setRefreshing(false);
                    break;
                case ERROR:
                    binding.srlNews.setRefreshing(false);
                    Snackbar.make(binding.srlNews, R.string.error_network, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void observeNews() {
        viewModel.getNews().observe(getViewLifecycleOwner(), news ->
            binding.rvNews.setAdapter(new NewsAdapter(news, viewModel::saveNews))
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}