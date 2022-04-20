package ru.tsu.project.main.profile

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
import ru.tsu.project.databinding.ActivityProfileBinding
import ru.tsu.project.main.popular.PopularActivity
import ru.tsu.project.main.rank.RankActivity

class ProfileActivity : AppCompatActivity(R.layout.activity_profile) {

    private val binding: ActivityProfileBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.bottomView.selectedItemId = R.id.profileItem
        binding.bottomView.setOnNavigationItemSelectedListener { it ->
            when(it.itemId) {
                R.id.mainItem -> {
                    startActivity(Intent(this, PopularActivity::class.java))
                    finish()
                }
                R.id.rankItem -> {
                    startActivity(Intent(this, RankActivity::class.java))
                    finish()
                }
            }
            true
        }

        /*GlobalScope.launch(Dispatchers.Main) {
            try {
                val
            } catch (e: HttpException) {
                DialogUtils.createDialog(this@ProfileActivity)
            }
        }*/
    }
}