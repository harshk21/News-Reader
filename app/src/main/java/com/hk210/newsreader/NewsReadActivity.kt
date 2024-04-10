package com.hk210.newsreader

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.hk210.newsreader.databinding.NewsReadLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsReadActivity : AppCompatActivity() {

    private var _binding: NewsReadLayoutBinding? = null
    private val binding: NewsReadLayoutBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = NewsReadLayoutBinding.inflate(layoutInflater)
        binding.root.apply {
            setContentView(this)
        }
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
