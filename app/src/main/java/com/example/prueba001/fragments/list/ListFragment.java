package com.example.prueba001.fragments.list;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.prueba001.model.HeroModel;
import com.example.prueba001.viewModels.ListScreenViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() == null) return;
        // TODO Mostrar algun tipo de spinner o cargando
        ListScreenViewModel viewModel = new ViewModelProvider(this).get(ListScreenViewModel.class);
        viewModel.getHeros().observe(getActivity(), new Observer<List<HeroModel>>() {
            @Override
            public void onChanged(List<HeroModel> heroModels) {
                // TODO Ocultar algun tipo de spinner o cargando
            }
        });
    }
}
