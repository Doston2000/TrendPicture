package uz.lazydevelopers1.trendpictures.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.lazydevelopers1.domain.models.UnsplashPhoto
import uz.lazydevelopers1.trendpictures.databinding.PhotoItemBinding

class PhotoAdapter(var images: ArrayList<UnsplashPhoto>) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(var photoItemBinding: PhotoItemBinding) :
        RecyclerView.ViewHolder(photoItemBinding.root) {
        fun onBind(unsplashPhoto: UnsplashPhoto) {
            Picasso.get().load(unsplashPhoto.urls.small).into(photoItemBinding.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            PhotoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.onBind(images[position])
    }

    override fun getItemCount(): Int = images.size

}