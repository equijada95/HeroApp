package com.equijada95.heroapp.domain.utils

fun com.equijada95.heroapp.data.api.model.images.ImagesModel.mapToDb() =
    com.equijada95.heroapp.data.bbdd.models.images.ImagesDbModel(
        Math.random().toInt(),
        xs,
        sm,
        md,
        lg
    )

fun com.equijada95.heroapp.data.bbdd.models.images.ImagesDbModel.mapToModel() =
    com.equijada95.heroapp.data.api.model.images.ImagesModel(
        xs,
        sm,
        md,
        lg
    )

fun com.equijada95.heroapp.data.api.model.appearance.AppearanceModel.mapToDb() =
    com.equijada95.heroapp.data.bbdd.models.appearance.AppearanceDbModel(
        Math.random().toInt(),
        gender,
        race,
        height.toString(),
        weight.toString(),
        eyeColor,
        hairColor
    )

fun com.equijada95.heroapp.data.bbdd.models.appearance.AppearanceDbModel.mapToModel() =
    com.equijada95.heroapp.data.api.model.appearance.AppearanceModel(
        gender,
        race,
        height.getList(),
        weight.getList(),
        eyeColor,
        hairColor
    )

fun com.equijada95.heroapp.data.api.model.biography.BiographyModel.mapToDb() =
    com.equijada95.heroapp.data.bbdd.models.biography.BiographyDbModel(
        Math.random().toInt(),
        fullName,
        alterEgos,
        aliases.toString(),
        placeOfBirth,
        firstAppearance,
        publisher,
        alignment
    )

fun com.equijada95.heroapp.data.bbdd.models.biography.BiographyDbModel.mapToModel() =
    com.equijada95.heroapp.data.api.model.biography.BiographyModel(
        fullName,
        alterEgos,
        aliases.getList(),
        placeOfBirth,
        firstAppearance,
        publisher,
        alignment
    )

fun com.equijada95.heroapp.data.api.model.stats.StatsModel.mapToDb() =
    com.equijada95.heroapp.data.bbdd.models.stats.StatsDbModel(
        Math.random().toInt(),
        intelligence,
        strength,
        speed,
        durability,
        power,
        combat
    )

fun com.equijada95.heroapp.data.bbdd.models.stats.StatsDbModel.mapToModel() =
    com.equijada95.heroapp.data.api.model.stats.StatsModel(
        intelligence,
        strength,
        speed,
        durability,
        power,
        combat
    )

fun com.equijada95.heroapp.data.api.model.HeroModel.mapToDb() =
    com.equijada95.heroapp.data.bbdd.models.HeroDbModel(
        id,
        images?.mapToDb(),
        name,
        powerstats?.mapToDb(),
        appearance?.mapToDb(),
        biography?.mapToDb(),
    )

fun com.equijada95.heroapp.data.bbdd.models.HeroDbModel.mapToModel() =
    com.equijada95.heroapp.data.api.model.HeroModel(
        id,
        images?.mapToModel(),
        name,
        powerstats?.mapToModel(),
        appearance?.mapToModel(),
        biography?.mapToModel(),
        true
    )

fun List<com.equijada95.heroapp.data.bbdd.models.HeroDbModel>.mapToModel() = map { it.mapToModel() }