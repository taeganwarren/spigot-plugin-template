# Developing a "Hello World" Spigot Plugin with Intellij for a Minecraft Server
Note: This guide was written for Windows 10 and uses JDK 12.0.2 and assumes development for Minecraft 1.14.4

## Installing required software and dependencies
- Installing JDK
    - From the Oracle website, download and install the latest version of JDK/JSE (12.0.2 at the time of writing).
    https://www.oracle.com/technetwork/java/javase/downloads/index.html
- Installing Git Bash
    - From the Git website, download and install the latest version of Git (Version 2.22.0 at the time of writing)
    https://git-scm.com/downloads
- Installing Intellij
    - From the Jetbrains website, download and install the latest version of the Jetbrains Toolbox.
    https://www.jetbrains.com/toolbox/
    - Once installed, use it to install the latest version of Intellij.
    - Alternatively, download and install the latest version of Intellij by itself.

## Ensuring your Java installation is set up correctly
- Open up Git Bash and enter the command:
    ```
    java -version
    ```
    - If the output matches the version of the JDK you just installed (12.0.2 in my case), proceed to the next section, "Setting up a test server". If the output does not match, continue reading this section.
- Open up your start menu and type in "Edit the system environment variables", and open up the matching control panel shortcut.
- Click the "Advanced" tab, then "Environment Variables" at the bottom.
- On the bottom half of the Environment Variables window, under "System Variables", click the "Path" variable, then click "Edit".
- Click on "New" and enter in the location of your JDK installation. In my case, this location was "C:\Program Files\Java\jdk-12.0.2\bin".
- Once this is entered, you can click "Ok" at the bottom and close out of the related Environment Variables windows.
- Re-open Git Bash and type in the same command from above. You should now see the correct version of the JDK you installed.

## Setting up a test server
Note: This test server will only be used by you, locally, for development and testing purposes. There is no need to port forward the server or allow access to it by anyone else.
- Building Spigot
    - Download the latest BuildTools.jar from the Spigot website.
    https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar
    - Place the BuildTools.jar into a new directory where you will be building the jars. I recommend a folder called "Spigot Jars" on your desktop.
    - Open up Git Bash and navigate to that directory.
    - Execute the following command:
        ```
        Java -jar BuildTools.jar --rev 1.14.4
        ```
        - This will take a few minutes as it builds the 1.14.4 version of the spigot jar.
- Starting the server
    - Place the newly created spigot-1.14.4.jar file into a separate folder where you will be running the server from. I recommend a folder called "Spigot Test Server" on your desktop.
    - Create a new text file in the directory with the following contents:
        ```
        @echo off
        java -Xms1G -Xmx1G -XX:+UseConcMarkSweepGC -jar spigot-1.14.4.jar
        pause
        ```
        - Click "File" and then "Save as". Name this file "start.bat"
    - Double click the file to execute the script
        - This will create a few new files in the directory related to running the test server, including a file called "eula.txt". When instructed by the output from the script, open the "eula.txt" file and change "false" to "true". Save and close the file. Once this is finished, re-run the "start.bat" script. This will create a few more files and start up the server.
        - You can then open up Minecraft, go to Multiplayer, Direct Connect, and enter in "localhost". This will connect you to your new test server.
    - Go back to your terminal where the server output is shown and type in "stop" and hit enter. This will shut down the server. You can then close the terminal window, and the Minecraft window.
        
## Setting up your environment

## Creating the "Hello World" plugin

## Building and running the plugin