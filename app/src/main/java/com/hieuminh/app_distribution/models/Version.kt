package com.hieuminh.app_distribution.models

data class Version(
    val name: String,
    val builds: List<Build> = emptyList(),
    val isExpanded: Boolean = true,
)
