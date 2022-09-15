package com.example.prueba001.fragments.list.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.prueba001.R;
import com.example.prueba001.databinding.FragmentHeroListItemBinding;
import com.example.prueba001.model.HeroModel;

import java.util.ArrayList;
import java.util.List;

public class ListFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HeroModel> heroList;
    private Context context;
    private OnHeroClickCallback callback;

    public ListFragmentAdapter(OnHeroClickCallback callback, Context context) {
        heroList = new ArrayList<>();
        this.context = context;
        this.callback = callback;
    }

    public void setHeroList(List<HeroModel> heroList) {
        this.heroList = heroList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentHeroListItemBinding fragmentHeroListItemBinding = FragmentHeroListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HeroViewHolder(fragmentHeroListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HeroViewHolder heroViewHolder = (HeroViewHolder) holder;
        heroViewHolder.bind(heroList.get(position));
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    private class HeroViewHolder extends RecyclerView.ViewHolder {

        private FragmentHeroListItemBinding binding;

        public HeroViewHolder(@NonNull FragmentHeroListItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bind(HeroModel hero) {
            if (hero.getImages() != null) {
                Glide.with(context)
                        .load(hero.getImages().getLg())
                        .transform(new CircleCrop())
                        .into(binding.ivHero);
            }

            binding.tvName.setText(hero.getName());

            if (hero.getPowerstats() != null) {

                binding.tvIntelligence.setText(context.getString(R.string.hero_alignment) + hero.getPowerstats().getIntelligence());

                binding.tvStrenght.setText(context.getString(R.string.hero_strength) + hero.getPowerstats().getStrength());

                binding.tvSpeed.setText(context.getString(R.string.hero_speed) + hero.getPowerstats().getSpeed());

                binding.tvDurability.setText(context.getString(R.string.hero_durability) + hero.getPowerstats().getDurability());

                binding.tvPower.setText(context.getString(R.string.hero_power) + hero.getPowerstats().getPower());

                binding.tvCombat.setText(context.getString(R.string.hero_combat) + hero.getPowerstats().getCombat());
            }

            if (hero.isFavorite()) {
                binding.ivFav.setVisibility(View.VISIBLE);
            } else {
                binding.ivFav.setVisibility(View.GONE);
            }

            binding.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onHeroClick(hero);
                }
            });
        }

    }

}
