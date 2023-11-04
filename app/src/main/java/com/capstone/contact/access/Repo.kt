package com.capstone.contact.access
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import com.capstone.contact.access.model.ContactDummy
import com.capstone.contact.access.model.Contacts



class Repo {
    private val contact = mutableListOf<Contacts>()

    init { if (contact.isEmpty()) { for (randomContact in ContactDummy.RandomContact) {
                contact.add(randomContact) } } }


    fun findContact(query: String): Flow<List<Contacts>> = flow {
        val data = contact.filter { it.fullName.contains(query, ignoreCase = true) }
        emit(data)
    }

    fun uniqueId(ContactId: Int): Contacts = contact.first { it.id == ContactId }


    fun finalContact(ContactId: Int, newState: Boolean): Flow<Boolean> {
        val idx = contact.indexOfFirst { it.id == ContactId }
        val final = if (idx >= 0) {
            val contacts = contact[idx]
            contact[idx] = contacts.copy(fav = newState)
            true
        } else {
            false
        }
        return flowOf(final)
    }


    fun favCon(): Flow<List<Contacts>> = flowOf(contact.filter { it.fav })

    companion object {
        @Volatile
        private var instance: Repo? = null

        fun useRepo(): Repo =
            instance ?: synchronized(this) {
                Repo().apply {
                    instance = this
                }
            }
    }
}

