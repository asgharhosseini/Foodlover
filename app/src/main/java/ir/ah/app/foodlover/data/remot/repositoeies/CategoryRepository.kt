package ir.ah.app.foodlover.data.remot.repositoeies

import ir.ah.app.foodlover.data.model.category.Category

interface CategoryRepository {
    suspend fun getAllCategory(): List<Category>
}