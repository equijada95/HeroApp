package com.example.prueba001.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.prueba001.R
import com.example.prueba001.bbdd.models.HeroDbModel
import com.example.prueba001.bbdd.viewmodel.DataBaseViewModel
import com.example.prueba001.databinding.FragmentDetailBinding
import com.example.prueba001.model.HeroModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(), View.OnClickListener {

    private lateinit var hero: HeroModel
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DataBaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DataBaseViewModel::class.java]
        createViews()
    }

    private fun createViews() {
        val ctx = context ?: return
        if (hero.isFavorite) {
            binding.ivFav.background = AppCompatResources.getDrawable(ctx, R.drawable.ic_fav)
        } else {
            binding.ivFav.background = AppCompatResources.getDrawable(ctx, R.drawable.ic_unfav)
        }
        binding.ivFav.setOnClickListener(this)
        hero.images?.let { image ->
            Glide.with(ctx)
                .load(image.lg)
                .transform(CircleCrop())
                .into(binding.ivHero)
        }
        binding.tvName.text = hero.name
        hero.powerstats?.let { stats ->
            with (binding) {
                tvIntelligence.text = ctx.getString(R.string.hero_alignment) + stats.intelligence
                tvStrenght.text = ctx.getString(R.string.hero_strength) + stats.strength
                tvSpeed.text = ctx.getString(R.string.hero_speed) + stats.speed
                tvDurability.text = ctx.getString(R.string.hero_durability) + stats.durability
                tvPower.text = ctx.getString(R.string.hero_power) + stats.power
                tvCombat.text = ctx.getString(R.string.hero_combat) + stats.combat
            }
        }
        hero.appearance?.let { appearance ->
            with(binding) {
                tvGender.text = ctx.getString(R.string.hero_gender) + appearance.gender
                tvRace.text = ctx.getString(R.string.hero_race) + appearance.race
                tvHeight.text = ctx.getString(R.string.hero_heigth) + appearance.height
                tvWeight.text = ctx.getString(R.string.hero_weight) + appearance.weight
                tvEyeColor.text = ctx.getString(R.string.hero_eye_color) + appearance.eyeColor
                tvHairColor.text = ctx.getString(R.string.hero_hair_color) + appearance.hairColor
            }
        }
        hero.biography?.let { biography ->
            with (binding) {
                tvFullName.text = ctx.getString(R.string.hero_full_name) + biography.fullName
                tvAlterEgos.text = ctx.getString(R.string.hero_alter_egos) + biography.alterEgos
                tvAliases.text = ctx.getString(R.string.hero_aliases) + biography.aliases
                tvPlaceBirth.text = ctx.getString(R.string.hero_place_birth) + biography.placeOfBirth
                tvFirstAppearance.text = ctx.getString(R.string.hero_first_appearance) + biography.firstAppearance
                tvPublisher.text = ctx.getString(R.string.hero_publisher) + biography.publisher
                tvAlignment.text = ctx.getString(R.string.hero_alignment) + biography.alignment
            }
        }
    }

    private fun setFav() {
        val ctx = context ?: return
        if (hero.isFavorite) {
            hero.isFavorite = false
            HeroDbModel.generateModel(hero)?.let { viewModel.deleteHero(it) }
            binding.ivFav.background = AppCompatResources.getDrawable(ctx, R.drawable.ic_unfav)
        } else {
            hero.isFavorite = true
            HeroDbModel.generateModel(hero)?.let { viewModel.insertHero(it) }
            binding.ivFav.background = AppCompatResources.getDrawable(ctx, R.drawable.ic_fav)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.iv_fav -> setFav()
        }
    }

    companion object {
        fun newInstance(hero: HeroModel): DetailFragment {
            val fr = DetailFragment()
            fr.hero = hero
            return fr
        }
    }
}