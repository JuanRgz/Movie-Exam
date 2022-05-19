package com.study.mymovies.ui.fragment.home

import androidx.fragment.app.viewModels
import com.study.mymovies.R
import com.study.mymovies.core.StatusEnum
import com.study.mymovies.databinding.FragmentHomeBinding
import com.study.mymovies.ui.common.BaseFragment
import com.study.mymovies.ui.utils.showToast

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    val viewModel:HomeViewModel by viewModels()
    override fun initElements() {
        viewModel.apply{
            getPlayingMovies()
            getPopularMovies()
            getTopRatedMovies()
        }
    }

    override fun getLayout(): Int = R.layout.fragment_home

    override fun initObservers() {
        viewModel.playingMovies.observe(viewLifecycleOwner) {
            when(it.status){
                StatusEnum.LOADING -> {
                    showLoading(true)
                }
                StatusEnum.SUCCESS -> {
                    it.data?.results?.let { list ->
                        showPlayingMovies(list)
                    }
                    showLoading(false)
                }
                StatusEnum.ERROR -> {
                    showLoading(false)
                    showToast("Error al obtener los resultados")
                }
            }
        }
        viewModel.popularMovies.observe(viewLifecycleOwner) {
            when(it.status){
                StatusEnum.LOADING -> {
                    showLoading(true)
                }
                StatusEnum.SUCCESS -> {
                    it.data?.results?.let { list ->
                        showPopularMovies(list)
                    }
                    showLoading(false)
                }
                StatusEnum.ERROR -> {
                    showLoading(false)
                    showToast("Error al obtener los resultados")
                }
            }
        }
        viewModel.topRatedMovies.observe(viewLifecycleOwner) {
            when(it.status){
                StatusEnum.LOADING -> {
                    showLoading(true)
                }
                StatusEnum.SUCCESS -> {
                    it.data?.results?.let { list ->
                        showTopRatedMovies(list)
                    }
                    showLoading(false)
                }
                StatusEnum.ERROR -> {
                    showLoading(false)
                    showToast("Error al obtener los resultados")
                }
            }
        }
    }

    override fun initView() {
        bind.apply {

        }
    }
}