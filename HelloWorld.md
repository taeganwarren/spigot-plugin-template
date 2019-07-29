# Developing a "Hello World" minecraft Spigot Plugin with Intellij
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
        ```Bash
        Java -jar BuildTools.jar --rev 1.14.4
        ```
        - This will take a few minutes as it builds the 1.14.4 version of the spigot jar.
- Starting the server
    - Place the newly created spigot-1.14.4.jar file into a separate folder where you will be running the server from. I recommend a folder called "Spigot Test Server" on your desktop.
    - Create a new text file in the directory with the following contents:
        ```Batch
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
- Open up Intellij.
- Go to "File", "New", "Project".
- Select "Java" on the left side menu.
- Select the Java SDK you installed in the "Project SDK" dropdown menu (12.0.2 in my case), then click "Next" at the bottom right.
- Leave the "Create project from template" checkbox blank, and click "Next".
- Enter in the name you would like your plugin to be in the "Plugin Name" box (I named mine "HelloWorld"). Click "Finish" in the bottom right.
- Use "Ctrl + Alt + Shift + S" to open the "Project Structure" window. Then navigate to "Modules" under the "Project Settings" section on the right side.
- Click on the "Dependencies" tab.
- Click on the plus sign button on the right side, then click "JARs or directories...".
- Navigate to where your recently built "Spigot-1.14.4" jar file is located (In my case, this is in the "Spigot Jars" folder I made on my desktop).
- Once you have it selected, press "Ok". Then press "Apply", then "Ok".
- Expand the "External Libraries" section in the project window. You should see the Spigot jar file you just added, and the Java SDK you selected when setting up the project.

## Creating the "Hello World" plugin
- In the same project window, expand the project folder. You should see a folder called "src".
- Right click on this folder, go to "New", then "Package".
- Call this package something along the lines of "me.[your name].[project name]", and then click "Ok".
- Right click on the package folder you just created, go to "New", then "Java Class", and name it "Main".
- Paste in the following code:
    ```Java
    package me.[your name].[project name];

    import org.bukkit.command.Command;
    import org.bukkit.command.CommandSender;
    import org.bukkit.entity.Player;
    import org.bukkit.plugin.java.JavaPlugin;

    public class Main extends JavaPlugin {

        @Override
        public void onEnable(){
            //Fired when the server enables the plugin
        }

        @Override
        public void onDisable(){
            //Fired when the server stops and disables all plugins
        }

        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (label.equalsIgnoreCase("hello")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage("Hello world!");
                    return true;
                }
            }
            return false;
        }

    }
    ```
  - Be sure to change the first line of the code to match what you called your package.
  - Code explanation:
    - The "onEnable" and "onDisable" functions are required by all plugins. "onEnable" is ran when the plugin is enabled such as on server start up or reload. "onDisable" is ran when the plugin is disabled, such as when the server is shutting down.
    - The "onCommand" function is ran when a player initiates a command on the server. In this case, it checks if the command initiated is "/hello". If so, it then responds to the player with "Hello world!".
- Right click on the "src" directory again, go to "New", then "File". Name it "plugin.yml".
- Paste in the following code"
  ```yml
  name: [your plugin name]
  version: 1.0
  author: [your name]
  main: me.[your name].[project name].Main

  commands:
    Hello:
  ```
- Fill in the fields with what you called your project and package
  - Note: these fields are case sensitive so type them in exactly as they appear in your project.

## Building and running the plugin
- Use "Ctrl + Alt + Shift + S" to open the "Project Structure" window. Then navigate to "Artifacts" under the "Project Settings" section on the right side.
- Click the plus button, go to "JAR", then "From modules with dependencies...".
- Make sure the Module is the same as your project name, then click "Ok".
- Click on "Apply" then "Ok" on the bottom right of the window.
- At the top of the Intellij window, go to "Build" and then "Build Artifacts", then click "Build" again on the window that appears.
- Navigate to the output folder for your project (In my case, this was at "C:\Users\my_name\IdeaProjects\HelloWorld\out\artifacts\HelloWorld_jar"). Yours should be similar, unless changed. You can check this by going to the "Project Structure" window using "Ctrl + Alt + Shift + S" and clicking on "Project".
- Once your have the compilied .jar file from your project, copy it into the "plugins" folder in your test servers directory, and start your test server up.
- Open up Minecraft, connect to your test server, and execute the /hello command to see the "Hello world!" output.
- Congratulations! You just sucessfully created your first spigot plugin!