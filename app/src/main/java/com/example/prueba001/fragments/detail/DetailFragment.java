package com.example.prueba001.fragments.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.prueba001.R;
import com.example.prueba001.bbdd.models.HeroDbModel;
import com.example.prueba001.bbdd.viewmodel.DataBaseViewModel;
import com.example.prueba001.databinding.FragmentDetailBinding;
import com.example.prueba001.model.HeroModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailFragment extends Fragment implements View.OnClickListener {

    private HeroModel hero;

    private FragmentDetailBinding binding;

    private DataBaseViewModel viewModel;

    public static DetailFragment newInstance(HeroModel hero) {
        DetailFragment fr = new DetailFragment();
        fr.hero = hero;
        return fr;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DataBaseViewModel.class);
        createViews();
    }

    private void createViews() {
        if (hero.isFavorite()) {
            binding.ivFav.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.ic_fav));
        } else {
            binding.ivFav.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.ic_unfav));
        }

        binding.ivFav.setOnClickListener(this);

        if (hero.getImages() != null) {
            Glide.with(getContext())
                    .load(hero.getImages().getLg())
                    .transform(new CircleCrop())
                    .into(binding.ivHero);
        }

        binding.tvName.setText(hero.getName());

        if (hero.getPowerstats() != null) {
            binding.tvIntelligence.setText(getContext().getString(R.string.hero_alignment) + hero.getPowerstats().getIntelligence());

            binding.tvStrenght.setText(getContext().getString(R.string.hero_strength) + hero.getPowerstats().getStrength());

            binding.tvSpeed.setText(getContext().getString(R.string.hero_speed) + hero.getPowerstats().getSpeed());

            binding.tvDurability.setText(getContext().getString(R.string.hero_durability) + hero.getPowerstats().getDurability());

            binding.tvPower.setText(getContext().getString(R.string.hero_power) + hero.getPowerstats().getPower());

            binding.tvCombat.setText(getContext().getString(R.string.hero_combat) + hero.getPowerstats().getCombat());
        }

        if (hero.getAppearance() != null) {
            binding.tvGender.setText(getContext().getString(R.string.hero_gender) + hero.getAppearance().getGender());

            binding.tvRace.setText(getContext().getString(R.string.hero_race) + hero.getAppearance().getRace());

            binding.tvHeight.setText(getContext().getString(R.string.hero_heigth) + hero.getAppearance().getHeight());

            binding.tvWeight.setText(getContext().getString(R.string.hero_weight) + hero.getAppearance().getWeight());

            binding.tvEyeColor.setText(getContext().getString(R.string.hero_eye_color) + hero.getAppearance().getEyeColor());

            binding.tvHairColor.setText(getContext().getString(R.string.hero_hair_color) + hero.getAppearance().getHairColor());
        }

        if (hero.getBiography() != null) {
            binding.tvFullName.setText(getContext().getString(R.string.hero_full_name) + hero.getBiography().getFullName());

            binding.tvAlterEgos.setText(getContext().getString(R.string.hero_alter_egos) + hero.getBiography().getAlterEgos());

            binding.tvAliases.setText(getContext().getString(R.string.hero_aliases) + hero.getBiography().getAliases());

            binding.tvPlaceBirth.setText(getContext().getString(R.string.hero_place_birth) + hero.getBiography().getPlaceOfBirth());

            binding.tvFirstAppearance.setText(getContext().getString(R.string.hero_first_appearance) + hero.getBiography().getFirstAppearance());

            binding.tvPublisher.setText(getContext().getString(R.string.hero_publisher) + hero.getBiography().getPublisher());

            binding.tvAlignment.setText(getContext().getString(R.string.hero_alignment) + hero.getBiography().getAlignment());
        }
    }

    private void setFav() {
        if (hero.isFavorite()) {
            hero.setFavorite(false);
            viewModel.deleteHero(HeroDbModel.generateModel(hero));
            binding.ivFav.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.ic_unfav));
        } else {
            hero.setFavorite(true);
            viewModel.insertHero(HeroDbModel.generateModel(hero));
            binding.ivFav.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.ic_fav));
        }
    }

    @Override
    public void onClick(View view) {
        if (view == null) return;

        switch (view.getId()) {
            case R.id.iv_fav:
                setFav();
                break;
        }
    }
}
