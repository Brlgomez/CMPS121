# CMPS121
Mobile Applications
Introduces programming and application development for mobile devices. Covers the SDK and main programming platforms available on mobile devices, methodologies for developing native applications, division of computation between the mobile platform and servers, and mobile-to-server communications. Introduces platforms based on JavaScript and HTML5 for the development of applications that are portable across platforms. Students develop components of applications, leading to fully functional applications by the end of the course. Course based on emulators and SDK tools, so ownership of a cellphone/tablet is not required for the course. Pre-requisite(s): Computer Science 12B and 12M. L. de Alfaro, J. Whitehead



ASG1:
For this assignment, you have to implement a game of tic-tac-toe.  On the device there need to be the following elements:
	•	On top, a TextView that can either be hidden, or contain "X won!", "O won!", or "Tie."
	•	In the middle, a 3x3 grid of buttons. 
	•	Below the 3x3 grid, a button that says "New game". 
The buttons in the 3x3 grid are initially blank / gray / ... .  When you press a gray button, alternatively symbols X and O appear on the buttons you press, allowing you to play the game (so the activity has to keep track of the turn).  Pressing buttons already containing X or O has no effect.  When three buttons in a row contain the same symbol, you highlight the row in some way, and you declare the game outcome.  Otherwise, if the grid is full but there are no three in a row, you declare a tie. 
It's up to you how to highlight the three in a row.  A possible way consists in using different images -- say, X and O with different color or background.  Another possibility is to use X and O icons that are pngs that are transparent outside of the X and O strokes, and use the background attribute of ImageButtons to set and change the background of the buttons to hilight and "normallight" the buttons. 
Also give your application a descriptive icon (the ic_launcher icon). 



ASG2:
We are going to build a simple app that acquires the location, interrogates a weather API, and reports the weather conditions for the location.
The Weather API
To get the weather, you need to issue an HTTP GET call to this URL:
http://luca-teaching.appspot.com/weather/default/get_weather
The response looks like this.  
It can be a server error (500).
It can be an application error:
 {"response": {"result": "error"}}
Or else, it can be a proper weather response, which looks similar to this:
{"response": {"conditions": {"wind_gust_mph": 0, "temp_f": 52.299999999999997, "observation_location": {"city": "High St, Santa Cruz", "full": "High St, Santa Cruz, California", "elevation": "141 ft", "country": "US", "longitude": "-122.034447", "state": "California", "country_iso3166": "US", "latitude": "36.977425"}, "temp_c": 11.300000000000001, "relative_humidity": "90%", "weather": "Clear", "dewpoint_c": 10, "windchill_c": "NA", "pressure_mb": "1025", "windchill_f": "NA", "dewpoint_f": 50, "wind_mph": 0.0}, "result": "ok"}}
(Note the "result": "ok" : this is what tells you that this response is valid).
What you have to build
You have to build an application consisting of one activity. 
When you open the activity, there is a button that says: "Get Weather Conditions". 
When you click it, it has to do the server call, get the weather conditions, and display them. 
You need to display at least 
	•	something from location (city name and elevation, at least)
	•	temperature
	•	relative humidity
	•	wind speed (average and gusts)
	•	weather (the string tagged by "weather")
You can display more if you wish.
If an error occurs, you have to advise the users with some kind of error string, and you have to let them try again to get the weather. 
That's it.  A simple assignment on doing http calls.
Please:
	•	Write your code clearly.
	•	Use the Retrofit library explained in class.
	•	Make sure the info is presented readably, does not disappear if the phone/tablet is rotated, and is scrollable so can be read on small screens.
	


ASG3:
In this assignment, you have to build an app that enables you to group chat anonymously with people nearby. 
The API
You are going to use the following server API.
Base URL: https://luca-teaching.appspot.com/localmessages/default/
post_message
Parameters:
	•	lat : latitude (float)
	•	lng : longitude (float)
	•	user_id : your user id (string)
	•	nickname : nickname to talk to other users (string)
	•	message : the message you are posting. (string)
	•	message_id : a random string id you associate with each message. (string)
Example:
https://luca-teaching.appspot.com/localmessages/default/post_message?lat=9.9993&lng=10.0004&user_id=31&nickname=Hobbes&message=Tuna&message_id=8755
{"result": "ok"}
get_messages
Parameters:
	•	lat : latitude (float)
	•	lng : longitude (float)
	•	user_id : user id (string)
This returns a certain number of local messages, in arbitrary order.
Example: 
https://luca-teaching.appspot.com/localmessages/default/get_messages?lat=9.99999&lng=10.0001&user_id=39
{"result_list": [{"timestamp": "2016-02-09T00:42:27.823020", "message": "Tuna", "nickname": "Hobbes", "user_id": "31", message_id=8755}, {"timestamp": "2016-02-09T00:41:53.328900", "message": "Holaaaa", "nickname": "PeterPan", "user_id": "38"}, {"timestamp": "2016-02-09T00:34:24.536050", "message": "Holaaaa", "nickname": "PeterPan", "user_id": "38", message_id=8755}], "result": "ok"}
Your app
You have to develop an app that behaves as follows. 
The app consists of two activities, MainActivity and ChatActivity. 
MainActivity
Hw3-Main
<div class="docs-butterbar-container"><div class="docs-butterbar-wrap"><div class="jfk-butterBar jfk-butterBar-shown jfk-butterBar-warning">JavaScript isn't enabled in your browser, so this file can't be opened. Enable and reload.</div></div><br></div>
In MainActivity, the first time, you create for yourself a random user_id and you store it in the Preferences. 
Then, you ask the user to enter a nickname, and you acquire the location. 
Once both the user has entered a nickname, and the location is known to 50m or better, you enable the Start Chat button.  When pressed, the Start Chat button stores the nickname in the app preferences, and leads to ChatActivity.
ChatActivity
Look
ChatActivity
<div class="docs-butterbar-container"><div class="docs-butterbar-wrap"><div class="jfk-butterBar jfk-butterBar-shown jfk-butterBar-warning">JavaScript isn't enabled in your browser, so this file can't be opened. Enable and reload.</div></div><br></div>
In the chat activity, at the bottom there is a place where you can post a message, and a button that you can use to refresh the list of messages.
Above, there are the various messages. You need to display the messages in chronological order (newest at the bottom), and you need to have some way of distinguishing the messages that are from you, and the messages that are from others.  For this purpose, you can compare the user_id of each message with your own user_id (which you can find in the preferences). 
Behavior
When the user clicks "refresh", you need to refresh the list of messages (using API method get_messages). 
When the user types a new message and clicks Send, you have to:
	•	Add it to the bottom of the list of messages.  ADVANCED: Use some way of denoting that it's not confirmed yet that the message has been sent (e.g., add a "sending" icon, or set the text to gray rather than black). 
	•	Send it using the API call post_message. 
ADVANCED: When the post_message call returns {"result": "ok"} you can remove the icon or text treatment you used to indicate that the sending is still pending, and you can display the sent message like all other sent messages.
