package com.example.expensetracker.data.auth

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.expensetracker.domain.auth.AuthDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthDataStoreImpl @Inject constructor(@ApplicationContext private val context: Context) :
    AuthDataStore {

    companion object {
        private const val TOKEN = "token"
        private const val AUTH_DATA_STORE = "AUTH_DATA_STORE"
        private const val EMPTY_STRING = ""

        private val Context.dataStore by preferencesDataStore(name = AUTH_DATA_STORE)
    }

    override suspend fun saveToken(token: String) {
        val dataStoreKey = stringPreferencesKey(TOKEN)
        context.dataStore.edit { it[dataStoreKey] = token }
    }

    override suspend fun hasToken(): Boolean {
        return getToken().isNotEmpty()
    }

    override suspend fun getToken(): String {
        val dataStoreKey = stringPreferencesKey(TOKEN)
        val preference = context.dataStore.data.first()
        return preference[dataStoreKey] ?: EMPTY_STRING
    }

    override suspend fun removeToken() {
        val dataStoreKey = stringPreferencesKey(TOKEN)
        context.dataStore.edit { token -> token[dataStoreKey] = EMPTY_STRING }
    }
}
