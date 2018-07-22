package io.github.louistsaitszho.erg2

interface Repository {
    fun getAllSessions(): List<Session>
}

class RepositoryImpl : Repository {
    override fun getAllSessions(): List<Session> = emptyList()
}