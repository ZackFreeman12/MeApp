# Dating App Demo For Client

MeApp was a dating app prototype made to specification for a client. 
It was built in Java using Android Studio, implementing a user database and proflies with SQLite. 
Fully functional SMS chat between users was implemented using the ChatStream library. 
MeApp has since then been utilized by a happy client to pursue their future endeavors with investors.



# INSTALL INSTRUCTIONS

Zip file provided is the current main full build

Android Studio Application Setup - Me App

https://developer.android.com/studio 
Open Android Studio click the Tools tab and open the AVD Manager (Tools > AVD Manager).

Select the ‘+ Create Virtual Device…’ button, select Phone under Category, and the Pixel 2 Device and then click ‘Next’.

Select ‘Pie’, API Level ‘28’ and Target ‘Android 9.0 (Google Play)’. Click ‘Next’.

Change the AVD Name to whatever you like or leave it as default, ‘Pixel 2 API 28’. Click ‘Finish’.

Download the Application Code from https://github.com/ZackFreeman12/MeApp 

In Android Studio, click the File tab in the top left corner and click open (File > Open…).

Locate the Application code that was recently downloaded. Select the file and click ‘OK’.

To run the application, in the top left corner, make sure the device that was created in the AVD Manager is selected then click the green play button icon. Otherwise follow steps 2-5 again.		

The application should now be running. Please take a look at the ‘Set up Stream API’ before continuing further.

Stream API Key Setup - Free Version

Go to the Stream website : https://getstream.io/chat/ 

Create a free trial account.

Go to your Dashboard.

Underneath the ‘DEVELOPMENT APP’ select the application name.

In the top right corner select ‘App Settings’. And click ‘Edit’ (App Settings > Edit).

Make sure ‘Environment’ has been set to ‘Development’. Click ‘Save App’.

Scroll down and underneath ‘Authentication’, enable ‘Disable Auth Checks’ and enable ‘Disable Permissions Checks’.

Go back to application page we landed on after clicking ‘Dashboard’

Underneath ‘DEVELOPMENT APP’ once again, copy the ‘Key’
.
In Android Studio, open the application’s code.

On the left side with the Hierarchies of folders. Starting from the root folder ‘app’, open ‘src’, then open ‘main’, then ‘res’, then ‘values’, and finally click open the ‘strings.xml’ file (app > src > main > res > values > strings.xml).

In the ‘strings.xml’ file, look for the “api_key” and change the old key to your new, copied key.

Launch or re-launch the application.

