package com.example.prueba001

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.prueba001.databinding.ActivityMainBinding
import com.example.prueba001.fragments.detail.DetailFragment
import com.example.prueba001.fragments.list.ListFragment
import com.example.prueba001.model.HeroModel
import com.example.prueba001.ui.theme.HeroAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var actionBar: ActionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateView()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(binding.container.id, ListFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun openDetail(hero: HeroModel) {
     //   actionBar = supportActionBar
     //   actionBar?.setDisplayHomeAsUpEnabled(true)
     //   val transaction = supportFragmentManager.beginTransaction()
     //   val fr = DetailFragment.newInstance(hero)
     //   transaction.add(binding.container.id, fr)
     //   transaction.addToBackStack(null)
     //   transaction.commit()
        setContent {
            HeroAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Detail(hero)
                }
            }
        }
    }

    private fun inflateView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        actionBar?.setDisplayHomeAsUpEnabled(false)
        supportFragmentManager.popBackStack()
        return true
    }
}

@Composable
fun Detail(hero: HeroModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Card {
            Column(
                modifier = Modifier
                    .padding(10.dp),
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
                    fontSize = 16.sp
                )
                Text(
                    text = "${stringResource(id = R.string.hero_intelligence)} ${hero.powerstats?.intelligence} \n" +
                            "${stringResource(id = R.string.hero_strength)} ${hero.powerstats?.strength} \n" +
                            "${stringResource(id = R.string.hero_speed)} ${hero.powerstats?.speed} \n" +
                            "${stringResource(id = R.string.hero_durability)} ${hero.powerstats?.durability} \n" +
                            "${stringResource(id = R.string.hero_power)} ${hero.powerstats?.power} \n" +
                            "${stringResource(id = R.string.hero_combat)} ${hero.powerstats?.combat}"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Detail(HeroModel.heroTest())
}