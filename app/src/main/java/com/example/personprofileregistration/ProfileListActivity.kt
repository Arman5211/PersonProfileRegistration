package com.example.personprofileregistration

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.personprofileregistration.adapter.ProfileAdapter
import com.example.personprofileregistration.model.UserProfile
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
            val intent = Intent(this@ProfileListActivity, ProfileDetailActivity::class.java)
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
    private fun showDeleteConfirmationDialog(userProfile: UserProfile) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Profile")
        builder.setMessage("Are you sure you want to delete this profile?")

        builder.setPositiveButton("Yes") { dialog, which ->
            profileViewModel.deleteUserProfile(userProfile)  // Delete the profile
            dialog.dismiss()
        }

        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()  // Cancel delete operation
        }
        
        val dialog = builder.create()
        dialog.show()

        // Comment out or remove the LoadingActivity part
        // val intent = Intent(this, LoadingActivity::class.java)
        // intent.putExtra("TARGET_ACTIVITY", "com.example.userprofileregistration.ProfileActivity")
        // startActivity(intent)
    }
}