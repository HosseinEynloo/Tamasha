package com.hossein.tamasha.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.hossein.tamasha.databinding.FragmentHomeBinding
import com.hossein.tamasha.ui.home.adapters.GenersAdapter
import com.hossein.tamasha.ui.home.adapters.LastMoviesAdapter
import com.hossein.tamasha.ui.home.adapters.TopMoviesAdapter
import com.hossein.tamasha.utils.initRecyclerView
import com.hossein.tamasha.utils.showInvisible
import com.hossein.tamasha.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var topMoviesAdapter: TopMoviesAdapter

    @Inject
    lateinit var genersAdapter: GenersAdapter

    @Inject
    lateinit var lastMoviesAdapter: LastMoviesAdapter
    private val pagerHelper: PagerSnapHelper by lazy { PagerSnapHelper() }
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // call api
        viewModel.loadTopMoviesList(3)
        viewModel.loadGenersList()
        viewModel.loadLastMoviesList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            //Get Top Movies
            viewModel.moviesTopList.observe(viewLifecycleOwner) { response ->
                topMoviesAdapter.differ.submitList(response.data)
                //recyclerView
                recyclerViewTopMovies.initRecyclerView(
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    ), topMoviesAdapter
                )
                //Indicator
                pagerHelper.attachToRecyclerView(recyclerViewTopMovies)
                topMoviesIndicator.attachToRecyclerView(recyclerViewTopMovies, pagerHelper)

            }
            // Get Geners Movies
            viewModel.genresList.observe(viewLifecycleOwner) { genersResponse ->

                genersAdapter.differ.submitList(genersResponse)
                //recyclerView
                recyclerViewGennres.initRecyclerView(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    genersAdapter
                )


            }
            //Get Last Movies
            viewModel.lastMoviesList.observe(viewLifecycleOwner) { response ->
                lastMoviesAdapter.setData(response.data)
                //recyclerView
                recyclerViewLstMovies.initRecyclerView(
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                    ), lastMoviesAdapter
                )
            }
            //Loading
            viewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    moviesLoading.showInvisible(true)
                    nestedScrool.showInvisible(false)
                } else {
                    moviesLoading.showInvisible(false)
                    nestedScrool.showInvisible(true)
                }
            }

        }
    }
}