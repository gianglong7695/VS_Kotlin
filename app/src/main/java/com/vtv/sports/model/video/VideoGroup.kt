package com.vtv.sports.model.video

/**
 * Created by Giang Long on 12/25/2018.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
data class VideoGroup(
        val videoZone: VideoZone,
        val listVideo: List<VideoBox>,
        val type: Int
)