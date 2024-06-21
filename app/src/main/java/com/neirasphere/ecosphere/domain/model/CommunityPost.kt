package com.neirasphere.ecosphere.domain.model

import java.text.SimpleDateFormat
import java.util.Locale

data class CommunityPost(
    val id: Int,
    val user: com.neirasphere.ecosphere.domain.model.User,
    val text: String,
    val image: Int?,
    val comments: Int,
    var likes: Int,
    var liked: Boolean,
    val views: Int,
    val createdAt: String
) {
    fun like() {
        this.liked = !this.liked
        this.likes = if (this.liked) this.likes + 1 else this.likes - 1
    }

    fun timeDiff(): Long {
        val now = System.currentTimeMillis()
        val postCreatedAt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(this.createdAt)
        val createdAtMills = postCreatedAt.time
        val differenceMillis = now - createdAtMills

        return differenceMillis
    }

    fun timeAgo(): String {
        val seconds = this.timeDiff()/1000
        val minutes = seconds/60
        val hours = minutes/60
        val days = hours/24

        val relativeTime: String
        if (seconds < 60) {
            relativeTime = "$seconds detik lalu"
        } else if (minutes < 60) {
            relativeTime = "$minutes menit lalu"
        } else if (hours < 24) {
            relativeTime = "$hours jam lalu"
        } else {
            relativeTime = "$days hari lalu"
        }

        return relativeTime
    }

    fun numberDisplay(number: Int): String {
        if (number < 1000) {
            return number.toString()
        } else if (number < 1000000) {
            return (number / 1000).toString() + "Rb"
        } else if (number < 1000000000) {
            return (number / 1000000).toString() + "Jt"
        } else {
            return (number / 1000000000).toString() + "M"
        }
    }

    fun dateDisplay(): String {
        val postCreatedString = this.createdAt
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val postCreatedAt = simpleDateFormat.parse(postCreatedString)

        val formattedDate = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(postCreatedAt)

        return formattedDate
    }

    fun timeDisplay(): String {
        val postCreatedString = this.createdAt
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val postCreatedAt = simpleDateFormat.parse(postCreatedString)

        val formattedTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(postCreatedAt)

        return formattedTime
    }
}

data class CommunityPostSQL(
    val id: Int,
    val user: com.neirasphere.ecosphere.domain.model.User,
    val text: String,
    val image: String? = null,
    val comments: Int,
    var likes: Int,
    var liked: Boolean,
    val views: Int,
    val createdAt: String
) {
    fun like() {
        this.liked = !this.liked
        this.likes = if (this.liked) this.likes + 1 else this.likes - 1
    }

    fun timeDiff(): Long {
        val now = System.currentTimeMillis()
        val postCreatedAt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(this.createdAt)
        val createdAtMills = postCreatedAt.time
        val differenceMillis = now - createdAtMills

        return differenceMillis
    }

    fun timeAgo(): String {
        val seconds = this.timeDiff()/1000
        val minutes = seconds/60
        val hours = minutes/60
        val days = hours/24

        val relativeTime: String
        if (seconds < 60) {
            relativeTime = "$seconds detik lalu"
        } else if (minutes < 60) {
            relativeTime = "$minutes menit lalu"
        } else if (hours < 24) {
            relativeTime = "$hours jam lalu"
        } else {
            relativeTime = "$days hari lalu"
        }

        return relativeTime
    }

    fun numberDisplay(number: Int): String {
        if (number < 1000) {
            return number.toString()
        } else if (number < 1000000) {
            return (number / 1000).toString() + "Rb"
        } else if (number < 1000000000) {
            return (number / 1000000).toString() + "Jt"
        } else {
            return (number / 1000000000).toString() + "M"
        }
    }

    fun dateDisplay(): String {
        val postCreatedString = this.createdAt
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val postCreatedAt = simpleDateFormat.parse(postCreatedString)

        val formattedDate = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(postCreatedAt)

        return formattedDate
    }

    fun timeDisplay(): String {
        val postCreatedString = this.createdAt
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val postCreatedAt = simpleDateFormat.parse(postCreatedString)

        val formattedTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(postCreatedAt)

        return formattedTime
    }
}