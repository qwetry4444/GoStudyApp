package com.example.gostudyapp.core.data.di

import com.example.gostudyapp.core.data.database.repositories.GroupRepositoryImpl
import com.example.gostudyapp.core.data.database.repositories.RoomRepositoryImpl
import com.example.gostudyapp.core.data.database.repositories.ScheduleRepositoryImpl
import com.example.gostudyapp.core.data.database.repositories.ScheduleSubgroupRepositoryImpl
import com.example.gostudyapp.core.data.database.repositories.ScheduleTeacherRepositoryImpl
import com.example.gostudyapp.core.data.database.repositories.SubjectRepositoryImpl
import com.example.gostudyapp.core.data.database.repositories.TeacherRepositoryImpl
import com.example.gostudyapp.core.domain.IRepository.GroupRepository
import com.example.gostudyapp.core.domain.IRepository.RoomRepository
import com.example.gostudyapp.core.domain.IRepository.ScheduleRepository
import com.example.gostudyapp.core.domain.IRepository.ScheduleSubgroupRepository
import com.example.gostudyapp.core.domain.IRepository.ScheduleTeacherRepository
import com.example.gostudyapp.core.domain.IRepository.SubjectRepository
import com.example.gostudyapp.core.domain.IRepository.TeacherRepository
import com.example.gostudyapp.core.domain.usecases.GroupUseCase.AddGroupUseCase
import com.example.gostudyapp.core.domain.usecases.GroupUseCase.GetAllGroupsUseCase
import com.example.gostudyapp.core.domain.usecases.GroupUseCase.GetGroupByIdUseCase
import com.example.gostudyapp.core.domain.usecases.RoomUseCase.AddRoomUseCase
import com.example.gostudyapp.core.domain.usecases.RoomUseCase.GetAllRoomsUseCase
import com.example.gostudyapp.core.domain.usecases.RoomUseCase.GetRoomByIdUseCase
import com.example.gostudyapp.core.domain.usecases.ScheduleSubgroupUseCase.AddScheduleSubgroupUseCase
import com.example.gostudyapp.core.domain.usecases.ScheduleTeacherUseCase.AddScheduleTeacherUseCase
import com.example.gostudyapp.core.domain.usecases.ScheduleUseCase.AddScheduleUseCase
import com.example.gostudyapp.core.domain.usecases.ScheduleUseCase.GetScheduleByIdUseCase
import com.example.gostudyapp.core.domain.usecases.SubjectUseCase.AddSubjectUseCase
import com.example.gostudyapp.core.domain.usecases.SubjectUseCase.GetSubjectByIdUseCase
import com.example.gostudyapp.core.domain.usecases.TeacherUseCase.AddTeacherUseCase
import com.example.gostudyapp.core.domain.usecases.TeacherUseCase.GetAllTeachersUseCase
import com.example.gostudyapp.core.domain.usecases.TeacherUseCase.GetTeacherByIdUseCase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CloudFirestoreModule {
    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideRoomRepository(firestore: FirebaseFirestore): RoomRepository =
        RoomRepositoryImpl(firestore)
    @Provides
    @Singleton
    fun provideGroupRepository(firestore: FirebaseFirestore): GroupRepository =
        GroupRepositoryImpl(firestore)
    @Provides
    @Singleton
    fun provideTeacherRepository(firestore: FirebaseFirestore): TeacherRepository =
        TeacherRepositoryImpl(firestore)
    @Provides
    @Singleton
    fun provideSubjectRepository(firestore: FirebaseFirestore): SubjectRepository =
        SubjectRepositoryImpl(firestore)
    @Provides
    @Singleton
    fun provideScheduleRepository(firestore: FirebaseFirestore): ScheduleRepository =
        ScheduleRepositoryImpl(firestore)
    @Provides
    @Singleton
    fun provideScheduleSubgroupRepository(firestore: FirebaseFirestore): ScheduleSubgroupRepository =
        ScheduleSubgroupRepositoryImpl(firestore)
    @Provides
    @Singleton
    fun provideScheduleTeacherRepository(firestore: FirebaseFirestore): ScheduleTeacherRepository =
        ScheduleTeacherRepositoryImpl(firestore)

    @Provides
    @Singleton
    fun provideGetRoomByIdUseCase(repository: RoomRepository) = GetRoomByIdUseCase(repository)
    @Provides
    @Singleton
    fun provideGetAllRoomsUseCase(repository: RoomRepository) = GetAllRoomsUseCase(repository)
    @Provides
    @Singleton
    fun provideAddRoomUseCase(repository: RoomRepository) = AddRoomUseCase(repository)

    @Provides
    @Singleton
    fun provideAddGroupUseCase(repository: GroupRepository) = AddGroupUseCase(repository)
    @Provides
    @Singleton
    fun provideGetAllGroupsUseCase(repository: GroupRepository) = GetAllGroupsUseCase(repository)
    @Provides
    @Singleton
    fun provideGetGroupByIdUseCase(repository: GroupRepository) = GetGroupByIdUseCase(repository)

    @Provides
    @Singleton
    fun provideAddTeacherUseCase(repository: TeacherRepository) = AddTeacherUseCase(repository)
    @Provides
    @Singleton
    fun provideGetAllTeachersUseCase(repository: TeacherRepository) = GetAllTeachersUseCase(repository)
    @Provides
    @Singleton
    fun provideGetTeacherByIdUseCase(repository: TeacherRepository) = GetTeacherByIdUseCase(repository)

    @Provides
    @Singleton
    fun provideAddSubjectUseCase(repository: SubjectRepository) = AddSubjectUseCase(repository)
    @Provides
    @Singleton
    fun provideGetSubjectByIdUseCase(repository: SubjectRepository) = GetSubjectByIdUseCase(repository)

    @Provides
    @Singleton
    fun provideAddScheduleUseCase(repository: ScheduleRepository) = AddScheduleUseCase(repository)
    @Provides
    @Singleton
    fun provideGetScheduleByIdUseCase(repository: ScheduleRepository) = GetScheduleByIdUseCase(repository)

    @Provides
    @Singleton
    fun provideAddScheduleSubgroupUseCase(repository: ScheduleSubgroupRepository) = AddScheduleSubgroupUseCase(repository)

    @Provides
    @Singleton
    fun provideAddScheduleTeacherUseCase(repository: ScheduleTeacherRepository) = AddScheduleTeacherUseCase(repository)


}