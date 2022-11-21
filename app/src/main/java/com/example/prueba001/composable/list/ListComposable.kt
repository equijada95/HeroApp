package com.example.prueba001.composable.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.prueba001.R
import com.example.prueba001.bbdd.viewmodel.DataBaseViewModel
import com.example.prueba001.composable.customViews.LoadingComponent
import com.example.prueba001.composable.customViews.SearchBar
import com.example.prueba001.model.HeroModel
import com.example.prueba001.test.ModelTest
import com.example.prueba001.utils.setListWithFavorites
import com.example.prueba001.utils.mapToDb
import com.example.prueba001.utils.mapToModel
import com.example.prueba001.viewModels.HeroViewModel
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListComposable(
    heroViewModel: HeroViewModel = hiltViewModel(),
    goToDetail: (HeroModel) -> Unit
) {

    val heroList = heroViewModel.heroes.observeAsState(listOf()).value

    val refreshing by heroViewModel.isRefreshing.collectAsState()

    var searchText by remember { mutableStateOf("") }

    val pullRefreshState = rememberPullRefreshState(refreshing, {
        if (searchText.isNotEmpty()) {
            heroViewModel.refreshSearch(searchText)
        } else {
            heroViewModel.refresh()
        }
    })

    fun setFav(hero: HeroModel) {
        if (!hero.isFavorite) { // funciona al revés porque ya se ha cambiado la variable fav del objeto
            heroViewModel.deleteHero(hero.mapToDb())
        } else {
            heroViewModel.insertHero(hero.mapToDb())
        }
    }

  //  var heroes = heroList
//
  //  heroes.setListWithFavorites(favList)
//
  //  if (heroList.isEmpty() && searchText.isEmpty()) heroes = favList.mapToModel()
    
    ListView(
        heroList = heroList,
        refreshing = refreshing,
        pullRefreshState = pullRefreshState,
        isSearch = searchText.isNotEmpty(),
        goToDetail = goToDetail,
        setFav = { setFav(it) },
        setSearch = {
            searchText = it
            heroViewModel.search(it)
        }
    )
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ListView(
    heroList: List<HeroModel>,
    refreshing: Boolean,
    pullRefreshState: PullRefreshState,
    isSearch: Boolean,
    goToDetail: (HeroModel) -> Unit,
    setFav: (HeroModel) -> Unit,
    setSearch: (String) -> Unit
) {

    Column {
        SearchBar(setSearch = setSearch)

        Box(Modifier.pullRefresh(pullRefreshState)) {
            if (heroList.isEmpty() && !isSearch) {
                LoadingComponent()
            } else {
                ListItems(heroList = heroList, goToDetail = goToDetail, setFav = setFav)
            }
            PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
        }
    }
}

@Composable
private fun ListItems(
    heroList: List<HeroModel>,
    goToDetail: (HeroModel) -> Unit,
    setFav: (HeroModel) -> Unit
) {
    LazyColumn {
        items(
            items = heroList, itemContent = { hero ->
                ItemView(hero, goToDetail, setFav)
            }
        )
    }
}

@Composable
private fun ItemView(
    hero: HeroModel,
    goToDetail: (HeroModel) -> Unit,
    setFav: (HeroModel) -> Unit
) {

    var isFav by remember { mutableStateOf(false) }

    fun setFav() {
        isFav = !isFav
        hero.isFavorite = !hero.isFavorite
        setFav(hero)
    }

    isFav = hero.isFavorite

    Card(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_constraint))
            .clickable(
                enabled = true,
                onClickLabel = "Go to Detail",
                onClick = { goToDetail(hero) }
            )
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

@Preview(showBackground = true)
@Composable
fun ListItemsPreview() {
    ListItems(heroList = ModelTest.listHeroTest(), goToDetail = {}, setFav = {})
}