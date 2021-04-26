package ir.ah.app.foodlover.data.remot.repositoeies

import ir.ah.app.foodlover.data.model.restaurant.*

interface SearchRepository {
    suspend fun getSearch(searchQuery: String): List<Restaurant>
}