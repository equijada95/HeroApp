package com.equijada95.heroapp.presentation.detail.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.equijada95.heroapp.R
import com.equijada95.heroapp.data.api.model.HeroModel
import com.equijada95.heroapp.data.api.model.test.ModelTest
import com.equijada95.heroapp.presentation.customViews.TopBackBar
import com.equijada95.heroapp.presentation.detail.viewModel.DetailViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailComposable(
    hero: HeroModel,
    viewModel: DetailViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {
        TopBackBar(title = hero.name)
        ItemView(hero) { viewModel.setFav(hero) }
    }
}

@Composable
private fun ItemView(
    hero: HeroModel,
    setFav: () -> Unit
) {

    var isFav by remember { mutableStateOf(hero.isFavorite) }

    Card(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_constraint))
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_constraint)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            hero.images?.lg?.let {
                GlideImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(CircleShape),
                    imageModel = it,
                    contentScale = ContentScale.Crop,
                )
            }
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
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_constraint))
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
    Card(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_constraint))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_constraint)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            hero.biography?.let { biography ->
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
            Image(
                if (isFav) painterResource(R.drawable.ic_fav) else painterResource(R.drawable.ic_unfav),
                contentDescription = "",
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.icon_fav_dimen))
                    .height(dimensionResource(id = R.dimen.icon_fav_dimen))
                    .clickable(
                        enabled = true,
                        onClickLabel = "Set Favorite",
                        onClick = {
                            setFav()
                            isFav = !isFav
                        }
                    ),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    DetailComposable(ModelTest.heroTest())
}