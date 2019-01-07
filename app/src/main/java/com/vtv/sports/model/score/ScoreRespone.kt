package com.vtv.sports.model.score

import com.google.gson.annotations.SerializedName
import com.vtv.sports.model.common.LastUpdated

/**
 * Created by Giang Long on 1/7/2019.
 * Skype: gianglong7695@gmail.com (id: gianglong7695_1)
 * Des:
 */
class ScoreRespone(
    @SerializedName("LastUpdated")
    val lastUpdated: LastUpdated,
    @SerializedName("Schedule")
    val schedule: List<Schedule>,
    @SerializedName("ScheduleCategory")
    val scheduleCategory: List<ScheduleCategory>
)


