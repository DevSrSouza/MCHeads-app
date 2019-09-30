package br.com.devsrsouza.mcheads.ui.home

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import br.com.devsrsouza.mcheads.R
import br.com.devsrsouza.mcheads.common.HeadCategory
import br.com.devsrsouza.mcheads.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val debug = true

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(
            this,
            HomeViewModelFactory(activity!!.application, debug)
        ).get<HomeViewModel>()
    }

    private var menu: Menu? = null

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

        setHasOptionsMenu(true)

        viewModel.category.observe(viewLifecycleOwner, Observer {
            if (menu != null) updateMenuItemCheck(it)
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        this.menu = menu

        inflater.inflate(R.menu.home_menu, menu)

        val search = menu.findItem(R.id.search_item)?.actionView as SearchView?

        search?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchHeads(newText)

                return true
            }

        })

        search?.setOnCloseListener {
            viewModel.searchHeads(null)

             false
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        menu = null
        super.onDestroy()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        updateMenuItemCheck(viewModel.category.value)
    }

    private val categoriesMenuResources = mapOf(
        HeadCategory.ALPHABET to R.id.alphabet_item,
        HeadCategory.ANIMALS to R.id.animals_item,
        HeadCategory.BLOCKS to R.id.blocks_item,
        HeadCategory.DECORATION to R.id.decoration_item,
        HeadCategory.HUMANS to R.id.humans_item,
        HeadCategory.HUMANOID to R.id.humanoid_item,
        HeadCategory.MONSTERS to R.id.monsters_item,
        HeadCategory.PLANTS to R.id.plants_item,
        HeadCategory.MISCELLANEOUS to R.id.miscellaneous_item,
        HeadCategory.FOOD_AND_DRINKS to R.id.food_and_drinks_item
    )

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.all_item) {
            viewModel.updateCategory(null)
        } else {
            categoriesMenuResources.entries.find { it.value == item.itemId }?.key?.also {
                viewModel.updateCategory(it)
            }
        }
        return true
    }

    private fun updateMenuItemCheck(category: HeadCategory?) {
        fun turn(resource: Int, value: Boolean) {
            menu?.findItem(resource)?.isChecked = value
        }

        fun uncheck(resource: Int) = turn(resource, false)
        fun check(resource: Int) = turn(resource, true)

        val allItem = R.id.all_item

        uncheck(allItem)
        for (r in categoriesMenuResources.values) uncheck(r)

        if (category == null) check(allItem)
        else categoriesMenuResources.get(category)?.also { check(it) }
    }
}