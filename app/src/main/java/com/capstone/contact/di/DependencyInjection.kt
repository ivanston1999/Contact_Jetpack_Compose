package com.capstone.contact.di

import com.capstone.contact.access.Repo

object DependencyInjection {
    fun useRepo(): Repo = Repo.useRepo()
}
