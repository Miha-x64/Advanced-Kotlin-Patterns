package net.aquadc.advancedkotlinpatterns.feature.sealed.example

sealed class Attachment

class Link(
        val url: String,
        val title: String,
        val description: String,
        val imageUrl: String?
) : Attachment()

class Photo(
        val text: String,
        val photoUrl: String
) : Attachment()

class Sticker(
        val imageUrl: String
) : Attachment()
