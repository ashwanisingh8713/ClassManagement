package com.indusjs.cm.domain.model.project
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


//data class Project : ArrayList<ProjectItem>()
//typealias Project = ArrayList<ProjectItem>

@Serializable
data class Projects(
    val success: Boolean = true,
    val message: String = "",
    val projects: List<ProjectItem> = listOf()
)


@Serializable
data class ProjectItem(
    @SerialName("client_name")
    val clientName: String = "",
    @SerialName("contact_email")
    val contactEmail: String = "",
    @SerialName("created_date")
    val createdDate: String = "",
    @SerialName("deadline")
    val deadline: String = "",
    @SerialName("design_tool_used")
    val designToolUsed: String = "",
    @SerialName("notes")
    val notes: String = "",
    @SerialName("priority_level")
    val priorityLevel: String = "",
    @SerialName("project_quotation_price")
    val projectQuotationPrice: Int = 0,
    @SerialName("project_status")
    val projectStatus: String = "",
    @SerialName("project_summary")
    val projectSummary: String = "",
    @SerialName("project_title")
    val projectTitle: String = "",
    @SerialName("repository_link")
    val repositoryLink: String = "",
    @SerialName("required_tech_skills")
    val requiredTechSkills: List<String> = listOf(),
    @SerialName("wireframe_gallery")
    val wireframeGallery: List<String> = listOf()
)




