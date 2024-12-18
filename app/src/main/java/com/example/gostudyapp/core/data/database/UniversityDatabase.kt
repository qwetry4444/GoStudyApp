package com.example.gostudyapp.core.data.database

//import androidx.room.Database
//import androidx.room.RoomDatabase
//import com.example.gostudyapp.core.data.database.dao.RoomDao
//import com.example.gostudyapp.core.data.database.dao.ScheduleDao
//import com.example.gostudyapp.core.data.database.dao.ScheduleSubgroupDao
//import com.example.gostudyapp.core.data.database.dao.ScheduleTeacherDao
//import com.example.gostudyapp.core.data.database.dao.SubgroupDao
//import com.example.gostudyapp.core.data.database.dao.TeacherDao
//import com.example.gostudyapp.core.data.database.entities.GroupDto
//import com.example.gostudyapp.core.data.database.entities.Room
//import com.example.gostudyapp.core.data.database.entities.ScheduleDto
//import com.example.gostudyapp.core.data.database.entities.ScheduleSubgroupDto
//import com.example.gostudyapp.core.data.database.entities.ScheduleTeacherDto
//import com.example.gostudyapp.core.data.database.entities.SubgroupDto
//import com.example.gostudyapp.core.data.database.entities.SubjectDto
//import com.example.gostudyapp.core.data.database.entities.TeacherDto

//@Database(
//    entities = [GroupDto::class, SubgroupDto::class, TeacherDto::class, Room::class, SubjectDto::class, ScheduleDto::class, ScheduleSubgroupDto::class, ScheduleTeacherDto::class],
//    version = 1,
//    exportSchema = false)
//abstract class UniversityDatabase : RoomDatabase() {
//    abstract fun scheduleDao(): ScheduleDao
//    abstract fun subgroupDao(): SubgroupDao
//    abstract fun teacherDao(): TeacherDao
//    abstract fun scheduleSubgroupDao(): ScheduleSubgroupDao
//    abstract fun scheduleTeacherDao(): ScheduleTeacherDao
//    abstract fun roomDao(): RoomDao
//
//}