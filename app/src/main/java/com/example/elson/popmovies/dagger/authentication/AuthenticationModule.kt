package com.example.elson.popmovies.dagger.authentication

import com.example.elson.popmovies.data.AuthenticationRepository
import com.example.elson.popmovies.network.Authentication
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthenticationModule {

    @AuthenticationScope
    @Provides
    fun provideAuthenticationRequest(retrofit: Retrofit): Authentication {
        return retrofit.create(Authentication::class.java)
    }

    @AuthenticationScope
    @Provides
    fun provideAuthenticationRepository(authentication: Authentication): AuthenticationRepository {
        return AuthenticationRepository(authentication)
    }
}