package com.example.handledatawithstatflow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.handledatawithstatflow.helpers.Event
import com.example.handledatawithstatflow.helpers.Resource
import com.example.handledatawithstatflow.repositories.TestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel() {

    private val repository=TestRepository("Work Done")

    // TODO: 11/8/2021 Simple way to show how to handle repo with view model by using StateFlow
    private val _textStatus= MutableStateFlow<Event<Resource<String>>>(Event(Resource.Loading()))
    val textStatus: MutableStateFlow<Event<Resource<String>>> =_textStatus

     fun  getText() {

       // _textStatus.emit(Event(Resource.Loading()))

        viewModelScope.launch(Dispatchers.Main) {
            val result=repository.getText()
            _textStatus.emit(Event(result))
        }

    }

}