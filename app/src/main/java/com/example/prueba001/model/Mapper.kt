package com.example.prueba001.model

import com.example.prueba001.bbdd.models.HeroDbModel
import com.example.prueba001.bbdd.models.appearance.AppearanceDbModel
import com.example.prueba001.bbdd.models.biography.BiographyDbModel
import com.example.prueba001.bbdd.models.images.ImagesDbModel
import com.example.prueba001.bbdd.models.stats.StatsDbModel
import com.example.prueba001.model.appearance.AppearanceModel
import com.example.prueba001.model.biography.BiographyModel
import com.example.prueba001.model.images.ImagesModel
import com.example.prueba001.model.stats.StatsModel
import com.example.prueba001.utils.getList

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
    isFavorite
)

fun HeroDbModel.mapToModel() = HeroModel(
    id,
    images?.mapToModel(),
    name,
    powerstats?.mapToModel(),
    appearance?.mapToModel(),
    biography?.mapToModel(),
    isFavorite
)

fun List<HeroDbModel>.mapToModel() = map { it.mapToModel() }