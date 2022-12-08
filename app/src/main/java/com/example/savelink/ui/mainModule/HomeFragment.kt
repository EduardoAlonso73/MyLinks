package com.example.savelink.ui.mainModule

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.savelink.data.entities.CategoryEnt
import com.example.savelink.data.entities.LinkEnt
import com.example.savelink.databinding.FragmentHomeBinding
import com.example.savelink.ui.categoryModule.Adapter.CategoryAdapter
import com.example.savelink.ui.mainModule.adapter.ListLinkAdapter
import com.example.savelink.ui.mainModule.mainViewModel.GetLinkViewModel
import com.example.savelink.utils.IOnClickListener

class HomeFragment : Fragment(), IOnClickListener {
    private lateinit var mAdapter: ListLinkAdapter
    private lateinit var mAdapterCategory: CategoryAdapter
    private lateinit var mGridLayout: GridLayoutManager
    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var viewModelProvide: GetLinkViewModel
    private lateinit var appContext: Context


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        appContext = requireContext().applicationContext
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setuprvCatogory()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModelProvide = ViewModelProvider(requireActivity())[GetLinkViewModel::class.java]

        viewModelProvide.getListLink().observe(viewLifecycleOwner) {
            mAdapter.submitList(it)
        }
    }
    private fun setuprvCatogory(){
        mAdapterCategory = CategoryAdapter()
        mGridLayout = GridLayoutManager(appContext, 1)
        mBinding.rvCategory.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = mAdapterCategory
        }
        mAdapterCategory.submitList(mutableListOf(
            CategoryEnt(1,"Categori1"),
            CategoryEnt(2,"Categori1"),
            CategoryEnt(3,"Categori1"),
            CategoryEnt(4,"Categori1"),
            CategoryEnt(5,"Categori1"),
            CategoryEnt(6,"Categori1"),

        ))
    }

    private fun setupRecyclerView() {
        mAdapter = ListLinkAdapter(this)
        mGridLayout = GridLayoutManager(appContext, 1)
        mBinding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = mGridLayout
            adapter = mAdapter
        }
    }


    override fun onClick(linkEnt: LinkEnt) {
        AlertDialog.Builder(activity).apply {
            setTitle("Alert")
            setMessage("Are you sure you want to go to the web?")
            setNegativeButton("Cancel", null)
            setPositiveButton("Go website") { _, _ -> goToWebsite(linkEnt.link) }

        }.show()

    }

    override fun isFavorite(linkEnt: LinkEnt) {
        Toast.makeText(appContext, linkEnt.isFavorite.toString(), Toast.LENGTH_SHORT).show()
        viewModelProvide.updateLinkFavorite(linkEnt)
    }

    override fun removeLink(linkEnt: LinkEnt) {

        AlertDialog.Builder(activity).apply {
            setTitle("Alert")
            setMessage("Are you sure you want delete link?")
            setNegativeButton("Cancel", null)
            setPositiveButton("Delete") { _, _ ->  viewModelProvide.deleteLink(linkEnt) }

        }.show()


    }

    override fun shareLink(link: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, link)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)

    }

    override fun copiClipboard(link: String) {
        val clipboard = appContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip: ClipData = ClipData.newPlainText("link", link)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(appContext, "Copied", Toast.LENGTH_SHORT).show()
    }


/*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
------- Go to WebSite ---------
-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* */

    private fun goToWebsite(website: String) {

        if (website.isNotEmpty()) {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(website)
            startActivity(openURL)
        }


    }

}