package com.equijada95.heroapp.presentation.list.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.equijada95.heroapp.domain.repository.HeroRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
class ListViewModelTest {

    @RelaxedMockK
    private lateinit var heroRepository: HeroRepository

    private lateinit var viewModel: ListViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        viewModel = ListViewModel(heroRepository)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

}