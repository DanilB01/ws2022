package ru.tsu.project.main.popular

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.tsu.project.R
import ru.tsu.project.databinding.ItemGameBinding
import ru.tsu.project.network.games.GameDetails

class AllGamesAdapter(val listener: GameListener): RecyclerView.Adapter<AllGamesAdapter.PopularVH>() {

    var items: List<GameDetails> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class PopularVH(view: View): RecyclerView.ViewHolder(view) {

        private val binding = ItemGameBinding.bind(view)

        fun bind(item: GameDetails) {
            with(binding) {
                Glide.with(root).load(item.previewUrl).into(cardImage)
                gameText.text = item.title

                root.setOnClickListener {
                    listener.onClick()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return PopularVH(view)
    }

    override fun onBindViewHolder(holder: PopularVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    interface GameListener {
        fun onClick()
    }
}