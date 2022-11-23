package com.equijada95.heroapp.domain.utils

import com.equijada95.heroapp.data.bbdd.models.HeroDbModel
import com.equijada95.heroapp.data.bbdd.models.appearance.AppearanceDbModel
import com.equijada95.heroapp.data.bbdd.models.biography.BiographyDbModel
import com.equijada95.heroapp.data.bbdd.models.images.ImagesDbModel
import com.equijada95.heroapp.data.bbdd.models.stats.StatsDbModel
import com.equijada95.heroapp.data.api.model.HeroModel
import com.equijada95.heroapp.data.api.model.appearance.AppearanceModel
import com.equijada95.heroapp.data.api.model.biography.BiographyModel
import com.equijada95.heroapp.data.api.model.images.ImagesModel
import com.equijada95.heroapp.data.api.model.stats.StatsModel

fun ImagesModel.mapToDb() = ImagesDbModel(
    Math.random().toInt(),
    xs,
    sm,
    md,
    lg
)

fun ImagesDbModel.mapToModel() = ImagesModel(
    xs,
    sm,
    md,
    lg
)

fun AppearanceModel.mapToDb() = AppearanceDbModel(
    Math.random().toInt(),
    gender,
    race,
    height.toString(),
    weight.toString(),
    eyeColor,
    hairColor
)

fun AppearanceDbModel.mapToModel() = AppearanceModel(
    gender,
    race,
    height.getList(),
    weight.getList(),
    eyeColor,
    hairColor
)

fun BiographyModel.mapToDb() = BiographyDbModel(
    Math.random().toInt(),
    fullName,
    alterEgos,
    aliases.toString(),
    placeOfBirth,
    firstAppearance,
    publisher,
    alignment
)

fun BiographyDbModel.mapToModel() = BiographyModel(
    fullName,
    alterEgos,
    aliases.getList(),
    placeOfBirth,
    firstAppearance,
    publisher,
    alignment
)

fun StatsModel.mapToDb() = StatsDbModel(
    Math.random().toInt(),
    intelligence,
    strength,
    speed,
    durability,
    power,
    combat
)

fun StatsDbModel.mapToModel() = StatsModel(
    intelligence,
    strength,
    speed,
    durability,
    power,
    combat
)

fun HeroModel.mapToDb() = HeroDbModel(
    id,
    images?.mapToDb(),
    name,
    powerstats?.mapToDb(),
    appearance?.mapToDb(),
    biography?.mapToDb(),
)

fun HeroDbModel.mapToModel() = HeroModel(
    id,
    images?.mapToModel(),
    name,
    powerstats?.mapToModel(),
    appearance?.mapToModel(),
    biography?.mapToModel(),
    true
)

fun List<HeroDbModel>.mapToModel() = map { it.mapToModel() }