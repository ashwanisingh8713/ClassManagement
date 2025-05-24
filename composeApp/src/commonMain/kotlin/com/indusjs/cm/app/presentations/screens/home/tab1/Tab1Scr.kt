package com.indusjs.cm.app.presentations.screens.home.tab1


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.indusjs.cm.app.model.ResourceUiState
import com.indusjs.cm.domain.model.project.ProjectItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.compose.getKoin



@Composable
fun Tab1Screen(tab1Navigator: NavHostController,
               topbarVisibility: MutableState<Boolean>,
               viewModel: Tab1_ProjectsViewModel = getKoin().get<Tab1_ProjectsViewModel>()) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(true) {
        viewModel.setEvent(Tab1_ProjectsContract.Event.OnProjectsLoadFromRemote)
    }
    // Set the top bar visibility to true when this screen is displayed
    when (uiState.projects) {
        is ResourceUiState.Loading -> {
            Text(
                text = "Loading projects...",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
            )
        }

        is ResourceUiState.Success -> {
            val projects = (uiState.projects as ResourceUiState.Success).data.projects
            // Display the list of projects
            ProjectList(projects = projects)
        }

        is ResourceUiState.Error -> {
            Text(
                text = "Failed to load projects: ${(uiState.projects as ResourceUiState.Error).message}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
            )
        }

        ResourceUiState.Empty -> {
            Text(
                text = "No projects available.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
            )
        }
        ResourceUiState.Idle -> {
            // Handle idle state if necessary
            Text(
                text = "Idle state, no projects loaded.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
            )
        }
    }
}

@Composable
fun ProjectList(projects: List<ProjectItem>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(projects) { project ->
            ProjectCard(project = project)
        }
    }
}

@Composable
fun ProjectCard(project: ProjectItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle card click, e.g., navigate to detail screen */ },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // Project Title
            Text(
                text = project.projectTitle,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Client Name
            DetailRow(icon = Icons.Default.Person, label = "Client:", value = project.clientName)

            // Project Summary
            Text(
                text = project.projectSummary,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // Wireframe Gallery
            if (project.wireframeGallery.isNotEmpty()) {
                Text(
                    text = "Wireframe Gallery:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(project.wireframeGallery) { imageUrl ->
                        // In a real app, you would use an image loading library like Coil or Glide here.
                        // For demonstration, we'll just show the URL as clickable text.
                        Surface(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .width(120.dp)
                                .height(80.dp)
                                .clickable { /* Handle image click, e.g., open full image */ }
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize().padding(4.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Wireframe",
                                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                    modifier = Modifier.size(24.dp)
                                )
                                Text(
                                    text = "Wireframe",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    maxLines = 1,
                                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "Click to view",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    maxLines = 1,
                                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }


            // Quotation Price
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Quotation Price:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "$${project.projectQuotationPrice}",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            // Created Date & Deadline
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DetailRow(icon = Icons.Default.DateRange, label = "Created:", value = project.createdDate)
                //DetailRow(icon = Icons.Default.DateRange, label = "Deadline:", value = project.created_date)
            }

            // Priority Level
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Priority",
                    tint = when (project.priorityLevel) {
                        "High" -> Color(0xFFE53935) // Red
                        "Medium" -> Color(0xFFFFB300) // Amber
                        "Low" -> Color(0xFF43A047) // Green
                        else -> Color.Gray
                    },
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Priority: ${project.priorityLevel}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = when (project.priorityLevel) {
                        "High" -> Color(0xFFE53935)
                        "Medium" -> Color(0xFFFFB300)
                        "Low" -> Color(0xFF43A047)
                        else -> Color.Gray
                    }
                )
            }

            // Project Status
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val statusColor = when (project.projectStatus) {
                    "In Progress" -> Color(0xFF1E88E5) // Blue
                    "Pending" -> Color(0xFFFB8C00) // Orange
                    "Completed" -> Color(0xFF4CAF50) // Green
                    else -> Color.Gray
                }
                Surface(
                    color = statusColor,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(
                        text = project.projectStatus,
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Text(
                    text = "Status",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // Required Tech Skills
            Text(
                text = "Required Skills:",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                project.requiredTechSkills.forEach { skill ->
                    SkillChip(skill = skill)
                }
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            // Contact Email
            DetailRow(icon = Icons.Default.Email, label = "Contact:", value = project.contactEmail)

            // Repository Link (clickable placeholder)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* Handle link click, e.g., open in browser */ }
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = "Repository Link",
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Repository: ${project.repositoryLink}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary, // Make it look like a link
                    textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline,
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )
            }

            // Design Tool Used
            DetailRow(icon = Icons.Default.Info, label = "Design Tool:", value = project.designToolUsed)

            // Notes
            if (project.notes.isNotBlank()) {
                Text(
                    text = "Notes:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                )
                Text(
                    text = project.notes,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )
            }
        }
    }
}

@Composable
fun DetailRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = "$label ",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun SkillChip(skill: String) {
    Surface(
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.wrapContentWidth()
    ) {
        Text(
            text = skill,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

// A simple FlowRow implementation as it's not directly available in Compose Multiplatform
// (You might need to add a dependency like 'com.google.accompanist:accompanist-flowlayout:0.28.0'
// or implement a custom layout for more complex scenarios)
@Composable
fun FlowRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable () -> Unit
) {
    // This is a simplified placeholder. For a true FlowRow, you'd typically use
    // Accompanist Flow Layout or a custom layout.
    // For demonstration, we'll just use a Column and wrap content.
    // In a real KMM project, you'd import the proper FlowRow from Accompanist.
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = Alignment.Top
        ) {
            content()
        }
    }
}






