package com.indusjs.cm.domain.repo

interface ICommunityRepo {
    fun getManagementCommittee(param: Any?): List<Any>?
    fun getNeighbours(param: Any?): List<Any>?
    fun getServiceVendors(param: Any?): List<Any>?
    fun getEmergencyContacts(param: Any?): List<Any>?
}