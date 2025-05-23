package com.indusjs.cm.app.presentations.theme



import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp


import androidx.compose.ui.graphics.Color


// --- Base Color ---
// The user-provided base color, a light sky blue.
val BaseThemeColor = Color(0xFFE1F5FE)

val topbarColor:List<Color> = listOf(Color(0xFFE1F5FE), Color(0xFFFFFFFF))

// --- Light Theme Color Palette ---
// Derived from or to complement the BaseThemeColor.

// Primary
val PrimaryLight = Color(0xFF00677F) // A moderately dark, saturated cyan/blue
val OnPrimaryLight = Color.White
val PrimaryContainerLight = BaseThemeColor // Using the user's base color here
val OnPrimaryContainerLight = Color(0xFF001F28) // Dark text for good contrast on light blue

// Secondary
val SecondaryLight = Color(0xFF4B626B) // Muted blue-gray
val OnSecondaryLight = Color.White
val SecondaryContainerLight = Color(0xFFCFE6F1)
val OnSecondaryContainerLight = Color(0xFF071E26)

// Tertiary
val TertiaryLight = Color(0xFF5B5D7E) // Muted purple-blue
val OnTertiaryLight = Color.White
val TertiaryContainerLight = Color(0xFFE0E0FF)
val OnTertiaryContainerLight = Color(0xFF181A37)

// Error
val ErrorLight = Color(0xFFBA1A1A)
val OnErrorLight = Color.White
val ErrorContainerLight = Color(0xFFFFDAD6)
val OnErrorContainerLight = Color(0xFF410002)

// Background & Surface
val BackgroundLight = Color(0xFFF6FCFF) // Very light, almost white with a hint of blue
val OnBackgroundLight = Color(0xFF171C1F)
val SurfaceLight = Color(0xFFF6FCFF)
val OnSurfaceLight = Color(0xFF171C1F)
val SurfaceVariantLight = Color(0xFFDBE4E9) // For cards, slightly darker than surface
val OnSurfaceVariantLight = Color(0xFF3F484C)
val OutlineLight = Color(0xFF6F797D)
val OutlineVariantLight = Color(0xFFBFC8CC) // Softer outline

// --- Dark Theme Color Palette ---
// Designed to offer a good dark mode experience, related to the light theme's feel.

// Primary
val PrimaryDark = Color(0xFF7BD0EE) // A lighter, vibrant cyan/blue for dark theme
val OnPrimaryDark = Color(0xFF003543)
val PrimaryContainerDark = Color(0xFF004D60) // Darker container
val OnPrimaryContainerDark = Color(0xFFC2E8F6) // Lighter text for this container

// Secondary
val SecondaryDark = Color(0xFFB4CAD4) // Light blue-gray
val OnSecondaryDark = Color(0xFF1D333C)
val SecondaryContainerDark = Color(0xFF344A53)
val OnSecondaryContainerDark = Color(0xFFCFE6F1)

// Tertiary
val TertiaryDark = Color(0xFFC3C3EB) // Light muted purple-blue
val OnTertiaryDark = Color(0xFF2D2F4D)
val TertiaryContainerDark = Color(0xFF444564)
val OnTertiaryContainerDark = Color(0xFFE0E0FF)

// Error (can be the same or adjusted for dark theme aesthetics)
val ErrorDark = Color(0xFFFFB4AB)
val OnErrorDark = Color(0xFF690005)
val ErrorContainerDark = Color(0xFF93000A)
val OnErrorContainerDark = Color(0xFFFFDAD6)

// Background & Surface
val BackgroundDark = Color(0xFF171C1F) // Dark gray/blue
val OnBackgroundDark = Color(0xFFDFE3E7)
val SurfaceDark = Color(0xFF171C1F)
val OnSurfaceDark = Color(0xFFDFE3E7)
val SurfaceVariantDark = Color(0xFF3F484C) // For cards
val OnSurfaceVariantDark = Color(0xFFBFC8CC)
val OutlineDark = Color(0xFF899297)
val OutlineVariantDark = Color(0xFF3F484C) // Softer outline

// --- Material 3 ColorSchemes ---

