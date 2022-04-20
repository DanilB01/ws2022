package ru.tsu.project.main.rank

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.tsu.project.R
import ru.tsu.project.databinding.ItemGameBinding
import ru.tsu.project.databinding.ItemRankBinding
import ru.tsu.project.network.games.GameDetails
import ru.tsu.project.network.games.GameResult

class RankAdapter: RecyclerView.Adapter<RankAdapter.PopularVH>() {

    var items: List<GameResult> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class PopularVH(view: View): RecyclerView.ViewHolder(view) {

        private val binding = ItemRankBinding.bind(view)

        fun bind(item: GameResult, pos: Int) {
            with(binding) {
                nameText.text = item.userNickname
                scoreText.text = item.points.toString()
                rankText.text = (pos + 1).toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rank, parent, false)
        return PopularVH(view)
    }

    override fun onBindViewHolder(holder: PopularVH, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount() = items.size
}