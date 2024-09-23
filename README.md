# CURD Application
This is a demo Android project built using Kotlin that demonstrates the use of Room Database with MVVM (Model-View-ViewModel) architecture. The project features an application that handles a list of subscribers, with functionalities to display and manage the list using Room, LiveData, and RecyclerView.
## Features
MVVM Architecture: Follows the MVVM pattern to maintain a separation of concerns.
Room Database: Utilizes Room for local persistence of data.
LiveData: Observes changes in the database and automatically updates the UI.
RecyclerView: Displays the list of subscribers.
Dependency Injection: Uses ViewModelProvider to inject ViewModel dependencies.
## How It Works
### Room Database
Entities: Defines the data structure stored in the Room database. In this case, the entity is Subscriber.
DAO (Data Access Object): Defines methods for interacting with the database.
Database: Ties together the DAO and entities.
### MVVM Pattern
Model: Contains the data logic (Room database).
ViewModel: Holds UI data and interacts with the repository to get data.
View: Displays the data and observes changes from the ViewModel.
### RecyclerView and LiveData
The RecyclerView is bound to LiveData from the ViewModel, which observes changes in the database and updates the list automatically.
