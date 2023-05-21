
package com.selincengiz.jronedio.view

import android.animation.ObjectAnimator
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ActionMenuView
import androidx.core.animation.doOnEnd
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.values
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.selincengiz.jronedio.R
import com.selincengiz.jronedio.databinding.ActivityMainBinding
import com.selincengiz.jronedio.model.Test
import com.selincengiz.jronedio.singleton.DataManager


class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    private lateinit var binding: ActivityMainBinding
    private lateinit var adminActionMenuView: ActionMenuView
    private lateinit var homeActionMenuView: ActionMenuView
    private lateinit var sideActionMenuView: ActionMenuView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




        // Read from the database
        val database = Firebase.database
        val myRef = database.reference

        // Verileri tek seferde okumak için
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // verileri dataSnapshot'den alın
                // ...
                for (postSnapshot in dataSnapshot.children) {
                    val value = postSnapshot.getValue(String::class.java)
                    val gson = Gson()
                    val test = gson.fromJson(value, Test::class.java)

                    println(test.header.titleText)
                    DataManager.data.add(test)
                    }

            }

            override fun onCancelled(error: DatabaseError) {
                // okuma işlemi iptal edildiğinde yapılacak işlemler
                // ...
            }
        })




        //////Açılış animasyonu
        val fragments = binding.root.findViewById<FragmentContainerView>(R.id.fragmentContainerView)
        fragments.isVisible=false
        val menu =binding.appBarLayout
        menu.isVisible=false

        val logo = binding.root.findViewById<ImageView>(R.id.animLogo)
        val animator = ObjectAnimator.ofFloat(logo, "rotation", 360f)
        animator.duration = 4000
        animator.startDelay=1000
        animator.start()
        animator.doOnEnd {
            logo.isVisible= false
            fragments.isVisible=true
            menu.isVisible=true

        }

        ////////////////////////////Admin
        adminActionMenuView = findViewById(R.id.admin)
        menuInflater.inflate(R.menu.admin_menu, adminActionMenuView.menu)

        adminActionMenuView.setOnMenuItemClickListener {

            binding.fragmentContainerView.findNavController()
                .navigate(R.id.adminLoginFragment)

            ;true
        }



/////////////////////////Home

        homeActionMenuView = findViewById(R.id.home)
        menuInflater.inflate(R.menu.home_menu, homeActionMenuView.menu)

        homeActionMenuView.setOnMenuItemClickListener {

            binding.fragmentContainerView.findNavController()
                .navigate(R.id.homeFragment)

            ;true
        }


        /////////////////////////SideButton

        sideActionMenuView = findViewById(R.id.side)
        menuInflater.inflate(R.menu.side_menu, sideActionMenuView.menu)

        sideActionMenuView.setOnMenuItemClickListener {

            drawerLayout = findViewById(R.id.drawerLayout)
            drawerLayout.openDrawer(GravityCompat.START)

            ;true
        }



        ////////////////////////SideBar
        drawerLayout = findViewById(R.id.drawerLayout)

        // Pass the ActionBarToggle action into the drawerListener
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarToggle)

        // Display the hamburger icon to launch the drawer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Call syncState() on the action bar so it'll automatically change to the back button when the drawer layout is open
        actionBarToggle.syncState()


        // Call findViewById on the NavigationView
        navView = findViewById(R.id.navView)
        navView.bringToFront();

        // Call setNavigationItemSelectedListener on the NavigationView to detect when items are clicked
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.iliski -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    Toast.makeText(this, "İlişki Testleri", Toast.LENGTH_SHORT).show()
                    val bundle = bundleOf("type" to "relationship")
                    binding.fragmentContainerView.findNavController()
                        .navigate(R.id.action_global_categoryTestFragment,bundle)
                    true
                }
                R.id.kisilik -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    Toast.makeText(this, "Kişilik Testleri", Toast.LENGTH_SHORT).show()

                    val bundle = bundleOf("type" to "personality")
                    binding.fragmentContainerView.findNavController()
                        .navigate(R.id.action_global_categoryTestFragment,bundle)
                    true
                }
                R.id.psikoloji -> {
                    drawerLayout.closeDrawer(GravityCompat.START)

                    Toast.makeText(this, "Psikoloji Testleri", Toast.LENGTH_SHORT).show()
                    val bundle = bundleOf("type" to "psikoloji")

                    binding.fragmentContainerView.findNavController()
                        .navigate(R.id.action_global_categoryTestFragment,bundle)

                    true
                }
                R.id.astroloji -> {
                    drawerLayout.closeDrawer(GravityCompat.START)

                    Toast.makeText(this, "Astroloji Testleri", Toast.LENGTH_SHORT).show()
                    val bundle = bundleOf("type" to "astroloji")

                    binding.fragmentContainerView.findNavController()
                        .navigate(R.id.action_global_categoryTestFragment,bundle)

                    true
                }
                R.id.yemek -> {
                    drawerLayout.closeDrawer(GravityCompat.START)

                    Toast.makeText(this, "Yemek Testleri", Toast.LENGTH_SHORT).show()
                    val bundle = bundleOf("type" to "yemek")

                    binding.fragmentContainerView.findNavController()
                        .navigate(R.id.action_global_categoryTestFragment,bundle)

                    true
                }
                R.id.kultur -> {
                    drawerLayout.closeDrawer(GravityCompat.START)

                    Toast.makeText(this, "Genel Kültür Testleri", Toast.LENGTH_SHORT).show()
                    val bundle = bundleOf("type" to "kultur")

                    binding.fragmentContainerView.findNavController()
                        .navigate(R.id.action_global_categoryTestFragment,bundle)

                    true
                }
                else -> {
                    false
                }
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(navView)
        return true
    }

    // override the onBackPressed() function to close the Drawer when the back button is clicked
    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}