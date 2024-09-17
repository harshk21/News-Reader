### **News Reader App**
This project is a basic news reader app with background change in a RecyclerView adapter using Kotlin. It utilizes the News API (https://newsapi.org/) to fetch news articles.

#### Features
1. Fetch news articles from get headlines api.
2. Display news articles in a scrollable list.
3. Detail article screen 

#### **Setup and Run Instructions**

* Clone the repository
`git clone https://github.com/harshk21/News-Reader.git`

* Get API key:
  * Create a free account on News API (https://newsapi.org/).
  * Generate an API key under your account.
  * Replace API in app/build.gradle.kts

### Architectural Decisions:
* Implemented Hilt for dependency injection which helped in object creation.
* Implemented kotlin coroutines where ever was possible with proper API states (Loading, Error & Success).
* Implemented MVVM architecture and followed simple packaging.

#### Folder structure:

   ![image](https://github.com/harshk21/NewsReaderApp/assets/26200591/7f046bf0-9ac8-40d0-99d1-1740426af674)

#### Artifacts:

![Group 1](https://github.com/harshk21/NewsReaderApp/assets/26200591/ffaa0b65-1abe-4a0d-a6c1-4a0b91644d2d)












