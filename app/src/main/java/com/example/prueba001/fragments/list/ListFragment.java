package com.example.prueba001.fragments.list;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.prueba001.MainActivity;
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
public class ListFragment extends Fragment implements OnHeroClickCallback {

    private FragmentListBinding binding;

    private ListFragmentAdapter adapter;
    private List<HeroModel> originalHeros = new ArrayList<>();
    private List<HeroModel> actualHeros = new ArrayList<>();

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
        // TODO Mostrar algun tipo de spinner o cargando
        ListScreenViewModel viewModel = new ViewModelProvider(this).get(ListScreenViewModel.class);
        viewModel.getHeros().observe(getActivity(), new Observer<List<HeroModel>>() {
            @Override
            public void onChanged(List<HeroModel> heroModels) {
                // TODO Ocultar algun tipo de spinner o cargando
                setOriginalHeros(heroModels);
                setEditTextListener();
                setAdapter(heroModels);
            }
        });
    }

    public void setOriginalHeros(List<HeroModel> originalHeros) {
        this.originalHeros = originalHeros;
    }

    private void setAdapter(List<HeroModel> heroModels) {
        if (heroModels == null || heroModels.isEmpty()) return;
        this.actualHeros = heroModels;
        if (adapter != null) {
            adapter.setHeroList(heroModels);
            adapter.notifyDataSetChanged();
        } else {
            adapter = new ListFragmentAdapter(this, getContext());
            adapter.setHeroList(heroModels);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            binding.rvHeros.setLayoutManager(layoutManager);
            adapter.setHeroList(heroModels);
            binding.rvHeros.setAdapter(adapter);
        }
    }

    private void setEditTextListener() {
        binding.etLocationSearcher.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setAdapter(filterHeros(charSequence.toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private List<HeroModel> filterHeros(String search) {
        return ListUtils.filter(originalHeros, new ListUtils.Predicate<HeroModel>() {
            @Override
            public boolean evaluate(HeroModel element) {
                return element.getName().contains(search);
            }
        });
    }

    @Override
    public void onHeroClick(@NonNull HeroModel hero) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.openDetail(hero);
    }
}
