package com.example.gostudyapp.core.data.database.repositories

import com.example.gostudyapp.core.data.database.entities.RoomDto
import com.example.gostudyapp.core.data.database.mappers.toDomain
import com.example.gostudyapp.core.data.database.mappers.toDto
import com.example.gostudyapp.core.domain.IRepository.RoomRepository
import com.example.gostudyapp.core.domain.model.Schedule.Room
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomRepositoryImpl @Inject constructor (private val firestore: FirebaseFirestore) : RoomRepository {
    override suspend fun getRoomById(roomId: String): Room? {
        val document = firestore.collection("rooms")
            .document(roomId.toString())
            .get()
            .await()

        return document.toObject(RoomDto::class.java)?.toDomain()
    }

    override suspend fun getAllRooms(): List<Room> {
        return firestore.collection("rooms")
            .get()
            .await()
            .documents
            .mapNotNull { it.toObject(RoomDto::class.java)?.toDomain() }
    }

    override suspend fun addRoom(room: Room) {
        firestore.collection("rooms")
            .add(room.toDto())
            .await()
    }
}