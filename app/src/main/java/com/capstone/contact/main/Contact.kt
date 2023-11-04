package com.capstone.contact.main


import com.capstone.contact.nav.NavItem
import com.capstone.contact.nav.Routes
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import com.capstone.contact.R
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.capstone.contact.ui.page.*
import androidx.navigation.compose.NavHost
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun Contact(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val thisRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (thisRoute != Routes.ToDetail.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Main.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.Main.route) {
                Main(
                    toDetail = { ContactId ->
                        navController.navigate(Routes.ToDetail.createRoute(ContactId))
                    }
                )
            }
            composable(Routes.Fav.route) {
                Favorite(
                    toDetail = { ContactId ->
                        navController.navigate(Routes.ToDetail.createRoute(ContactId))
                    }
                )
            }
            composable(Routes.About.route) {
                About()
            }
            composable(
                route = Routes.ToDetail.route,
                arguments = listOf(
                    navArgument("ContactId") { type = NavType.IntType }
                )
            ) {
                val id = it.arguments?.getInt("ContactId") ?: -1
                Detail(
                    id = id,
                    back = {
                        navController.navigateUp()
                    }
                )
            }

        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val thisRoute = navBackStackEntry?.destination?.route
        val navItem = listOf(
            NavItem(
                item = stringResource(R.string.mainMenu),
                symbol = Icons.Default.Home,
                routes = Routes.Main
            ),
            NavItem(
                item = stringResource(R.string.fav),
                symbol = Icons.Rounded.FavoriteBorder,
                routes = Routes.Fav
            ),
            NavItem(
                item = stringResource(R.string.about),
                symbol = Icons.Default.AccountCircle,
                routes = Routes.About
            ),
        )
        BottomNavigation {
            navItem.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.symbol,
                            contentDescription = item.item
                        )
                    },
                    label = { Text(item.item) },
                    selected = thisRoute == item.routes.route,
                    onClick = {
                        navController.navigate(item.routes.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}