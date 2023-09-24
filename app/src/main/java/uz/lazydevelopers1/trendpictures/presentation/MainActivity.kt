package uz.lazydevelopers1.trendpictures.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import uz.lazydevelopers1.domain.models.UnsplashPhoto
import uz.lazydevelopers1.trendpictures.App
import uz.lazydevelopers1.trendpictures.R
import uz.lazydevelopers1.trendpictures.databinding.ActivityMainBinding
import uz.lazydevelopers1.trendpictures.databinding.PhotoItemBinding
import uz.lazydevelopers1.trendpictures.utils.NetworkHelper
import uz.lazydevelopers1.trendpictures.vm.UnsplashPhotoResource
import uz.lazydevelopers1.trendpictures.vm.UnsplashPhotoViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

//    forTest

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: UnsplashPhotoViewModel by viewModels { factory }

    private lateinit var activityMainBinding: ActivityMainBinding

    private lateinit var photoAdapter: PhotoAdapter

    private lateinit var images: ArrayList<UnsplashPhoto>

    lateinit var networkHelper: NetworkHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        b()

        networkHelper = NetworkHelper(this)

        if (networkHelper.isNetworkConnected()) {
            getImages()
        } else {
            Toast.makeText(this, "Network disconnected", Toast.LENGTH_SHORT).show()
        }

    }

    fun c() {
        window.navigationBarColor = ContextCompat.getColor(this, R.color.white)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
    }

    fun a() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
        )
    }

    fun b() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun getImages() {
        images = ArrayList()
        photoAdapter = PhotoAdapter(images)
        activityMainBinding.rv.adapter = photoAdapter
        lifecycleScope.launch {
            viewModel.getUnsplashPhotos(
                "YazqkbNkpIZKOlEqIHZwhux4w_GFbxVw1NVyhzIl_3Y",
                1,
                30,
                "popular"
            )
                .collect {
                    images.clear()
                    when (it) {
                        is UnsplashPhotoResource.Error -> {
                            activityMainBinding.rv.visibility = View.INVISIBLE
                            activityMainBinding.progressBar.visibility = View.INVISIBLE
                            activityMainBinding.errorMessage.visibility = View.VISIBLE
                            photoAdapter.notifyDataSetChanged()
                        }
                        is UnsplashPhotoResource.Success -> {
                            it.list?.forEach { photo ->
                                images.add(photo)
                            }
                            activityMainBinding.rv.visibility = View.VISIBLE
                            activityMainBinding.progressBar.visibility = View.INVISIBLE
                            activityMainBinding.errorMessage.visibility = View.INVISIBLE
                            photoAdapter.notifyDataSetChanged()
                        }
                        is UnsplashPhotoResource.Loading -> {
                            activityMainBinding.rv.visibility = View.INVISIBLE
                            activityMainBinding.progressBar.visibility = View.VISIBLE
                            activityMainBinding.errorMessage.visibility = View.INVISIBLE
                            photoAdapter.notifyDataSetChanged()
                        }
                    }
                }
        }
        Toast.makeText(this, "Ishladi", Toast.LENGTH_SHORT).show()
    }
}