private val LightColors = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimaryLight,
    primaryContainer = PrimaryContainerLight,
    onPrimaryContainer = OnPrimaryContainerLight,
    secondary = SecondaryLight,
    onSecondary = OnSecondaryLight,
    secondaryContainer = SecondaryContainerLight,
    onSecondaryContainer = OnSecondaryContainerLight,
    tertiary = TertiaryLight,
    onTertiary = OnTertiaryLight,
    tertiaryContainer = TertiaryContainerLight,
    onTertiaryContainer = OnTertiaryContainerLight,
    error = ErrorLight,
    onError = OnErrorLight,
    errorContainer = ErrorContainerLight,
    onErrorContainer = OnErrorContainerLight,
    background = BackgroundLight,
    onBackground = OnBackgroundLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,
    outline = OutlineLight,
    outlineVariant = OutlineVariantLight
)

private val DarkColors = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = OnPrimaryDark,
    primaryContainer = PrimaryContainerDark,
    onPrimaryContainer = OnPrimaryContainerDark,
    secondary = SecondaryDark,
    onSecondary = OnSecondaryDark,
    secondaryContainer = SecondaryContainerDark,
    onSecondaryContainer = OnSecondaryContainerDark,
    tertiary = TertiaryDark,
    onTertiary = OnTertiaryDark,
    tertiaryContainer = TertiaryContainerDark,
    onTertiaryContainer = OnTertiaryContainerDark,
    error = ErrorDark,
    onError = OnErrorDark,
    errorContainer = ErrorContainerDark,
    onErrorContainer = OnErrorContainerDark,
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,
    outline = OutlineDark,
    outlineVariant = OutlineVariantDark
)

// --- Custom Theme Colors (Beyond Material ColorScheme) ---
// Define a data class for any additional colors your app might need.
data class AppCustomColors(
    val success: Color,
    val onSuccess: Color,
    val successContainer: Color,
    val onSuccessContainer: Color,
    val warning: Color,
    val onWarning: Color
    // Add other custom colors here, e.g., specific chart colors, status colors
)

// Define instances for light and dark themes
val LightCustomColors = AppCustomColors(
    success = Color(0xFF2E7D32), // Example success green
    onSuccess = Color.White,
    successContainer = Color(0xFFC8E6C9),
    onSuccessContainer = Color(0xFF1B5E20),
    warning = Color(0xFFFFA000), // Example warning amber
    onWarning = Color.Black
)

val DarkCustomColors = AppCustomColors(
    success = Color(0xFF81C784), // Lighter success green for dark theme
    onSuccess = Color.Black,
    successContainer = Color(0xFF1B5E20),
    onSuccessContainer = Color(0xFFC8E6C9),
    warning = Color(0xFFFFCA28), // Lighter warning amber
    onWarning = Color.Black
)

// Create a CompositionLocal to provide AppCustomColors down the Composable tree
private val LocalAppCustomColors = staticCompositionLocalOf { LightCustomColors }

// --- Main AppTheme Composable ---
/**
 * Applies the application theme.
 *
 * @param useDarkTheme Whether to use the dark theme. Defaults to the system setting.
 * @param content The content to be themed.
 */
@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val M3colors = if (useDarkTheme) DarkColors else LightColors
    val customColors = if (useDarkTheme) DarkCustomColors else LightCustomColors

    // Provide the custom colors via CompositionLocalProvider
    CompositionLocalProvider(LocalAppCustomColors provides customColors) {
        MaterialTheme(
            colorScheme = M3colors,
            typography = AppTypography, // Define AppTypography in your project (e.g., Type.kt)
            shapes = AppShapes,       // Define AppShapes in your project (e.g., Shape.kt)
            content = content
        )
    }
}

// --- Theme Accessor Object ---
/**
 * Object to easily access theme properties (both Material 3 and Custom).
 */
object AppTheme {
    /** Provides access to the Material 3 ColorScheme. */
    val M3Colors: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    /** Provides access to custom colors defined in AppCustomColors. */
    val CustomColors: AppCustomColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppCustomColors.current

    // --- Specific Component Color Accessors ---
    // These primarily map to Material 3 ColorScheme roles,
    // which components like TopAppBar, Card, Button use by default.

    /** Background color for TopAppBar. Typically `primary` or `surface`. */
    val topBarBackgroundColor: Color
        @Composable
        @ReadOnlyComposable
        get() = M3Colors.primary // Or M3Colors.surface for a less prominent top bar

