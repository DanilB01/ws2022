package ru.tsu.project.main.popular

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.tsu.project.R
import ru.tsu.project.common.DialogUtils
import ru.tsu.project.databinding.ActivityPopularBinding
import ru.tsu.project.game.GameActivity
import ru.tsu.project.main.profile.ProfileActivity
import ru.tsu.project.main.rank.RankActivity
import ru.tsu.project.network.ApiRepo
import ru.tsu.project.network.Network

class PopularActivity : AppCompatActivity(R.layout.activity_popular) {

    private val binding: ActivityPopularBinding by viewBinding()
    private val api by lazy { ApiRepo(Network.retrofit) }
    private val popularAdapter by lazy { PopularAdapter() }
    private val allGamesAdapter by lazy { AllGamesAdapter(object : AllGamesAdapter.GameListener {
        override fun onClick() {
            startActivity(Intent(this@PopularActivity, GameActivity::class.java))
        }

    }) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.bottomView.setOnNavigationItemSelectedListener { it ->
            when(it.itemId) {
                R.id.rankItem -> {
                    startActivity(Intent(this, RankActivity::class.java))
                    finish()
                }
                R.id.profileItem -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                }
            }
            true
        }

        binding.horizontalRecycler.adapter = popularAdapter
        binding.allGamesRecycler.adapter = allGamesAdapter
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val items = api.getGames()
                Network.gameId = items.first().id
                popularAdapter.items = items
                allGamesAdapter.items = items
            } catch (e: HttpException) {
                DialogUtils.createDialog(this@PopularActivity)
            }
        }
    }
}