package ir.ah.app.foodlover.di

import android.content.Context
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
import ir.ah.app.foodlover.ui.adapter.CategoryAdapter
import ir.ah.app.foodlover.ui.adapter.PopularAdapter
import ir.ah.app.foodlover.ui.adapter.RecommendedAdapter
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

}