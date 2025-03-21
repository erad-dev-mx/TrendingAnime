package dev.erad.tinyapps.data.di

import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.erad.tinyapps.data.network.KitsuApi
import dev.erad.tinyapps.data.repository.KitsuRepositoryImpl
import dev.erad.tinyapps.domain.repository.KitsuRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder().add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun providesKitsuApi(moshi: Moshi): KitsuApi =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .baseUrl(KitsuApi.BASE_URL)
            .build()
            .create(KitsuApi::class.java)

    @Provides
    @Singleton
    fun provideKitsuRepository(api: KitsuApi): KitsuRepository =
        KitsuRepositoryImpl(api)
}