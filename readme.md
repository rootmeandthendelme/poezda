# Trains

## Database
![alt text](https://github.com/rootmeandthendelme/poezda/blob/main/dbimg.png?raw=true)
database init script - src/main/resources/database/init.sql

## Endpoints

## Get trips
- **Method** - GET
- **Path** - /trips/search

### Params
startPoint - name of the departure location\
endPoint - name of the arrival location\
trainName - name of the train (optional)\
date - date of the trip (optional)

## Login 
- **Method** - POST
- **Path** - /login

### Body
    {
        "login": string,
        "password": string
    }

## Reserve seat on the trip
- **Method** - POST
- **Path** - /reservation
- **Authorization** - bearer token

### Body
    [
        {
            "seatId": number,
            "startPointId": number,
            "endPointId": number,
            "passport": string,
            "fullName": string
        },
        {..}
    ]

## Delete reservation
- **Method** - DELETE
- **Path** - /reservation/{id}
- **Authorization** - bearer token
