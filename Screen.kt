package com.example.musicappui

import androidx.annotation.DrawableRes

sealed class Screen(val title: String, val route: String) {

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
            dRoute = "subscribe",
            icon = R.drawable.outline_person_add_24
        )
    }
}

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount,
)
