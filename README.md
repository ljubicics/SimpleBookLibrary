# Simple Book Library

A modern Android application for reading PDF books with a clean, Material Design UI. The app provides seamless PDF reading experience with optimized page rendering and navigation.

## Features

- **PDF Viewer**: Smooth, high-performance PDF reading experience
- **Page Preloading**: Intelligent page preloading system for seamless navigation
- **Material Design UI**: Clean, intuitive interface following modern Android design patterns

## Technical Details

### Architecture
- **MVI**: Utilizes ViewModel and StateFlow for reactive UI updates
- **Jetpack Compose**: Modern declarative UI toolkit
- **Kotlin Coroutines**: For asynchronous operations
- **Dependency Injection**: Using Koin for service locator pattern

### Key Components
- `PdfViewerViewModel`: Manages PDF rendering state and loading logic
- `PdfBitmapConverter`: Handles PDF to bitmap conversion with rendering optimizations
- `HorizontalPager`: Provides smooth page swiping experience

## Future Roadmap

- **Library Management**: Organize and categorize books
- **Reading Progress Tracking**: Save reading positions and track completion
- **AI Integration**: Smart chapter summarization feature
- **Reading Statistics**: Track reading habits and provide insights
- **Cloud Sync**: Synchronize reading progress across devices

## AI Features in Development

The upcoming AI integration will provide:
- **Chapter Summarization**: AI-generated summaries of current chapter
- **Content Analysis**: Key points and themes extraction
- **Vocabulary Enhancement**: Highlighting and explaining complex terms
- **Personalized Insights**: Tailored reading recommendations

## Getting Started

1. Clone the repository
2. Open in Android Studio
3. Build and run on an emulator or physical device

## Requirements

- Android 7.0 (API level 24) or higher
- Gradle 7.0+ and Android Gradle Plugin 7.0+

## License

[MIT License](LICENSE)