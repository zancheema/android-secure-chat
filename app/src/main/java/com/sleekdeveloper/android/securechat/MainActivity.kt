package com.sleekdeveloper.android.securechat

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.sleekdeveloper.android.securechat.MainViewModel.AuthenticationState.AUTHENTICATED
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment)
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)

        setUpAuthConditionalNavigation(graph, navHostFragment)
    }

    private fun setUpAuthConditionalNavigation(
        graph: NavGraph,
        navHostFragment: NavHostFragment
    ) {
        viewModel.authenticationState.observe(this, EventObserver { state ->
            graph.startDestination =
                if (state == AUTHENTICATED) R.id.chatsFragment else R.id.authFragment
            navHostFragment.navController.graph = graph
        })
    }
}