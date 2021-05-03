package ir.ah.app.foodlover.di

import android.content.*
import android.content.Context.*
import androidx.room.*
import com.bumptech.glide.*
import com.bumptech.glide.load.engine.*
import com.bumptech.glide.load.resource.bitmap.*
import com.bumptech.glide.request.*
import dagger.*
import dagger.hilt.*
import dagger.hilt.android.qualifiers.*
import dagger.hilt.components.*
import ir.ah.app.foodlover.R
import ir.ah.app.foodlover.data.loacal.*
import ir.ah.app.foodlover.other.*
import ir.ah.app.foodlover.other.Constance.SHARED_PREFERENCES_NAME
import ir.ah.app.foodlover.ui.adapter.*
import javax.inject.*

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


    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context): SharedPreferences =
            app.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideUserInfo(sharedPreferences: SharedPreferences) = UserInfoManager(sharedPreferences)


}