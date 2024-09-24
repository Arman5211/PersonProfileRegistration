package com.example.personprofileregistration.repository

import androidx.lifecycle.LiveData
import com.example.personprofileregistration.dao.UserProfileDao
import com.example.personprofileregistration.model.UserProfile

class UserProfileRepository (private val userProfileDao: UserProfileDao) {

    fun getUserProfiles(): LiveData<List<UserProfile>> {
        return userProfileDao.getAllUserProfiles()
    }

    suspend fun insert(userProfile: UserProfile) {
        userProfileDao.insert(userProfile)
    }

    suspend fun update(userProfile: UserProfile) {
        userProfileDao.update(userProfile)
    }

    suspend fun delete(userProfile: UserProfile) {
        userProfileDao.delete(userProfile)
    }

}