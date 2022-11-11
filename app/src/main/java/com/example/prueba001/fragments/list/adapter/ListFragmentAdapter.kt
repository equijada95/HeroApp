package com.example.prueba001.fragments.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.prueba001.R
import com.example.prueba001.databinding.FragmentHeroListItemBinding
import com.example.prueba001.model.HeroModel

class ListFragmentAdapter(callback: OnHeroClickCallback, context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var heroList: List<HeroModel> = emptyList()
    private val context: Context
    private val callback: OnHeroClickCallback

    init {
        this.context = context
        this.callback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val fragmentHeroListItemBinding = FragmentHeroListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroViewHolder(fragmentHeroListItemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val heroViewHolder = holder as HeroViewHolder
        heroViewHolder.bind(heroList[position])
    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    private inner class HeroViewHolder(private val binding: FragmentHeroListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hero: HeroModel) {
            hero.images?.let {
                Glide.with(context)
                    .load(it.lg)
                    .transform(CircleCrop())
                    .into(binding.ivHero)
            }
            binding.tvName.text = hero.name
            hero.powerstats?.let { stats ->
                with(binding) {
                    tvIntelligence.text = context.getString(R.string.hero_alignment) + stats.intelligence
                    tvStrenght.text = context.getString(R.string.hero_strength) + stats.strength
                    tvSpeed.text = context.getString(R.string.hero_speed) + stats.speed
                    tvDurability.text = context.getString(R.string.hero_durability) + stats.durability
                    tvPower.text = context.getString(R.string.hero_power) + stats.power
                    tvCombat.text = context.getString(R.string.hero_combat) + stats.combat
                }
            }
            if (hero.isFavorite) {
                binding.ivFav.background = AppCompatResources.getDrawable(context, R.drawable.ic_fav)
            } else {
                binding.ivFav.background = AppCompatResources.getDrawable(context, R.drawable.ic_unfav)
            }
            binding.container.setOnClickListener { callback.onHeroClick(hero) }
            binding.ivFav.setOnClickListener { callback.onFavChanged(hero) }
        }
    }
}