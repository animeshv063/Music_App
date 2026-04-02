package com.example.musicappui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainView() {

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()
    val controller = rememberNavController()
    val viewModel : MainViewModel = viewModel()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val currentScreen = remember {
        viewModel.currentScreen.value
    }
    val title = remember {
        mutableStateOf(currentScreen.title)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = title.value) },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                }
            )
        },
        drawerContent = {
            LazyColumn(
                modifier = Modifier.padding(16.dp)
            ) {
                items(screensInDrawer) { item ->
                    DrawerItem(
                        selected = currentRoute == item.dRoute, 
                        item = item
                    ) {
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                        if (item.dRoute == "add_account") {
                            // Handle Add Account
                        } else {
                            controller.navigate(item.dRoute)
                            title.value = item.dTitle
                        }
                    }
                }
            }
        }
    ) {
        Navigation(controller, viewModel, it)
    }
}

@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked: () -> Unit
) {
    val background = if (selected) Color.LightGray else Color.Transparent

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(background)
            .clickable { onDrawerItemClicked() }
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.dTitle,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = item.dTitle,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
fun Navigation(navController: NavHostController, viewModel: MainViewModel, pd : PaddingValues) {
    NavHost(
        navController as NavHostController, startDestination = Screen.DrawerScreen.AddAccount.route,
        modifier = Modifier.padding(pd)
    ) {
        composable(Screen.DrawerScreen.AddAccount.route) {

        }
        composable(Screen.DrawerScreen.Account.route) {
        }
    }
}
