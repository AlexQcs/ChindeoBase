package com.chindeo.repository.data.model.setting

data class PluginStatus(
        val packageName: String,
        var status: MQTTStatusEnum = MQTTStatusEnum.UN_KNOW
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PluginStatus

        if (packageName != other.packageName) return false

        return true
    }

    override fun hashCode(): Int {
        return packageName.hashCode()
    }
}
