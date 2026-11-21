package com.example.hamza_amir_midterm_labexam

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    private var currentProduct: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar = findViewById<MaterialToolbar>(R.id.detailToolbar)
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        currentProduct = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("EXTRA_PRODUCT", Product::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("EXTRA_PRODUCT") as? Product
        }

        val imgProduct = findViewById<ImageView>(R.id.imgDetailProduct)
        val tvTitle = findViewById<TextView>(R.id.tvDetailTitle)
        val tvDescription = findViewById<TextView>(R.id.tvDetailDescription)
        val tvUnitPrice = findViewById<TextView>(R.id.tvUnitPrice)

        currentProduct?.let { product ->
            imgProduct.setImageResource(product.imageResId)
            tvTitle.text = product.title
            tvDescription.text = product.description
            tvUnitPrice.text = String.format(Locale.US, "Unit Price: $%.2f", product.price)
        }

        val etQuantity = findViewById<EditText>(R.id.etQuantity)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val layoutError = findViewById<LinearLayout>(R.id.layoutError)
        val tvErrorMessage = findViewById<TextView>(R.id.tvErrorMessage)
        val tvTotalPrice = findViewById<TextView>(R.id.tvTotalPrice)

        btnCalculate.setOnClickListener {
            val product = currentProduct ?: return@setOnClickListener
            val quantityString = etQuantity.text.toString().trim()

            if (quantityString.isEmpty()) {
                layoutError.visibility = View.VISIBLE
                tvErrorMessage.text = getString(R.string.error_negative_quantity)
                tvTotalPrice.visibility = View.GONE
                return@setOnClickListener
            }

            val quantity = quantityString.toDoubleOrNull()

            if (quantity == null || quantity < 0) {
                // Display error message on user's screen in red color with suitable icon
                layoutError.visibility = View.VISIBLE
                tvErrorMessage.text = getString(R.string.error_negative_quantity)
                tvTotalPrice.visibility = View.GONE
            } else {
                layoutError.visibility = View.GONE
                val totalPrice = product.price * quantity
                tvTotalPrice.text = String.format(Locale.US, "Total Price: $%.2f", totalPrice)
                tvTotalPrice.visibility = View.VISIBLE
            }
        }
    }
}
