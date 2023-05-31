package pl.denys.karol.passlock.di

import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.denys.karol.passlock.repository.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        database: FirebaseFirestore,
        auth: FirebaseAuth,
        appPreferences: SharedPreferences,
        gson: Gson
    ):AuthRepository {
        return AuthRepositoryImplementation(auth, database, appPreferences, gson)
    }

    @Provides
    @Singleton
    fun provideFirebaseRepository(
        database: FirebaseFirestore,
        storageReference: StorageReference
    ): FirebaseRepository {
        return FirebaseRepositoryImplementation(database, storageReference)
    }

    @Provides
    @Singleton
    fun provideCryptRepository(
    ): CryptRepository {
        return CryptRepositoryImplementation()
    }

}
