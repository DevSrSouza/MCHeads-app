package br.com.devsrsouza.mcheads.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import br.com.devsrsouza.mcheads.R
import br.com.devsrsouza.mcheads.common.HeadCategory
import br.com.devsrsouza.mcheads.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this).get<HomeViewModel>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.headList.adapter = HeadGridAdapter()
        binding.executePendingBindings()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.search_item -> {
                // todo
            }

            R.id.alphabet_item -> viewModel.updateCategory(HeadCategory.ALPHABET)
            R.id.animals_item -> viewModel.updateCategory(HeadCategory.ANIMALS)
            R.id.blocks_item -> viewModel.updateCategory(HeadCategory.BLOCKS)
            R.id.decoration_item -> viewModel.updateCategory(HeadCategory.DECORATION)
            R.id.humans_item -> viewModel.updateCategory(HeadCategory.HUMANS)
            R.id.humanoid_item -> viewModel.updateCategory(HeadCategory.HUMANOID)
            R.id.monsters_item -> viewModel.updateCategory(HeadCategory.MONSTERS)
            R.id.plants_item -> viewModel.updateCategory(HeadCategory.PLANTS)
            R.id.food_and_drinks_item -> viewModel.updateCategory(HeadCategory.FOOD_AND_DRINKS)
        }
        return true
    }
}