    /** Content color (title, icons) for TopAppBar. */
    val topBarContentColor: Color
        @Composable
        @ReadOnlyComposable
        get() = M3Colors.onPrimary // Or M3Colors.onSurface if topBar is surface-colored

    /** Background color for BottomAppBar or NavigationBar. Typically `surface` or `secondaryContainer`. */
    val bottomBarBackgroundColor: Color
        @Composable
        @ReadOnlyComposable
        get() = M3Colors.surface // Or M3Colors.surfaceContainer for a slightly different shade

    /** Content color (icons, text) for BottomAppBar or NavigationBar. */
    val bottomBarContentColor: Color
        @Composable
        @ReadOnlyComposable
        get() = M3Colors.onSurface // Or M3Colors.onSurfaceVariant

    /** Background color for Card components. Typically `surfaceVariant` in M3. */
    val cardBackgroundColor: Color
        @Composable
        @ReadOnlyComposable
        get() = M3Colors.surfaceVariant

    /** Content color for Card components. */
    val cardContentColor: Color
        @Composable
        @ReadOnlyComposable
        get() = M3Colors.onSurfaceVariant

    /** Primary text color, typically used on `background` or `surface`. */
    val textColorPrimary: Color
        @Composable
        @ReadOnlyComposable
        get() = M3Colors.onBackground

    /** Secondary text color for less emphasis, often on `surfaceVariant` or for hints. */
    val textColorSecondary: Color
        @Composable
        @ReadOnlyComposable
        get() = M3Colors.onSurfaceVariant

    /** Text color to be used on a `primary` colored background. */
    val textOnPrimaryColor: Color
        @Composable
        @ReadOnlyComposable
        get() = M3Colors.onPrimary

    /**
     * TextField colors are complex and usually handled by the `TextFieldColors` parameter
     * in `TextField` composables, which derive from the `ColorScheme`.
     * Example: `TextFieldDefaults.colors()`
     * This provides the default text input color.
     */
    val textFieldTextColor: Color
        @Composable
        @ReadOnlyComposable
        get() = M3Colors.onSurface

    /** Default placeholder color for TextFields. */
    val textFieldPlaceholderColor: Color
        @Composable
        @ReadOnlyComposable
        get() = M3Colors.onSurfaceVariant

    /** Border color for TextFields (when outlined). */
    val textFieldBorderColor: Color
        @Composable
        @ReadOnlyComposable
        get() = M3Colors.outline

    /** Primary icon color, typically used on `surface` or `background`. */
    val iconColorPrimary: Color
        @Composable
        @ReadOnlyComposable
        get() = M3Colors.onSurface

    /** Secondary icon color for less emphasis. */
    val iconColorSecondary: Color
        @Composable
        @ReadOnlyComposable
        get() = M3Colors.onSurfaceVariant

    /** Icon color for icons placed on a `primary` colored background. */
    val iconColorOnPrimary: Color
        @Composable
        @ReadOnlyComposable
        get() = M3Colors.onPrimary

    /**
     * Button colors are best handled by `ButtonDefaults.buttonColors()`,
     * which intelligently pick from the `ColorScheme`.
     * This provides the default background for a filled button.
     */
    val buttonBackgroundColor: Color
        @Composable
        @ReadOnlyComposable
        get() = M3Colors.primary

    /** Default content color for a filled button. */
    val buttonContentColor: Color
        @Composable
        @ReadOnlyComposable
        get() = M3Colors.onPrimary

    /** Default content color for a TextButton or OutlinedButton. */
    val textButtonContentColor: Color
        @Composable
        @ReadOnlyComposable
        get() = M3Colors.primary
}

// --- Typography and Shapes (Placeholders) ---
// You should define these in separate files (e.g., Type.kt, Shape.kt)
// and customize them according to your design system.

/** Placeholder for application typography. Customize with your fonts and text styles. */
val AppTypography = androidx.compose.material3.Typography(
    // Define your text styles here, e.g.:
    // bodyLarge = TextStyle(fontFamily = FontFamily.Default, fontWeight = FontWeight.Normal, fontSize = 16.sp)
)

/** Placeholder for application shapes. Customize corner radii for components. */
val AppShapes = androidx.compose.material3.Shapes(
    // Define your shapes here, e.g.:
     small = RoundedCornerShape(4.dp),
     medium = RoundedCornerShape(8.dp),
     large = RoundedCornerShape(16.dp)
)

