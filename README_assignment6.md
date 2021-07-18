@author: Varun Kashyap
FileName: README.md
Date: 07/12/2021
Description: README for User Registration, assignment6, describing the application and how to deploy it on the WildFly server.

This submission contains a README, a WAR file containing all SRS application contents with proper directory structure,
and screenshots from application deployment.

How to run the application on the WildFly server:

First start the WildFly server:
1) From your command-line interface, change to your WildFly directory. 
2) Change to the bin directory.
3) Run the following command to start WildFly: $./standalone.sh &
4) To see if WildFly has started, go to your desired browser and type in: http://localhost:9990/console. Type in your login if requested.

Connect to WildFly server:
1) Within the same WildFly bin directory, type in: $ ./jboss-cli.sh
2) Once started, type: $connect

Build and deploy Web App:
1) Change into your web app directory
2) Type command: $ mvn wildfly:deploy
3) Go to: http://localhost:9990/console -> Deployments -> VKashyap_assignment6
4) Click on web app link to execute

ADMIN LOGIN CREDENTIALS:
UserId: adminLogin
Password: password