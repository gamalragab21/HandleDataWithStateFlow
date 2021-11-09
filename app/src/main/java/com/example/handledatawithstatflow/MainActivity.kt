package com.example.handledatawithstatflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.handledatawithstatflow.helpers.Event
import com.example.handledatawithstatflow.helpers.EventObserver
import com.example.handledatawithstatflow.helpers.Resource
import com.example.handledatawithstatflow.viewmodels.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {


    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        homeViewModel.getText()

        lifecycleScope.launchWhenStarted {


            homeViewModel.textStatus.collect(EventObserver(
                onSuccess = {
                    progress.isVisible = it.isEmpty()
                    textStatus.text = it
                },
                onError = {
                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                }, onLoading = {
                    progress.isVisible = true
                }
            ))


        }
//
//        homeViewModel.textStatus.observe(this,EventObserver(
//            onLoading = {
//                progress.isVisible=true
//            },
//            onError = {
//                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//            },
//            onSuccess = {
//                progress.isVisible=it.isEmpty()
//                textStatus.text=it
//            }
//        ))

    }
}