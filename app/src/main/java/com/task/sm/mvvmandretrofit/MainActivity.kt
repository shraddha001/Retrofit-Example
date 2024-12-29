package com.task.sm.mvvmandretrofit

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.google.android.material.textview.MaterialTextView
import com.task.sm.mvvmandretrofit.apiservice.Resource
import com.task.sm.mvvmandretrofit.repository.MyRepository
import com.task.sm.mvvmandretrofit.retrofit.RetrofitClient
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private val viewModel: MyViewModel by viewModels {
        MyViewModelFactory(MyRepository(RetrofitClient.apiService))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<Button>(R.id.mbtn).setOnClickListener {
            viewModel.getCharges()
        }

        viewModel.getChargesState().observe(this, Observer { resource ->
            when (resource) {
                is Resource.Loading -> {
                    findViewById<ProgressBar>(R.id.progress).isVisible = true
                    findViewById<Button>(R.id.mbtn).isVisible = false
                }

                is Resource.Success -> {
                    findViewById<ProgressBar>(R.id.progress).isVisible = false
                    findViewById<Button>(R.id.mbtn).isVisible = false
                    findViewById<MaterialTextView>(R.id.responseTv).apply {
                        visibility = View.VISIBLE
                        text = resource.data.toString()
                    }
                }

                is Resource.Error -> {
                    Toast.makeText(this, "${resource.message}", Toast.LENGTH_LONG).show()
                }
            }
        })
    }


}