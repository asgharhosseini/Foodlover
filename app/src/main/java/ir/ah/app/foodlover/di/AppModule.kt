package ir.ah.app.foodlover.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.data.loacal.OrderDao
import ir.ah.app.foodlover.data.loacal.OrderDatabase
import ir.ah.app.foodlover.ui.adapter.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePopularAdapter(glide: RequestManager) = PopularAdapter(glide)

    @Singleton
    @Provides
    fun provideRecommendedAdapter(glide: RequestManager) = RecommendedAdapter(glide)

    @Singleton
    @Provides
    fun provideSearchAdapter(glide: RequestManager) = SearchAdapter(glide)

    @Singleton
    @Provides
    fun provideAllRestaurantAdapter(glide: RequestManager) = AllRestaurantAdapter(glide)

    @Singleton
    @Provides
    fun provideBannerAdapter(glide: RequestManager) = BannerAdapter(glide)

    @Singleton
    @Provides
    fun provideAppetizerAdapter(glide: RequestManager) = AppetizerAdapter(glide)

    @Singleton
    @Provides
    fun provideMainCourseAdapter(glide: RequestManager) = MainCourseAdapter(glide)

    @Singleton
    @Provides
    fun provideDessertAdapter(glide: RequestManager) = DessertAdapter(glide)

    @Singleton
    @Provides
    fun provideBeveragesAdapter(glide: RequestManager) = BeveragesAdapter(glide)

    @Singleton

    @Provides
    fun provideOrderAdapter(glide: RequestManager) = OrderAdapter(glide)

    @Singleton
    @Provides
    fun provideCategoryAdapter() = CategoryAdapter()


    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions().placeholder(R.drawable.logobase)
            .error(R.drawable.logobase)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(16)))
            .diskCacheStrategy(DiskCacheStrategy.DATA)
    )

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): OrderDatabase {
        return Room.databaseBuilder(
            appContext,
            OrderDatabase::class.java,
            "order_db"
        ).allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideOrderDao(orderDatabase: OrderDatabase): OrderDao {
        return orderDatabase.orderDao()
    }


}