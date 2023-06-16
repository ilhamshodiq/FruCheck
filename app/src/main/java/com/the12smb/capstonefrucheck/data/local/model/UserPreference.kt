package com.the12smb.capstonefrucheck.data.local.model

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference private constructor(private val dataStore: DataStore<Preferences>){


    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

//        private val NAME_KEY = stringPreferencesKey("name")
//        private val EMAIL_KEY = stringPreferencesKey("email")
//        private val TOKEN_KEY = stringPreferencesKey("token")
//        private val LOGIN_KEY = booleanPreferencesKey("state")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}
