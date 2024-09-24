package com.example.personprofileregistration

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.personprofileregistration.adapter.ProfileAdapter
import com.example.personprofileregistration.viewmodel.UserProfileViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton




class ProfileListActivity : AppCompatActivity() {

    private lateinit var profileViewModel: UserProfileViewModel
    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_list)

        profileViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.profileRecyclerView)
        profileAdapter = ProfileAdapter()

        recyclerView.adapter = profileAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        profileViewModel.getUserProfiles().observe(this, Observer {
            profileAdapter.submitList(it)
        })

        profileAdapter.setOnItemClickListener {
            val intent = Intent(this@ProfileListActivity, SingleProfileActivity::class.java)
            intent.putExtra("USER_PROFILE", it)
            startActivity(intent)
        }

        profileAdapter.setOnDeleteClickListener {
            profileViewModel.deleteUserProfile(it)
        }

        profileAdapter.setOnUpdateClickListener {
            val intent = Intent(this@ProfileListActivity, UpdateProfileActivity::class.java)
            intent.putExtra("USER_PROFILE", it)
            startActivity(intent)
        }

        findViewById<FloatingActionButton>(R.id.addProfileBtn).setOnClickListener {
            val intent = Intent(this@ProfileListActivity, AddProfileActivity::class.java)
            startActivity(intent)
        }
    }
}