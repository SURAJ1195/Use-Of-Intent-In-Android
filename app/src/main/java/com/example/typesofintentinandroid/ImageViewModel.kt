package com.example.typesofintentinandroid

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ImageViewModel:ViewModel(){
    var uri: Uri? by mutableStateOf(null)
        private set
    fun updateUri(uri:Uri?){
        Log.d("suraj", "updateUri is called")
        this.uri = uri
    }
}