/*
// --- How to Use in your App.kt or main Composable ---

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.* // Import Material 3 components
// ... other necessary imports

@Composable
fun App() {
    AppTheme { // Apply the AppTheme at the root of your application
        MainScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class) // For TopAppBar and other M3 components
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Themed App") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppTheme.topBarBackgroundColor,
                    titleContentColor = AppTheme.topBarContentColor,
                    actionIconContentColor = AppTheme.topBarContentColor // Ensure action icons are also themed
                )
            )
        },
        containerColor = AppTheme.M3Colors.background // Use background from M3Colors
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp), // Add some general padding
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Welcome to the App!",
                style = MaterialTheme.typography.headlineSmall, // Use typography from theme
                color = AppTheme.textColorPrimary // Use primary text color
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = AppTheme.cardBackgroundColor,
                    contentColor = AppTheme.cardContentColor
                )
            ) {
                Text(
                    "This is a themed card using surfaceVariant.",
                    modifier = Modifier.padding(16.dp)
                )
            }

            Button(
                onClick = { /* TODO */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.buttonBackgroundColor,
                    contentColor = AppTheme.buttonContentColor
                )
            ) {
                Icon(Icons.Filled.Favorite, contentDescription = "Favorite", modifier = Modifier.size(ButtonDefaults.IconSize))
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Primary Button")
            }

            OutlinedButton(
                onClick = { /* TODO */ },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = AppTheme.textButtonContentColor // Uses primary by default
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = SolidColor(AppTheme.M3Colors.outline) // Use outline color for border
                )
            ) {
                Text("Outlined Button")
            }

            Text(
                "A warning message!",
                color = AppTheme.CustomColors.warning, // Using a custom color
                modifier = Modifier
                    .background(AppTheme.M3Colors.surfaceVariant, RoundedCornerShape(4.dp))
                    .padding(8.dp)
            )

            var textState by remember { mutableStateOf("") }
            TextField(
                value = textState,
                onValueChange = { textState = it },
                label = { Text("Enter text") },
                colors = TextFieldDefaults.colors( // M3 TextField theming
                    focusedTextColor = AppTheme.textFieldTextColor,
                    unfocusedTextColor = AppTheme.textFieldTextColor,
                    focusedContainerColor = AppTheme.M3Colors.surfaceColorAtElevation(Elevation.Level2),
                    unfocusedContainerColor = AppTheme.M3Colors.surfaceColorAtElevation(Elevation.Level1),
                    disabledContainerColor = AppTheme.M3Colors.surfaceColorAtElevation(Elevation.Level0),
                    cursorColor = AppTheme.M3Colors.primary,
                    focusedIndicatorColor = Color.Transparent, // Or AppTheme.M3Colors.primary
                    unfocusedIndicatorColor = Color.Transparent, // Or AppTheme.M3Colors.outline
                    focusedLabelColor = AppTheme.M3Colors.primary,
                    unfocusedLabelColor = AppTheme.textFieldPlaceholderColor,
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

// You'll need these for the example:
// import androidx.compose.material.icons.Icons
// import androidx.compose.material.icons.filled.Favorite
// import androidx.compose.runtime.getValue
// import androidx.compose.runtime.mutableStateOf
// import androidx.compose.runtime.remember
// import androidx.compose.runtime.setValue
// import androidx.compose.ui.Alignment
// import androidx.compose.ui.Modifier
// import androidx.compose.ui.graphics.SolidColor
// import androidx.compose.ui.unit.dp
// import androidx.compose.foundation.shape.RoundedCornerShape
// import androidx.compose.material3.surfaceColorAtElevation
// import androidx.compose.material3.Elevation (this is not a class, use Dp values for elevation directly or Surface())

// For surfaceColorAtElevation, you might need to provide Dp values:
// e.g., AppTheme.M3Colors.surfaceColorAtElevation(3.dp) for Material 3
// Or, more simply, use predefined container colors if suitable.
// The example above uses Elevation.LevelX which is not standard.
// A better way for TextField background:
// focusedContainerColor = AppTheme.M3Colors.surfaceContainerHighest, (or similar M3 role)
// unfocusedContainerColor = AppTheme.M3Colors.surfaceContainerHigh,
*/

