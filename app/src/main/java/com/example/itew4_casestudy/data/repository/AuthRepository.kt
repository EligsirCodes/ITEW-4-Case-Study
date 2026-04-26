package com.example.itew4_casestudy.data.repository

import com.example.itew4_casestudy.domain.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    sealed class AuthResult {
        data class Success(val role: String? = null) : AuthResult()
        data class Error(val message: String) : AuthResult()
    }

    suspend fun registerUser(email: String, password: String, name: String, role: String): AuthResult {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val user = authResult.user

            if (user != null) {
                val userModel = UserModel(
                    uid = user.uid,
                    name = name,
                    email = email,
                    role = role
                )

                firestore.collection("users")
                    .document(user.uid)
                    .set(userModel)
                    .await()

                AuthResult.Success()
            } else {
                AuthResult.Error("Failed to create user account.")
            }
        } catch (e: Exception) {
            AuthResult.Error(e.localizedMessage ?: "An unknown error occurred during registration.")
        }
    }

    suspend fun loginUser(email: String, password: String): AuthResult {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val uid = authResult.user?.uid

            if (uid != null) {
                val document = firestore.collection("users")
                    .document(uid)
                    .get()
                    .await()

                val role = document.getString("role") ?: "Student"

                AuthResult.Success(role)
            } else {
                AuthResult.Error("User identification failed.")
            }
        } catch (e: Exception) {
            AuthResult.Error(e.localizedMessage ?: "Invalid email or password.")
        }
    }
}