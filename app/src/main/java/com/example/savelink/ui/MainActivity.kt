package com.example.savelink.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.savelink.R
import com.example.savelink.databinding.ActivityMainBinding
import com.example.savelink.ui.addModule.AddDialogLinkFragment
import com.example.savelink.ui.favoriteModule.FavoriteFragment
import com.example.savelink.ui.mainModule.HomeFragment


class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private lateinit var mActivityFragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        fbAdd()
        initBottomNav()

    }

    private fun fbAdd(){
        mBinding.fbAdd.setOnClickListener{
            AddDialogLinkFragment().show(supportFragmentManager, AddDialogLinkFragment::class.java.simpleName)

        }
    }


    private fun initBottomNav() {
        val mFragmentManager = supportFragmentManager
        val homeFragment = HomeFragment()
        val favoriteFragment = FavoriteFragment()


        mActivityFragment = homeFragment
        with(mFragmentManager) {
            beginTransaction().add(
                R.id.hostFragments,
                favoriteFragment,
                favoriteFragment::class.java.name
            ).hide(favoriteFragment).commit()
            beginTransaction().add(R.id.hostFragments, homeFragment, homeFragment::class.java.name)
                .commit()
        }

        mBinding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    optionShowFragment(homeFragment)
                }
                R.id.action_favorites -> {
                    optionShowFragment(favoriteFragment)
                }

                else -> false
            }
        }
    }

    private fun optionShowFragment(nameFragment: Fragment): Boolean {
        val mFragmentManager = supportFragmentManager
        mFragmentManager.beginTransaction().hide(mActivityFragment).show(nameFragment).commit()
        mActivityFragment = nameFragment
        return true
    }



}