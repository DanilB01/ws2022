package ru.tsu.project.signin

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
import ru.tsu.project.databinding.ActivityMainBinding
import ru.tsu.project.main.PopularActivity
import ru.tsu.project.network.ApiRepo
import ru.tsu.project.network.Network
import ru.tsu.project.network.login.LoginForm

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()
    private val api by lazy { ApiRepo(Network.retrofit) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            loginButton.setOnClickListener {
                val name = nameEditText.text.toString()
                if(name.isBlank()) {
                    DialogUtils.createDialog(this@MainActivity)
                } else {
                    GlobalScope.launch(Dispatchers.Main) {
                        try {
                            Network.token = api.login(LoginForm(name))
                            startActivity(Intent(this@MainActivity, PopularActivity::class.java))
                            finish()
                        } catch (e: HttpException) {
                            DialogUtils.createDialog(this@MainActivity)
                        }
                    }
                }
            }
        }
    }
}