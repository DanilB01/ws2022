package ru.tsu.project.main.rank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.tsu.project.R
import ru.tsu.project.common.DialogUtils
import ru.tsu.project.databinding.ActivityRankBinding
import ru.tsu.project.main.popular.PopularActivity
import ru.tsu.project.main.popular.PopularAdapter
import ru.tsu.project.network.ApiRepo
import ru.tsu.project.network.Network

class RankActivity : AppCompatActivity(R.layout.activity_rank) {

    private val binding: ActivityRankBinding by viewBinding()
    private val api by lazy { ApiRepo(Network.retrofit) }
    private val rankAdapter by lazy { RankAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.bottomView.selectedItemId = R.id.rankItem
        binding.bottomView.setOnNavigationItemSelectedListener { it ->
            when(it.itemId) {
                R.id.mainItem -> {
                    startActivity(Intent(this, PopularActivity::class.java))
                    finish()
                }
            }
            true
        }

        binding.rankRecycler.adapter = rankAdapter
        GlobalScope.launch(Dispatchers.Main) {
            try {
                rankAdapter.items = api.getRank()
            } catch (e: HttpException) {
                DialogUtils.createDialog(this@RankActivity)
            }
        }
    }
}