package com.example.hamza_amir_midterm_labexam

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewProducts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val products = listOf(
            Product(
                title = "Fresh Red Apples",
                description = "Crisp and juicy red apples rich in fiber.",
                price = 2.50,
                imageResId = R.drawable.ic_apple
            ),
            Product(
                title = "Organic Whole Milk",
                description = "1 Liter fresh pasteurized whole milk.",
                price = 1.80,
                imageResId = R.drawable.ic_milk
            ),
            Product(
                title = "Whole Wheat Bread",
                description = "Freshly baked 100% whole grain loaf.",
                price = 2.20,
                imageResId = R.drawable.ic_bread
            ),
            Product(
                title = "Farm Fresh Eggs",
                description = "Pack of 12 organic grade-A farm eggs.",
                price = 3.50,
                imageResId = R.drawable.ic_eggs
            ),
            Product(
                title = "Extra Virgin Olive Oil",
                description = "500ml cold pressed extra virgin olive oil.",
                price = 8.99,
                imageResId = R.drawable.ic_oil
            ),
            Product(
                title = "Organic Yellow Bananas",
                description = "Fresh organic yellow bananas bundle.",
                price = 1.20,
                imageResId = R.drawable.ic_banana
            )
        )

        val adapter = ProductAdapter(products) { selectedProduct ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("EXTRA_PRODUCT", selectedProduct)
            }
            startActivity(intent)
        }

        recyclerView.adapter = adapter
    }
}
