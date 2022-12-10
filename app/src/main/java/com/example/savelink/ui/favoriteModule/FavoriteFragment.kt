package com.example.savelink.ui.favoriteModule

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.savelink.data.entities.LinkEnt
import com.example.savelink.databinding.FragmentFavoriteBinding
import com.example.savelink.ui.favoriteModule.Adapter.LinkFavoriteAdapter
import com.example.savelink.ui.favoriteModule.favoriteViewModel.LinkFavoriteViewModel
import com.example.savelink.utils.IOnClickListener


class FavoriteFragment : Fragment(),IOnClickListener {
    private lateinit var mAdapter: LinkFavoriteAdapter
    private lateinit var mGridLayout: GridLayoutManager
    private lateinit var mBinding: FragmentFavoriteBinding
    private lateinit var viewModelProvide: LinkFavoriteViewModel
    private lateinit var appContext: Context


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        appContext = requireContext().applicationContext
        mBinding=FragmentFavoriteBinding.inflate(inflater,container,false)
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupViewModel()
    }


    private fun setupRecyclerView() {
        mAdapter = LinkFavoriteAdapter(this)
        mGridLayout = GridLayoutManager(appContext, 1)
        mBinding.recyclerviewfv.apply {
            setHasFixedSize(true)
            layoutManager = mGridLayout
            adapter = mAdapter
        }
    }

    private fun setupViewModel() {
        viewModelProvide = ViewModelProvider(requireActivity())[LinkFavoriteViewModel::class.java]
        viewModelProvide.getListLinkFavori().observe(viewLifecycleOwner) {
            mAdapter.submitList(it)
        }
    }

    override fun onClick(linkEnt: LinkEnt) {}

    override fun isFavorite(linkEnt: LinkEnt) {}

    override fun removeLink(linkEnt: LinkEnt) {}

    override fun shareLink(link: String) {}

    override fun copiClipboard(link: String) {}
    override fun bookAdA(linkEnt: LinkEnt) {}
}