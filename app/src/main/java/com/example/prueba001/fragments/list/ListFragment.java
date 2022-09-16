package com.example.prueba001.fragments.list;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.prueba001.MainActivity;
import com.example.prueba001.R;
import com.example.prueba001.bbdd.models.HeroDbModel;
import com.example.prueba001.bbdd.viewmodel.DataBaseViewModel;
import com.example.prueba001.databinding.FragmentListBinding;
import com.example.prueba001.fragments.list.adapter.ListFragmentAdapter;
import com.example.prueba001.fragments.list.adapter.OnHeroClickCallback;
import com.example.prueba001.model.HeroModel;
import com.example.prueba001.utils.ListUtils;
import com.example.prueba001.viewModels.ListScreenViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListFragment extends Fragment implements OnHeroClickCallback, SwipeRefreshLayout.OnRefreshListener {

    private FragmentListBinding binding;

    private DataBaseViewModel dataBaseViewModel;

    private ListFragmentAdapter adapter;
    private List<HeroModel> originalHeroes = new ArrayList<>();
    private List<HeroModel> actualHeroes = new ArrayList<>();
    private List<HeroDbModel> favHeroes = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() == null) return;
        binding.swiperefresh.setOnRefreshListener(this);
        generateView();
    }

    private void generateView() {
        getFavHeroes();
        binding.progressBar.setVisibility(View.VISIBLE);
        ListScreenViewModel viewModel = new ViewModelProvider(this).get(ListScreenViewModel.class);
        viewModel.getHeroes().observe(getActivity(), new Observer<List<HeroModel>>() {
            @Override
            public void onChanged(List<HeroModel> heroModels) {
                binding.progressBar.setVisibility(View.GONE);
                setOriginalHeroes(heroModels);
                setFavorites();
                setEditTextListener();
                setAdapter(heroModels);
            }
        });
    }

    private void setOriginalHeroes(List<HeroModel> originalHeroes) {
        this.originalHeroes = originalHeroes;
    }

    private void getFavHeroes() {
        dataBaseViewModel = new ViewModelProvider(this).get(DataBaseViewModel.class);
        dataBaseViewModel.getAllFavs().observe(getActivity(), new Observer<List<HeroDbModel>>() {
            @Override
            public void onChanged(List<HeroDbModel> heroModels) {
                favHeroes = heroModels;
                setFavorites();
                if (originalHeroes == null || originalHeroes.isEmpty()) return;
                setAdapter(originalHeroes);
            }
        });
    }

    private void setFavorites() {
        for (HeroModel heroModel: originalHeroes) {
            for (HeroDbModel heroDbModel: favHeroes) {
                if (heroModel.getId() == heroDbModel.getId()) {
                    heroModel.setFavorite(true);
                }
            }
        }
    }

    private void setAdapter(List<HeroModel> heroModels) {
        if (heroModels == null) {
            binding.swiperefresh.setRefreshing(false);
            searchError();
            return;
        }
        if (heroModels.isEmpty()) {
            if (favHeroes == null || favHeroes.isEmpty()) {
                binding.swiperefresh.setRefreshing(false);
                Toast.makeText(getContext(), getContext().getString(R.string.error_list), Toast.LENGTH_SHORT).show();
                return;
            }
            heroModels = HeroModel.Companion.mapList(favHeroes);
            this.originalHeroes = heroModels;
        }
        this.actualHeroes = heroModels;
        if (adapter != null) {
            binding.swiperefresh.setRefreshing(false);
            adapter.setHeroList(heroModels);
            adapter.notifyDataSetChanged();
        } else {
            binding.swiperefresh.setRefreshing(false);
            adapter = new ListFragmentAdapter(this, getContext());
            adapter.setHeroList(heroModels);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            binding.rvHeroes.setLayoutManager(layoutManager);
            adapter.setHeroList(heroModels);
            binding.rvHeroes.setAdapter(adapter);
        }
    }

    private void searchError() {
        if (adapter != null) {
            adapter.setHeroList(new ArrayList<>());
            adapter.notifyDataSetChanged();
        }
        Toast.makeText(getContext(), getContext().getString(R.string.error_search), Toast.LENGTH_SHORT).show();
    }

    private void setEditTextListener() {
        binding.etLocationSearcher.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setAdapter(filterHeroes(charSequence.toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private List<HeroModel> filterHeroes(String search) {
        return ListUtils.filter(originalHeroes, new ListUtils.Predicate<HeroModel>() {
            @Override
            public boolean evaluate(HeroModel element) {
                return element.getName().contains(search);
            }
        });
    }

    @Override
    public void onHeroClick(@NonNull HeroModel hero) {
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity == null) return;
        mainActivity.openDetail(hero);
    }

    @Override
    public void onFavChanged(@NonNull HeroModel hero) {
        if (hero == null) return;
        if (hero.isFavorite()) {
            hero.setFavorite(false);
            dataBaseViewModel.deleteHero(HeroDbModel.generateModel(hero));
        } else {
            hero.setFavorite(true);
            dataBaseViewModel.insertHero(HeroDbModel.generateModel(hero));
        }
    }

    @Override
    public void onRefresh() {
        generateView();
    }
}
