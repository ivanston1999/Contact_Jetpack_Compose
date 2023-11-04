package com.capstone.contact.access.model


data class Contacts(
    val id: Int,
    val fullName: String,
    val hp: String,
    val address: String,
    val ig: String,
    val email: String,
    val desc: String,
    val img: Int,
    val fav: Boolean = false,
)
