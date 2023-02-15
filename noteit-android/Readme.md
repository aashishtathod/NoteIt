<p align="center">
  <img src="https://github.com/aashishtathod/NoteIt/blob/main/art/logo.png" height="128"/>
</p>

# NoteIt (Android) 📱

_**NoteIt Android application**_ is built with _Modern Android application development_ tools or libraries. 
Aim of this project is to showcase Good practice implementation of Android application development with proper architecture design. 
_Dedicated to all Android Developers with_ ❤️.

## Features  👓

Currently the codebase focuses on following key things:

- [x] Single Activity Design
- [x] Clean and Simple Material UI 🎨
- [x] Jetpack Compose UI


Following features will be added in future:

-  Offline Capability - Notes ✈️
-  Dark mode 🌗
-  Tests


## About this project 💡

- NoteIt Android app is available with _Jetpack Compose UI_.


## Development Setup 🖥

You will require latest Canary version of Android Studio 4.2 or newer to be able to build the app. Download latest Canary release from [here](https://developer.android.com/studio/preview).

## Design / Wireframes ✨

Design of this awesome application is implemented by [Sanju S](https://github.com/Spikeysanju). You can see design below.

[![Figma Design](https://img.shields.io/badge/Figma-Design-pink.svg?style=for-the-badge&logo=figma)](https://www.figma.com/file/6TPCaBtOmfXQ48bYIkpDi6/NotyKT)


![](art/figma-light-mode.png)


![](art/figma-dark-mode.png)

## Built with 🛠

- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library --> Yet to be added.
  - [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) - WorkManager is an API that makes it easy to schedule deferrable, asynchronous tasks that are expected to run even if the app exits or the device restarts --> Yet to be added.
- [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started) Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app.
    - [Safe args](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args) - Gradle plugin that provides type safety when navigating and passing data between destinations. 
- [Jetpack Security](https://developer.android.com/topic/security/)
    - [Encrypted SharedPreference](https://developer.android.com/topic/security/data) - Used to store key-value data using encryption --> Yet to be added.
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed objects with protocol buffers.
- [Dependency Injection](https://developer.android.com/training/dependency-injection) - 
  - [Hilt-Dagger](https://dagger.dev/hilt/) - Standard library to incorporate Dagger dependency injection into an Android application.
  - [Hilt-ViewModel](https://developer.android.com/training/dependency-injection/hilt-jetpack) - DI for injecting `ViewModel`.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.
- [Jetpack Compose UI Toolkit](https://developer.android.com/jetpack/compose) - Modern UI development toolkit.

## Modules

- Currently the application has single `app` module, but sooner it will migrated to multi-modular architecture.
    

## Architecture

This app uses [_**MVVM (Model View View-Model)**_](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) and [_**Clean Architecture**_](https://medium.com/android-dev-hacks/detailed-guide-on-android-clean-architecture-9eab262a9011) architecture.

