package com.capstone.contact.nav

sealed class Routes(val route: String)
{
    object Main : Routes("main")
    object ToDetail : Routes("main/{ContactId}") {
        fun createRoute(id: Int) = "main/$id" }
    object Fav : Routes("favorite")

    object About : Routes("profile")



}