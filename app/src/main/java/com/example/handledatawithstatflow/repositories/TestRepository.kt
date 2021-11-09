package com.example.handledatawithstatflow.repositories

import com.example.handledatawithstatflow.helpers.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TestRepository(private val text: String) {




    suspend fun getText()= withContext(Dispatchers.IO){
        Resource.Success(text)
    }









}