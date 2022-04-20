package ru.tsu.project.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.tsu.project.R
import ru.tsu.project.common.DialogUtils
import ru.tsu.project.databinding.ActivityPopularBinding
import ru.tsu.project.network.ApiRepo
import ru.tsu.project.network.Network

class PopularActivity : AppCompatActivity(R.layout.activity_popular) {

    private val binding: ActivityPopularBinding by viewBinding()
    private val api by lazy { ApiRepo(Network.retrofit) }
    private val popularAdapter by lazy { PopularAdapter() }
    private val allGamesAdapter by lazy { AllGamesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.horizontalRecycler.adapter = popularAdapter
        binding.allGamesRecycler.adapter = allGamesAdapter
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val items = api.getGames()
                popularAdapter.items = items
                allGamesAdapter.items = items
            } catch (e: HttpException) {
                DialogUtils.createDialog(this@PopularActivity)
            }
        }
    }
}