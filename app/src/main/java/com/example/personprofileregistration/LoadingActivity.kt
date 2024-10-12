package com.example.personprofileregistration

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.personprofileregistration.model.UserProfile

class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        Handler(Looper.getMainLooper()).postDelayed({

            val tergetActivityName = intent.getStringExtra("TARGET_ACTIVITY")
            val userProfile = intent.getSerializableExtra("USER_PROFILE") as UserProfile?

            val targetIntent = Intent()
            targetIntent.setClassName(this,tergetActivityName ?: return@postDelayed )
            userProfile?.let {
                targetIntent.putExtra("USER_PROFILE",it)
            }
            startActivity(targetIntent)
            finish()
        },3000)
    }
}
