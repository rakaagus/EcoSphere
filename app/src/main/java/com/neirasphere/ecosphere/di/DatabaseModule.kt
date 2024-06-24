package com.neirasphere.ecosphere.di


import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.neirasphere.ecosphere.data.local.DataSource
import com.neirasphere.ecosphere.data.local.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideLearnDataSource() = DataSource

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideClassifyHistoryDao(db: AppDatabase) = db.classifyHistoryDao

    @Provides
    @Singleton
    fun provideEduHistoryDao(db: AppDatabase) = db.eduHistoryDao

}