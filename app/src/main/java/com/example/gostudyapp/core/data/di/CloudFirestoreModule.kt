package com.example.gostudyapp.core.data.di

import com.example.gostudyapp.core.data.database.repositories.GroupRepositoryImpl
import com.example.gostudyapp.core.data.database.repositories.RoomRepositoryImpl
import com.example.gostudyapp.core.data.database.repositories.ScheduleRepositoryImpl
import com.example.gostudyapp.core.data.database.repositories.ScheduleSubgroupRepositoryImpl
import com.example.gostudyapp.core.data.database.repositories.ScheduleTeacherRepositoryImpl
import com.example.gostudyapp.core.data.database.repositories.SubgroupRepositoryImpl
import com.example.gostudyapp.core.data.database.repositories.SubjectRepositoryImpl
import com.example.gostudyapp.core.data.database.repositories.TeacherRepositoryImpl
import com.example.gostudyapp.core.data.database.repositories.UserRepositoryImpl
import com.example.gostudyapp.core.domain.IRepository.GroupRepository
import com.example.gostudyapp.core.domain.IRepository.RoomRepository
import com.example.gostudyapp.core.domain.IRepository.ScheduleRepository
import com.example.gostudyapp.core.domain.IRepository.ScheduleSubgroupRepository
import com.example.gostudyapp.core.domain.IRepository.ScheduleTeacherRepository
import com.example.gostudyapp.core.domain.IRepository.SubgroupRepository
import com.example.gostudyapp.core.domain.IRepository.SubjectRepository
import com.example.gostudyapp.core.domain.IRepository.TeacherRepository
import com.example.gostudyapp.core.domain.IRepository.UserRepository
import com.example.gostudyapp.core.domain.usecases.GroupUseCase.AddGroupUseCase
import com.example.gostudyapp.core.domain.usecases.GroupUseCase.GetAllGroupsUseCase
import com.example.gostudyapp.core.domain.usecases.GroupUseCase.GetGroupByIdUseCase
import com.example.gostudyapp.core.domain.usecases.GroupUseCase.GetGroupIdByNumberUseCase
import com.example.gostudyapp.core.domain.usecases.RoomUseCase.AddRoomUseCase
import com.example.gostudyapp.core.domain.usecases.RoomUseCase.GetAllRoomsUseCase
import com.example.gostudyapp.core.domain.usecases.RoomUseCase.GetRoomByIdUseCase
import com.example.gostudyapp.core.domain.usecases.ScheduleSubgroupUseCase.AddScheduleSubgroupUseCase
import com.example.gostudyapp.core.domain.usecases.ScheduleTeacherUseCase.AddScheduleTeacherUseCase
import com.example.gostudyapp.core.domain.usecases.ScheduleUseCase.AddScheduleUseCase
import com.example.gostudyapp.core.domain.usecases.ScheduleUseCase.GetScheduleByIdUseCase
import com.example.gostudyapp.core.domain.usecases.ScheduleUseCase.GetScheduleForSubgroupUseCase
import com.example.gostudyapp.core.domain.usecases.ScheduleUseCase.GetScheduleWithDetailsForSubgroupUseCase
import com.example.gostudyapp.core.domain.usecases.ScheduleUseCase.GetScheduleWithDetailsUseCase
import com.example.gostudyapp.core.domain.usecases.SubgroupUseCase.AddSubgroupUseCase
import com.example.gostudyapp.core.domain.usecases.SubgroupUseCase.GetGroupNumberAndSubgroupNameUseCase
import com.example.gostudyapp.core.domain.usecases.SubgroupUseCase.GetSubgroupByIdUseCase
import com.example.gostudyapp.core.domain.usecases.SubgroupUseCase.GetSubgroupIdByGroupNumberAndSubgroupNameUseCase
import com.example.gostudyapp.core.domain.usecases.SubgroupUseCase.GetSubgroupIdByNumberAndGroupIdUseCase
import com.example.gostudyapp.core.domain.usecases.SubjectUseCase.AddSubjectUseCase
import com.example.gostudyapp.core.domain.usecases.SubjectUseCase.GetSubjectByIdUseCase
import com.example.gostudyapp.core.domain.usecases.TeacherUseCase.AddTeacherUseCase
import com.example.gostudyapp.core.domain.usecases.TeacherUseCase.GetAllTeachersUseCase
import com.example.gostudyapp.core.domain.usecases.TeacherUseCase.GetTeacherByIdUseCase
import com.example.gostudyapp.core.domain.usecases.UserUseCase.UpdateUserGroupIDUseCase
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
    fun provideSubgroupRepository(firestore: FirebaseFirestore): SubgroupRepository =
        SubgroupRepositoryImpl(firestore)
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
    fun provideUserRepository(firestore: FirebaseFirestore): UserRepository =
        UserRepositoryImpl(firestore)


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
    fun provideAddSubgroupUseCase(repository: SubgroupRepository) = AddSubgroupUseCase(repository)
    @Provides
    @Singleton
    fun provideGetSubgroupByIdUseCase(repository: SubgroupRepository) = GetSubgroupByIdUseCase(repository)

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
    fun provideGetScheduleForSubgroupUseCase(repository: ScheduleRepository) = GetScheduleForSubgroupUseCase(repository)
    @Provides
    @Singleton
    fun provideGetScheduleWithDetailsUseCase(repository: ScheduleRepository) = GetScheduleWithDetailsUseCase(repository)
    @Provides
    @Singleton
    fun provideGetScheduleWithDetailsForSubgroupUseCase(repository: ScheduleRepository) = GetScheduleWithDetailsForSubgroupUseCase(repository)

    @Provides
    @Singleton
    fun provideAddScheduleSubgroupUseCase(repository: ScheduleSubgroupRepository) = AddScheduleSubgroupUseCase(repository)

    @Provides
    @Singleton
    fun provideAddScheduleTeacherUseCase(repository: ScheduleTeacherRepository) = AddScheduleTeacherUseCase(repository)

    @Provides
    @Singleton
    fun provideUpdateUserGroupIDUseCase(repository: UserRepository) = UpdateUserGroupIDUseCase(repository)

    @Provides
    @Singleton
    fun provideGetGroupIdByNumberUseCase(repository: GroupRepository) = GetGroupIdByNumberUseCase(repository)

    @Provides
    @Singleton
    fun provideGetSubgroupIdByNumberAndGroupIdUseCase(repository: SubgroupRepository) = GetSubgroupIdByNumberAndGroupIdUseCase(repository)

    @Provides
    @Singleton
    fun provideGetSubgroupIdByGroupNumberAndSubgroupNameUseCase(repository: SubgroupRepository) = GetSubgroupIdByGroupNumberAndSubgroupNameUseCase(repository)

    @Provides
    @Singleton
    fun provideGetGroupNumberAndSubgroupNameUseCase(repository: SubgroupRepository) = GetGroupNumberAndSubgroupNameUseCase(repository)


}