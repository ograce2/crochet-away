# Crochet Away
## COMP 3717 - My Mini App

## Author
**Olivia Grace**

## Overview
Crochet Away is a mobile Android application where users can keep track of their crochet projects. The application uses a Room database to persist information between sessions and makes HTTP requests to the CSS Colors REST API so that users can add colour schemes to their projects.

## Pages and Nav Bar

### Login
The Login page has two text fields for users to enter their username and password. 

If the user clicks the login button, the database is queried for an entry in the user table that has that user name. If an entry exists and the entry's password matches the password in the text field, then the user is logged in. If the user name is not in the database or the username and password does not match, the user is not logged in.

If the user clicks the login button, the database is queried for an entry in the user table that has that user name. If an entry does not exist then a new entry is entered in the user table with the username and password from the text fields and the user is logged in. If the user name is in the database then a new user is not added and the user is not logged in.

### Home
The Home page contains a column of cards representing the user's projects. The projects are all the projects in the project table with this users user ID. Each card contains the project's name and if the project is complete, a checkmark icon. If a project card is clicked, the user is taken to the Info pgae where more information about that project is clicked. At the top of the Home page is text saying Create New Project and a plus icon. If the plus icon is clicked, the user is taken to the Create Project page.

### Create Project
The Create Project page contains four text fields for the user to enter information about their crochet project. The first three fields are for the user to enter the name, link to a pattern, and notes for the project. 

The last text field lets the user enter the name of a colour. When the user types in the name of a colour and clicks the Get Colour button, a call is made to the CSS Colors REST API for a colour with that name. If the response has status 200, i.e. a colour with that name exists, the rgb values in the response are used to display the colour in a card below the Get Colour button.

When the user clicks on the Create Project button, the information from the first three text fields are used to create a new project in the project table. The number of rows is set to 0, the start date is set to the current date, and the end date is set to null. After the new project is inserted in the database, each of the colours retrieved from the CSS Colors REST API are inserted in the project colours table with the new project's project ID. The user is then taken back to the Home page.

### Info
The Info page displays information for an individual project. It shows the name, start date, pattern link, number of rows, notes, and colour scheme for the project by querying the information from the database. 

The number of rows shows a plus icon next to it. When the plus icon is clicked, the number of rows increases, both on the screen and in the database. This allows users to keep track of what row they are on in their project. If the project is finished then the plus icon is disabled and the user can no longer increase the number of rows.

If the project is finished, the end date is not null in the database, then the end date is displayed too. If the project is not finished, then a Finished button is displayed. When the user clicks the Finished button, the end date will be updated to the current date in the database and displayed for the user and the plus button for increasing the number of rows will be disabled.

### Top Nav Bar
The app has a top nav bar created using a CenterAlignedTopAppBar that contains a house icon on the left to take users to the home page, a logout icon on the right to log users out and take them to the login page, and the name of the app, Crochet Away. When a user is on the login page, the icon buttons in the nav bar are disabled to prevent users who are not logged in from getting into the app.

## Database

The app contains a Room database to persist data between sessions. The database has three tables:
- user_table
- project_table
- project_colours

The user_id field in project_table is used to connect a row in project_table to a row in user_table by the uid field in user table.

The project_id field in project_colours is used to connect a row in project_colours to a row in project_table by the pid filed in project_table.

## API

The app makes HTTP GET requests to the CSS Colors REST API:
https://www.csscolorsapi.com/

The HTTP GET requests are made in the Create Project page using Ktor. The rgb string from the JSON response is used to display the colour to the user in a card.
