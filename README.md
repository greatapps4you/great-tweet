# great-tweet
Twitter like Social Network

#Building
1 - Build the great-tweet-entities project with maven
    clean install

2 - Build the great-tweet-features project with maven
    clean install

3 - Build the great-tweet-application project with maven
    clean package

#Running
Run the resulting executable Jar from great-tweet-application from the command line:

java -jar great-tweet-application-1.0-SNAPSHOT.jar

#Posting Messages

Using Postmann or curl send a POST request to the URL:
localhost:8080/api/posting

Having the posting user as body. For example:
{"user":"josethedeveloper","tweet":"This is my First great tweet"}

And don't forget to set the Content-Type header to application/json

The Application is perfectly RESTful so you will get something like this:

{
    "id": 1,
    "content": "This is my First great tweet",
    "publicationTime": "2020-11-04T01:53:00.91446",
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/tweets/1"
        }
    }
}

Feel free to go to your browser with the provided resource link.
http://localhost:8080/api/tweets/1

# Checking the Wall

Since this is a GET request you can use your browser:

http://localhost:8080/api/wall/josethedeveloper

in the above example josethedeveloper is the id used to post a previous message.
Example Response:

{
    "_embedded": {
        "tweetList": [{
            "id": 1,
            "content": "This is my First great tweet",
            "publicationTime": "2020-11-04T01:53:00.91446",
            "_links": {
                "self": {
                    "href": "http://localhost:8080/api/tweets/1"
                }
            }
        }]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/wall/josethedeveloper"
        }
    }
}

# Following an User
Assuming:
1 - You and the user you want to follow have posted messages before,
thus have been created

Send a POST request with Postmann or curl to the Following URL:

localhost:8080/api/following/emma

In the above example emma is a user that has posted a message before.
Send your user in the body request. For example:

{"uniqueName":"josethedeveloper"}

If sucesful you get back the user you followed like this:

{
    "id": 2,
    "uniqueName": "emma",
    "name": "emma",
    "following": [],
    "tweets": [{
        "id": 2,
        "content": "I am an actress",
        "publicationTime": "2020-11-04T02:02:22.79158"
    }],
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/users/emma"
        }
    }
}

# Checking your timeline

Using your browser send a GET request to the Timeline URL
pretty much like you did with the Wall:

http://localhost:8080/api/timeline/josethedeveloper

Again, josethedevelopler is my user. 
You will get all the messages of people you followed in descending order by
publication time. Sample Timeline Response:

{
    "_embedded": {
        "timelineTOList": [{
            "uniqueName": "john",
            "name": "john",
            "publicationTime": "2020-11-04T02:21:22.908749",
            "content": "I am posting again",
            "_links": {
                "self": {
                    "href": "http://localhost:8080/api/tweets/4"
                }
            }
        }, {
            "uniqueName": "emma",
            "name": "emma",
            "publicationTime": "2020-11-04T02:02:22.79158",
            "content": "I am an actress",
            "_links": {
                "self": {
                    "href": "http://localhost:8080/api/tweets/2"
                }
            }
        }]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/wall/josethedeveloper"
        }
    }
}


Happy Tweeting!




