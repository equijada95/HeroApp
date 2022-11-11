package com.example.prueba001.fragments.list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.prueba001.MainActivity
import com.example.prueba001.R
import com.example.prueba001.bbdd.models.HeroDbModel
import com.example.prueba001.bbdd.models.HeroDbModel.Companion.generateModel
import com.example.prueba001.bbdd.viewmodel.DataBaseViewModel
import com.example.prueba001.databinding.FragmentListBinding
import com.example.prueba001.fragments.list.adapter.ListFragmentAdapter
import com.example.prueba001.fragments.list.adapter.OnHeroClickCallback
import com.example.prueba001.model.HeroModel
import com.example.prueba001.model.HeroModel.Companion.mapList
import com.example.prueba001.utils.compareById
import com.example.prueba001.viewModels.ListScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(), OnHeroClickCallback, OnRefreshListener {
    private lateinit var binding: FragmentListBinding
    private lateinit var dataBaseViewModel: DataBaseViewModel
    private var adapter: ListFragmentAdapter? = null
    private var originalHeroes: List<HeroModel> = arrayListOf()
    private lateinit var actualHeroes: List<HeroModel>
    private var favHeroes: List<HeroDbModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swiperefresh.setOnRefreshListener(this)
        generateView()
    }

    private fun generateView() {
        val owner = activity ?: return
        getFavHeroes()
        binding.progressBar.visibility = View.VISIBLE
        val viewModel = ViewModelProvider(this).get(ListScreenViewModel::class.java)
        viewModel.getHeroes().observe(owner) { heroModels ->
            binding.progressBar.visibility = View.GONE
            setOriginalHeroes(heroModels)
            setFavorites()
            setEditTextListener()
            setAdapter(heroModels, false)
        }
    }

    private fun setOriginalHeroes(originalHeroes: List<HeroModel>) {
        this.originalHeroes = originalHeroes
    }

    private fun getFavHeroes() {
        val owner = activity ?: return
        dataBaseViewModel = ViewModelProvider(this).get(DataBaseViewModel::class.java)
        dataBaseViewModel.getAllFavs()?.observe(owner, Observer { heroModels ->
            favHeroes = heroModels
            setFavorites()
            if (originalHeroes.isEmpty()) return@Observer
            setAdapter(originalHeroes, false)
        })
    }

    private fun setFavorites() {
        originalHeroes.compareById(favHeroes) {
            it.isFavorite = true
        }
    }

    private fun setAdapter(heroModels: List<HeroModel>, fromSearch: Boolean) {
        val ctx = context ?: return
        var heroesMutable = heroModels
        if (heroModels.isEmpty()) {
            if (fromSearch) {
                showError(ctx.getString(R.string.error_search))
                return
            }
            if (favHeroes.isEmpty()) {
                showError(ctx.getString(R.string.error_list))
                return
            }
            heroesMutable = mapList(favHeroes)
            originalHeroes = heroesMutable
        }
        actualHeroes = heroModels
        adapter?.let {
            binding.swiperefresh.isRefreshing = false
            it.setHeroList(heroesMutable)
            it.notifyDataSetChanged()
        } ?: run {
            binding.swiperefresh.isRefreshing = false
            adapter = ListFragmentAdapter(this, ctx)
            adapter?.setHeroList(heroesMutable)
            val layoutManager = LinearLayoutManager(ctx)
            binding.rvHeroes.layoutManager = layoutManager
            adapter?.setHeroList(heroesMutable)
            binding.rvHeroes.adapter = adapter
        }
    }

    private fun showError(message: String) {
        binding.swiperefresh.isRefreshing = false
        adapter?.let {
            it.setHeroList(ArrayList())
            it.notifyDataSetChanged()
        }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun setEditTextListener() {
        binding.etLocationSearcher.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                setAdapter(filterHeroes(charSequence.toString()), true)
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }

    private fun filterHeroes(search: String): List<HeroModel> {
        val heroes = originalHeroes.toMutableList()
        return heroes.filter { hero ->
            hero.name.contains(search)
        }
    }

    override fun onHeroClick(hero: HeroModel) {
        val mainActivity = activity as MainActivity? ?: return
        mainActivity.openDetail(hero)
    }

    override fun onFavChanged(hero: HeroModel) {
        if (hero.isFavorite) {
            hero.isFavorite = false
            generateModel(hero)?.let { dataBaseViewModel.deleteHero(it) }
        } else {
            hero.isFavorite = true
            generateModel(hero)?.let { dataBaseViewModel.insertHero(it) }
        }
    }

    override fun onRefresh() {
        generateView()
    }
}