

# Schedule Counter

---

## Table of Contents

- [Description](#Description)
- [Prerequisites](#Prerequisites)
- [Configuration](#Configuration)
- [Dependencies](#Dependencies)
- [Project Structure](#Project-Structure)
- [Getting Started](#Getting-Started)
- [API Endpoints](#API-Endpoints)

---

## Description

This is a README file for a Java Spring application that provides REST API endpoints for managing schedule.
The application is designed for the user or student to create a schedule for completing the task or work at hand. 
The user or, more precisely, the student, enters the time (hours) expected to complete the task, the deadline 
for completion. You can also mark which days and how much time you are busy. The program evaluates the user's 
busy time, sleep and rest and free time every day. If you do not specify the busy time on a certain day, 7 hours
of that day's time are allocated to complete the task. If there is not enough time to finish the work, the free 
time and sleep time are reduced. The schedule does not include weekends, Christmas, Easter, New Year. The schedule 
is saved in the database and the schedule can be viewed based on the user id.



---

## Prerequisites

- Java Development Kit (JDK) 8 or later
- Gradle build tool
- Any required IDE (e.g., IntelliJ IDEA, Eclipse, etc.)

---

## Configuration

- src/main/resources/application.properties: Contains application-specific configurations

---

## Dependencies

The project includes the necessary dependencies for building a Java Spring application. You can find the list of
dependencies and their versions in the build.gradle file.


---

## Project Structure

The project follows the standard Java Spring project structure:
- src/main/java: Contains the Java source code of the application.
- src/main/resources: Contains configuration files and resources.
- src/test: Contains the unit tests for the application.

---

## Getting Started

1. Clone the repository to your local machine:
-      $ git clone <repository_url>
2. Navigate to the project directory:
-      $ cd <project_directory>
3. Build the application using Gradle:
-      $ gradle build
4. There are a few ways how you can run the starter application:
    * Right click on _src/main/java/com/sarunas/Scheduler/Counter/application/SchedulerCounterApplication.java_ and select _Run_
    * Use `gradlew bootRun` to run the starter application.

---

## API Endpoints

The application provides the following REST API endpoints:

1. Create a new schedule:
- Endpoint: `POST /schedule`
- Request Body:
```
{
    "name": "Jonas",
    "workScope": 8,
    "completionDate": "2023-06-14",
    "busyDays": [
        {
            "date": "2023-06-08",
            "busyHours": 4
        },
        {
            "date": "2023-06-09",
            "busyHours": 6
        },
        {
            "date": "2023-06-10",
            "busyHours": 7
        },
        {
            "date": "2023-06-11",
            "busyHours": 5
        },
        {
            "date": "2023-06-12",
            "busyHours": 6
        },
        {
            "date": "2023-06-13",
            "busyHours": 6
        }
    ]
}
```

- Response: Status: 200 OK
- Body:

```
{
    "id": 1,
    "name": "Jonas",
    "workingTimes": [
        {
            "date": "2023-06-08",
            "hours": 4
        },
        {
            "date": "2023-06-09",
            "hours": 2
        },
        {
            "date": "2023-06-12",
            "hours": 1
        },
        {
            "date": "2023-06-13",
            "hours": 1
        }
    ]
}
```


2. Get work scheduler by student id:
- Endpoint: `GET /schedule/{studentId}`
- Response: Status: 200 OK
- Body:

```
  "id": 1,
    "name": "Jonas",
    "workScope": 8,
    "completionDate": "2023-06-14",
    "workingTimes": [
        {
            "date": "2023-06-08",
            "hours": 4
        },
        {
            "date": "2023-06-09",
            "hours": 2
        },
        {
            "date": "2023-06-12",
            "hours": 1
        },
        {
            "date": "2023-06-13",
            "hours": 1
        }
    ]
}
```


---