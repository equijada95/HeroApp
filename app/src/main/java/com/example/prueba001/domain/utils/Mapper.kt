package com.example.prueba001.domain.utils

import com.example.prueba001.data.bbdd.models.HeroDbModel
import com.example.prueba001.data.bbdd.models.appearance.AppearanceDbModel
import com.example.prueba001.data.bbdd.models.biography.BiographyDbModel
import com.example.prueba001.data.bbdd.models.images.ImagesDbModel
import com.example.prueba001.data.bbdd.models.stats.StatsDbModel
import com.example.prueba001.domain.api.model.HeroModel
import com.example.prueba001.domain.api.model.appearance.AppearanceModel
import com.example.prueba001.domain.api.model.biography.BiographyModel
import com.example.prueba001.domain.api.model.images.ImagesModel
import com.example.prueba001.domain.api.model.stats.StatsModel

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