# Grocery Catalog & Price Calculator

A Kotlin Android app that lets users browse a sample grocery catalog, view product details, and calculate the total cost of an item based on quantity.

## Features

- Browse grocery products in a scrollable list.
- Tap a product to view its image, description, and unit price.
- Enter a quantity and tap **Calculate** to see the total, formatted to two decimal places.
- See an on-screen error for blank, invalid, or negative quantities.

For example, **Fresh Red Apples** cost **$2.50** per unit. Entering a quantity of **3** displays **$7.50**.

## Built with

- **Kotlin** for app logic
- **XML layouts and Material Components** for the interface
- **RecyclerView** for the product catalog
- **Android Studio and Gradle** for development and builds

The catalog uses sample products defined in the app, with images bundled as drawable resources.

## Run locally

1. Clone or download the repository.
2. In Android Studio, select **Open** and choose the folder containing `settings.gradle.kts`.
3. Let Gradle sync. If Android Studio prompts you to install the SDK configured by the project, follow the prompt.
4. Select the `app` run configuration, choose an Android emulator or a connected device, and click **Run**. The app's minimum Android API level is **24**.
5. Open a product, enter a quantity, and tap **Calculate**.

## Project structure

| File or folder | Purpose |
| --- | --- |
| `MainActivity.kt` | Defines the sample catalog and opens the selected product. |
| `ProductAdapter.kt` | Displays products in the RecyclerView. |
| `DetailActivity.kt` | Displays product details, validates quantity, and calculates the total. |
| `Product.kt` | Defines the product data model. |
| `app/src/main/res/` | Contains XML layouts, strings, themes, and product drawables. |
