package com.study.vipoliveira.investapp.data.network.contact.entities

data class ContactResponse (
        val id: Int,
        val type: Int,
        val message: String,
        val typeField: String?,
        val hidden: Boolean,
        val topSpacing: Double,
        val show: Int?,
        val required: Boolean
    )