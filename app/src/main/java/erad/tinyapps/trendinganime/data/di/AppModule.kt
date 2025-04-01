package erad.tinyapps.trendinganime.data.di

import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import erad.tinyapps.trendinganime.data.network.KitsuApi
import erad.tinyapps.trendinganime.data.repository.KitsuRepositoryImpl
import erad.tinyapps.trendinganime.domain.repository.KitsuRepository
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