package com.the12smb.capstonefrucheck.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.the12smb.capstonefrucheck.data.local.model.UserPreference
import com.the12smb.capstonefrucheck.view.detail.DetailViewModel
import com.the12smb.capstonefrucheck.view.upload.UploadViewModel

class ViewModelFactory(private val pref: UserPreference) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
//            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
//                LoginViewModel(pref) as T
//            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel() as T
            }
            modelClass.isAssignableFrom(UploadViewModel::class.java) -> {
                UploadViewModel(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}