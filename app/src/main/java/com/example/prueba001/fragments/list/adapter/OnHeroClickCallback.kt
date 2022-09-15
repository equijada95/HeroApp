package com.example.prueba001.fragments.list.adapter

import com.example.prueba001.model.HeroModel

interface OnHeroClickCallback {

    fun onHeroClick(hero: HeroModel)

    fun onFavChanged(hero: HeroModel)

}