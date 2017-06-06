# MentorAppOA
Android Mentoring Sample App

## Description
This project is an Android Application that shows a list of GitHub subscribers and the detail of each user.

## Navigation

### Splash Screen: 
A single intro screen. It should last 3.5 seconds.

### Subscriber List Screen (Home)
For each subscriber on the list you have to show: Avatar and Username.


### Subscriber Detail Screen
This view is displayed when clicking on a subscriber. In this view you have to show: Avatar, Full Name,
Username, Company, Location, Followers and Following Counter, Repos Counter and a list of the
Repo Names.

By clicking on the user Full Name, the app has to navigate to the user profile in GitHub.
By clicking on a repo, the app has to navigate to the repo page in GitHub.

## API

The app has to implement this API to get the information of subscribers:

 https://api.github.com/repos/googlesamples/android-architecture/subscribers

Basically this is a public api provided by Github to retrieve information from repos, subscribers and followers.

## Implementation

- Respect the Java Coding Convention in your source code.
- The app should be available for Android 5.0 or higher.
- Base on your own criteria to Implement the view distribution and animations.
- Provide Activity and Fragment implementation.
- Implement an MVP architecture.
- Implement dependency injection.
- Feel free to implement any library to manage HTTP requests.
- Provide English/Spanish support.
- The app should be supported either Landscape or Portrait.
- Provide Unit Test for each functionality and, at least, one Espresso test.
- The purpose of the app and the architecture implemented in the project should be described in a Readme file.
- The initial commit in your repo must only contain the README file, any other changes on master should be managed through Pull Request and Code Review.
- Implement an Error Strategy based on Dialogs, Toasts or Snackbars that alerts/notifies unsuccessful scenarios.
