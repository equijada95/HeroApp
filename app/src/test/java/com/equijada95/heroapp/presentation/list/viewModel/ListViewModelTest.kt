package com.equijada95.heroapp.presentation.list.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.equijada95.heroapp.data.api.model.test.ModelTest
import com.equijada95.heroapp.domain.repository.HeroRepositoryImpl
import com.equijada95.heroapp.domain.result.ApiResult
import com.equijada95.heroapp.presentation.list.state.ListState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*

@OptIn(ExperimentalCoroutinesApi::class)
class ListViewModelTest {

    @RelaxedMockK
    private lateinit var heroRepository: HeroRepositoryImpl

    private lateinit var viewModel: ListViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = ListViewModel(heroRepository)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created, repository is called`() {
        coVerify(exactly = 1) { heroRepository.getHeroes(any(), false) }
    }

    @Test
    fun `when viewmodel is created, get heros`() = runTest {
        // Given
        val heroList = ModelTest.listHeroTest()
        val apiResult = ApiResult.Success(heroList)
        val scope = CoroutineScope(Job())
        coEvery { heroRepository.getHeroes(scope, false) } returns flowOf(apiResult)

        // When

        // Then
        assert(viewModel.state.value == ListState(heroList))
    }
}