package com.example.gostudyapp.core.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {

//    @Provides
//    fun provideScheduleDao(database: UniversityDatabase): ScheduleDao {
//        return database.scheduleDao()
//    }
//
//    @Provides
//    fun provideSubgroupDao(database: UniversityDatabase): SubgroupDao {
//        return database.subgroupDao()
//    }
//
//    @Provides
//    fun provideTeacherDao(database: UniversityDatabase): TeacherDao {
//        return database.teacherDao()
//    }
//
//    @Provides
//    fun provideScheduleTeacherDao(database: UniversityDatabase): ScheduleTeacherDao {
//        return database.scheduleTeacherDao()
//    }
//
//    @Provides
//    fun provideScheduleSubgroupDao(database: UniversityDatabase): ScheduleSubgroupDao {
//        return database.scheduleSubgroupDao()
//    }
//
//    @Provides
//    fun provideRoomDao(database: UniversityDatabase): RoomDao {
//        return database.roomDao()
//    }
//
//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext appContext: Context): UniversityDatabase {
//        return Room.databaseBuilder(
//            appContext,
//            UniversityDatabase::class.java,
//            "university_db"
//        ).build()
//    }
}

