package com.chindeo.repository.data.model.response.nursedoc

data class Patient(val bedDay: Int = 0,
                   val diaShow: Int = 0,
                   val sex: String = "",
                   val nurseLevel: Int = 0,
                   val nurseEventTags: List<NurseEventTagsItem>?,
                   val admNo: String = "",
                   val locDesc: String = "",
                   val doctor: String = "",
                   val bedDate: String = "",
                   val name: String = "",
                   val nurse: String = "",
                   val id: Int = 0,
                   val patNo: String = "",
                   val age: String = "",
                   val diaDesc: String = "")