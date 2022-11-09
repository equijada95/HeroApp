package com.example.prueba001.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.prueba001.R
import com.example.prueba001.model.HeroModel

@Composable
fun Detail(hero: HeroModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Card(
            elevation = dimensionResource(id = R.dimen.elevation_cardview)
        ) {
            Column(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_constraint)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = hero.images?.lg,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .clip(CircleShape)
                        .fillMaxWidth(),
                    contentDescription = null
                )
                Text(
                    text = hero.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = R.dimen.title_size).value.sp
                )
                hero.powerstats?.let { stats ->
                    Text(
                        text = "${stringResource(id = R.string.hero_intelligence)} ${stats.intelligence} \n" +
                                "${stringResource(id = R.string.hero_strength)} ${stats.strength} \n" +
                                "${stringResource(id = R.string.hero_speed)} ${stats.speed} \n" +
                                "${stringResource(id = R.string.hero_durability)} ${stats.durability} \n" +
                                "${stringResource(id = R.string.hero_power)} ${stats.power} \n" +
                                "${stringResource(id = R.string.hero_combat)} ${stats.combat}",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    )
                }
            }
        }
        hero.appearance?.let { appearance ->
            Card(
                elevation = dimensionResource(id = R.dimen.elevation_cardview)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_constraint)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.hero_appearance),
                        fontWeight = FontWeight.Bold,
                        fontSize = dimensionResource(id = R.dimen.title_size).value.sp
                    )
                    Text(
                        text = "${stringResource(id = R.string.hero_gender)} ${appearance.gender} \n" +
                                "${stringResource(id = R.string.hero_race)} ${appearance.race} \n" +
                                "${stringResource(id = R.string.hero_heigth)} ${appearance.height} \n" +
                                "${stringResource(id = R.string.hero_weight)} ${appearance.weight} \n" +
                                "${stringResource(id = R.string.hero_eye_color)} ${appearance.eyeColor} \n" +
                                "${stringResource(id = R.string.hero_hair_color)} ${appearance.hairColor}",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    )
                }
            }
        }
        hero.biography?.let { biography ->
            Card(
                elevation = dimensionResource(id = R.dimen.elevation_cardview)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_constraint)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.hero_biography),
                        fontWeight = FontWeight.Bold,
                        fontSize = dimensionResource(id = R.dimen.title_size).value.sp
                    )
                    Text(
                        text = "${stringResource(id = R.string.hero_full_name)} ${biography.fullName} \n" +
                                "${stringResource(id = R.string.hero_alter_egos)} ${biography.alterEgos} \n" +
                                "${stringResource(id = R.string.hero_aliases)} ${biography.aliases} \n" +
                                "${stringResource(id = R.string.hero_place_birth)} ${biography.placeOfBirth} \n" +
                                "${stringResource(id = R.string.hero_first_appearance)} ${biography.firstAppearance} \n" +
                                "${stringResource(id = R.string.hero_publisher)} ${biography.publisher} \n" +
                                "${stringResource(id = R.string.hero_alignment)} ${biography.alignment}",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Detail(HeroModel.heroTest())
}