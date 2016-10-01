##Contest Finder App. 

Mobile App to help the programmers find and 
keep track of upcoming coding competitions online. The Application makes use of IBM's Bluemix MobileFoundation to Bootstrap a quick UI which lists all contests base don 3 categories : 

 ### Upcoming contests
 ### Ongoing  contests
 ### Previously missed contests.

 The Application will send the user a daily notification of present day's contests.

 The details of all past contest are stored on Cloudant NoSQL DB.

 The backend service will fetch upcoming contest details via a third party API such as https://contesttrackerapi.herokuapp.com/  and update the data store.

 The mobile will help the busy developers keep track of their contest and make sure they never miss any practice to win and improve their skills.