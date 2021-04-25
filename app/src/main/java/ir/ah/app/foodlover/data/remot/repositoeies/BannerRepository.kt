package ir.ah.app.foodlover.data.remot.repositoeies

import ir.ah.app.foodlover.data.model.banner.Banner

interface BannerRepository {
    suspend fun getAllBanner(): List<Banner>
}