package ir.ah.app.foodlover.data.remot.repositoeies.search

import ir.ah.app.foodlover.data.model.restaurant.*
import ir.ah.app.foodlover.data.remot.repositoeies.*
import javax.inject.*

class SearchRepositoryImpl @Inject constructor(private val searchRepositoryFake: SearchRepositoryFake) : SearchRepository {
    override suspend fun getSearch(searchQuery: String): List<Restaurant> {
        return searchRepositoryFake.getSearch(searchQuery)
    }
}