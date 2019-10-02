# architecture-sandbox

This repository contains my experiments with different architecture approaches.
Also it simulates errors on each action (with N% probabilty)

## app-rx-mvp-subject
This module demonstrates usage of RX and BehaviorSubject to implement data persistance during config changes.

## app-state-mvvm-rx

### MVVM with view state
View get be only in certain strictly defined states (in this case JustLoading, JustError and Success).

Each state can have payload that contain all the data that view needs for displaying that state.

State payload is considered immutable.

### Clean architecture

##### Interactor layer
All app logic is contained there.

##### Repository layer
All data permutations are made there. 

Repository is responsible for getting data from DataSource in DataSource's format (for exmaple backend models) and mapping it to app internal format (if it needed). 

##### DataSource layer
This layer wraps any data source, for example Retrofit service or database.
Or in case of this example - TasksProvider.
