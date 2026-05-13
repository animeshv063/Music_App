package com.example.musicappui

import androidx.annotation.DrawableRes

sealed class Screen(val title: String, val route: String) {

    sealed class BottomScreen(
        val bTitle : String, val bRoute: String, @DrawableRes val icon: Int
    ):Screen(bTitle, bRoute){
        object Home : BottomScreen("Home", "home", R.drawable.baseline_home_24))
        object Browse : BottomScreen("Browse", "browse", R.drawable.outline_apps_24)
        object Library : BottomScreen("Library", "library", R.drawable.outline_library_music_24)
    }

    sealed class DrawerScreen(
        val dTitle: String,
        val dRoute: String,
        @DrawableRes val icon: Int
    ) : Screen(dTitle, dRoute) {

        object Account : DrawerScreen(
            dTitle = "Account",
            dRoute = "account",
            icon = R.drawable.outline_library_music_24
        )

        object Subscription : DrawerScreen(
            dTitle = "Subscription",
            dRoute = "subscribe",
            icon = R.drawable.outline_account_box_24
        )

        object AddAccount : DrawerScreen(
            dTitle = "Add Account",
            dRoute = "add_account",
            icon = R.drawable.outline_person_add_24
        )
    }
}

val screensInBottom = listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.Browse,
    Screen.BottomScreen.Library,
)

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount,
)

