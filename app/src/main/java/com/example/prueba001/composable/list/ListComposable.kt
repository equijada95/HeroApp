package com.example.prueba001.composable.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.prueba001.R
import com.example.prueba001.bbdd.viewmodel.DataBaseViewModel
import com.example.prueba001.model.HeroModel
import com.example.prueba001.model.mapToDb
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun ListView(heroList: List<HeroModel>) {
    LazyColumn {
        items(
            items = heroList, itemContent = { hero ->
                ItemView(hero = hero)
            }
        )
    }
}

@Composable
private fun ItemView(
    hero: HeroModel,
    dbViewModel: DataBaseViewModel = hiltViewModel()
) {

    var isFav by remember { mutableStateOf(hero.isFavorite) }

    fun setFav() {
        if (isFav) {
            isFav = false
            dbViewModel.deleteHero(hero.mapToDb())
        } else {
            isFav = true
            dbViewModel.insertHero(hero.mapToDb())
        }
    }

    Card(
        elevation = dimensionResource(id = R.dimen.elevation_cardview),
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

            Image(
                if (isFav) painterResource(R.drawable.ic_fav) else painterResource(R.drawable.ic_unfav),
                contentDescription = "",
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.icon_fav_dimen))
                    .height(dimensionResource(id = R.dimen.icon_fav_dimen))
                    .clickable(
                        enabled = true,
                        onClickLabel = "Set Favorite",
                        onClick = { setFav() }
                    ),
                contentScale = ContentScale.Crop
            )
        }
    }
}