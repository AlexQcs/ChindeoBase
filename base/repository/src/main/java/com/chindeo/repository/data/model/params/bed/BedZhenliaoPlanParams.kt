package com.chindeo.repository.data.model.params.bed

class BedZhenliaoPlanParams private constructor() {

    var admId: String? = null
    var type: String? = null
    var status: String? = null


    constructor(admId: String, menuType: String, isFinish: Boolean) : this() {
        this.admId = admId
        this.type = menuType
        this.status = if (isFinish) "F" else "A"
    }
}