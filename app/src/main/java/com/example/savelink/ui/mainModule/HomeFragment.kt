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
import com.example.savelink.ui.SelectCtgModule.SelectCategory
import com.example.savelink.ui.addCategory.AddCategory
import com.example.savelink.ui.categoryModule.Adapter.CategoryAdapter
import com.example.savelink.ui.mainModule.adapter.ListLinkAdapter
import com.example.savelink.ui.mainModule.mainViewModel.GetLinkViewModel
import com.example.savelink.utils.IOnCategoryListener
import com.example.savelink.utils.IOnClickListener
import com.example.savelink.utils.SaveLinkApplication
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), IOnClickListener,IOnCategoryListener {
    private lateinit var mAdapter: ListLinkAdapter
    private lateinit var mAdapterCategory: CategoryAdapter
    private lateinit var mGridLayout: GridLayoutManager
    private lateinit var mBinding: FragmentHomeBinding
    private val viewModelProvide: GetLinkViewModel by lazy {  ViewModelProvider(requireActivity())[GetLinkViewModel::class.java] }
    private lateinit var appContext: Context


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        appContext = requireContext().applicationContext
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRVCategory()
        setupRecyclerView()
        setupInitViewModel()
        swipeRefres()
        setupBtns()
    }


    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
            ------- SETUP VIEW MODEL ---------
    -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* */

    private fun setupInitViewModel() {
       /* viewModelProvide.isAddBookToCategory.observe(viewLifecycleOwner){
            if(it){
                mAdapter.teses()
            }
        }*/
        setupViewModelGetLink()
        setupViewModelGetCategory()
        setupViewModeHeaderCtg()
        setupViewModeResponse()

    }
    private  fun setupViewModeHeaderCtg(){
        viewModelProvide.headerCategory.observe(viewLifecycleOwner){
            mBinding.tvHeaderCategory.setText(it)
        }
    }
    private  fun setupViewModeResponse(){
        viewModelProvide.responseAction.observe(viewLifecycleOwner){
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()

        }
    }

    private  fun setupViewModelGetCategory(){
        viewModelProvide.getListCategory().observe(viewLifecycleOwner){
            mAdapterCategory.submitList(it)

        }
    }

    private  fun setupViewModelGetLink(){
        viewModelProvide.getListLink().observe(viewLifecycleOwner) {
            mAdapter.submitList(it)
            visibilityImgEmptry(it)
        }
    }
    private fun setupViewModelLinkByCategory(category:String){
        viewModelProvide.getListLinkCategory(category).observe(viewLifecycleOwner) {
            mAdapter.submitList(it)
        }
    }

    private  fun visibilityImgEmptry(it:MutableList<LinkEnt>){
        mBinding.imEmptry.visibility=if(it.isEmpty())View.VISIBLE else View.GONE
    }
    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
            ------- SETUP RECYCLER VIEW ---------
    -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* */

    private fun setupRVCategory(){
        mAdapterCategory = CategoryAdapter(this)
        mGridLayout = GridLayoutManager(appContext, 1)
        mBinding.rvCategory.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = mAdapterCategory
        }

    }

    private fun setupRecyclerView() {
        mAdapter = ListLinkAdapter(this)
        mGridLayout = GridLayoutManager(appContext, 1)
        mBinding.recyclerview.apply {
            //setHasFixedSize(true)
            layoutManager = mGridLayout
            adapter = mAdapter
        }
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

    private fun swipeRefres() {
        mBinding.swipe.setOnRefreshListener {
            setupViewModelGetLink()
            Toast.makeText(requireContext(), "Todo los link", Toast.LENGTH_SHORT).show()
            viewModelProvide.setHeaderCategory("Home")
            mBinding.swipe.isRefreshing=false
        }
    }

    private fun setupBtns(){
        mBinding.btnNewCategory.setOnClickListener {
            AddCategory().show(parentFragmentManager, AddCategory::class.java.simpleName)
        }
    }


    /*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
              ------- OVERRIDE FUNCTION ---------
      -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* */

    override fun onClick(linkEnt: LinkEnt) {
        AlertDialog.Builder(activity).apply {
            setTitle("Alert")
            setMessage("Are you sure you want to go to the web?")
            setNegativeButton("Cancel", null)
            setPositiveButton("Go website") { _, _ -> goToWebsite(linkEnt.link) }

        }.show()

    }

    override fun isFavorite(linkEnt: LinkEnt) { viewModelProvide.updateLinkFavorite(linkEnt) }

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

    override fun bookAdA(linkEnt: LinkEnt) {
        viewModelProvide.setlink(linkEnt)
        val fragment = SelectCategory()
        fragment.show(parentFragmentManager.beginTransaction(), SelectCategory::class.java.simpleName)

        //AddCategory11().show(parentFragmentManager, AddCategory11::class.java.simpleName)

    }

    override fun onClickCategory(categoryEnt: CategoryEnt) {
        setupViewModelLinkByCategory(categoryEnt.category)
        viewModelProvide.setHeaderCategory(categoryEnt.category)

    }




}