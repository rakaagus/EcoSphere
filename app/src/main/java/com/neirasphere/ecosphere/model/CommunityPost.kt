package com.neirasphere.ecosphere.model

import java.util.Date

data class CommunityPost(
    val id: Int,
    val user: User,
    val text: String,
    val image: Int?,
    val comments: Int,
    var likes: Int,
    var liked: Boolean,
    val views: Int,
    val timestamp: Long
) {
    fun like() {
        this.liked = !this.liked
        this.likes = if (this.liked) this.likes + 1 else this.likes - 1
    }

    fun timeAgo(): String {
        val currentTime = Date().time;
        val timeDiff = currentTime - timestamp;
        if (timeDiff >= (1000 * 60 * 60 * 24)) {
            // Days
            return "${timeDiff / (1000 * 60 * 60 * 24)}d";
        } else if (timeDiff >= (1000 * 60 * 60)) {
            // Hours
            return "${timeDiff / (1000 * 60 * 60)}h";
        } else if (timeDiff >= (1000 * 60)) {
            // Minutes
            return "${timeDiff / (1000 * 60)}m";
        } else if (timeDiff >= 1000) {
            // Seconds
            return "${timeDiff / 1000}s";
        }
        return "0s";
    }
}