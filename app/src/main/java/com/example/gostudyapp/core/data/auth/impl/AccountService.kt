package com.example.gostudyapp.core.data.auth.impl

import com.example.gostudyapp.core.domain.usecases.SubgroupUseCase.GetSubgroupIdByGroupNumberAndSubgroupNameUseCase
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

//class AccountService @Inject constructor(private val firestore: FirebaseFirestore) : IAccountService
//{
//    override val currentUser: Flow<User?>
//        get() = callbackFlow {
//            val listener =
//                FirebaseAuth.AuthStateListener { auth ->
//                    this.trySend(auth.currentUser.toUser())
//                }
//            Firebase.auth.addAuthStateListener(listener)
//            awaitClose { Firebase.auth.removeAuthStateListener(listener) }
//        }
//
//    override val currentUserId: String
//        get() = Firebase.auth.currentUser?.uid.orEmpty()
//
//    override fun hasUser(): Boolean {
//        return Firebase.auth.currentUser != null
//    }
//
//    override fun getUserProfile(): User {
//        return Firebase.auth.currentUser.toUser()
//    }
//
//    override suspend fun createAnonymousAccount() {
//        Firebase.auth.signInAnonymously().await()
//    }
//
//    override suspend fun updateDisplayName(newDisplayName: String) {
//        val profileUpdates = userProfileChangeRequest {
//            displayName = newDisplayName
//        }
//
//        Firebase.auth.currentUser!!.updateProfile(profileUpdates).await()
//    }
//
//    override suspend fun linkAccountWithGoogle(idToken: String) {
//        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
//        Firebase.auth.currentUser!!.linkWithCredential(firebaseCredential).await()
//    }
//
//    override suspend fun createAccount(email: String, password: String, groupNumber: String) {
//        val credential = EmailAuthProvider.getCredential(email, password)
//        Firebase.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                val user = Firebase.auth.currentUser
//                user?.let {
//                    // Создаём документ пользователя в Firestore
//                    val userData = User(email = email, groupNumber = groupNumber)
//                    firestore.collection("users")
//                        .document(user.uid)
//                        .set(userData)
//                }
//        }
//        //Firebase.auth.currentUser!!.linkWithCredential(credential).await()
//        }
//    }
//
//    override suspend fun signInWithGoogle(idToken: String) {
//        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
//        Firebase.auth.signInWithCredential(firebaseCredential).await()
//    }
//
//    override suspend fun signInWithEmail(email: String, password: String) {
//        Firebase.auth.signInWithEmailAndPassword(email, password).await()
//    }
//
//    override suspend fun signOut() {
//        Firebase.auth.signOut()
//
//        // Sign the user back in anonymously.
//        createAnonymousAccount()
//    }
//
//    override suspend fun deleteAccount() {
//        Firebase.auth.currentUser!!.delete().await()
//    }
//
//    private fun FirebaseUser?.toUser(): User {
//        return if (this == null) User() else User(
//            id = this.uid,
//            email = this.email ?: "",
//        )
//    }
//}


class AccountService @Inject constructor(
    private val getSubgroupIdByGroupNumberAndSubgroupNameUseCase: GetSubgroupIdByGroupNumberAndSubgroupNameUseCase
) {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()


    suspend fun createAccount(email: String, password: String, groupNumber: String, subgroupName: String = "а") {
        try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val userId = authResult.user?.uid ?: throw Exception("Не удалось получить UID пользователя")

            val userData = mapOf(
                "uid" to userId,
                "subgroup_id" to getSubgroupIdByGroupNumberAndSubgroupNameUseCase.invoke(groupNumber, subgroupName)
            )
            firestore.collection("users").document(userId).set(userData).await()
        } catch (e: Exception) {
            throw Exception("Ошибка при создании аккаунта: ${e.message}", e)
        }
    }

    suspend fun signInWithEmail(email: String, password: String) {
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
        } catch (e: Exception) {
            throw Exception("Ошибка при авторизации: ${e.message}", e)
        }
    }

    fun currentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    suspend fun getUserData(): Map<String, Any>? {
        val currentUser = currentUser()
            ?: throw Exception("Пользователь не авторизован")

        return try {
            val document = firestore.collection("users").document(currentUser.uid).get().await()
            if (document.exists()) {
                document.data
            } else {
                throw Exception("Данные пользователя не найдены")
            }
        } catch (e: Exception) {
            throw Exception("Ошибка при получении данных пользователя: ${e.message}", e)
        }
    }

    fun signOut() {
        firebaseAuth.signOut()
    }

    suspend fun resetPassword(email: String) {
        try {
            firebaseAuth.sendPasswordResetEmail(email).await()
        } catch (e: Exception) {
            throw Exception("Ошибка при сбросе пароля: ${e.message}", e)
        }
    }

    suspend fun signInWithGoogle(idToken: String) {
        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
        Firebase.auth.signInWithCredential(firebaseCredential).await()
    }
}
