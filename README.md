# tic-tac-toe-javafx

## Description
This is a GUI Tic-Tac-Toe game written in Java using JavaFX. It has 3 different 
game modes. a user can play with a computer, another person locally, or another 
person on another computer.

#### Playing Against the Computer
You will be able to enter a name for yourself. A game will then be started up.

#### Playing Against Another Person Locally
You will be able to enter both your names, and who is which piece (X or O). A
 game will then be started up.
 
#### Playing Against Another Person on Another Computer
You will be able to enter your name. A window will then pop up that lets you 
connect to someone else by entering their host and port. That person will be 
seeing the same screen. Whoever correctly enters the other's host and port 
first is player 1 (This is done through UDP). A game is then started where 
moves are sent through TCP where updates to your UI happen the second your 
opponent makes them.
#
#
In all of these game styles, whoever hits 20 wins first wins the it all. 
After this, you are taken back to the initial screen where you can choose 
which game style you want to play as.



## Purpose
This project will help me integrate my knowledge of javaFX and networked 
applications in Java. 

#### Networked Applications
I recently took Concepts of Parallel and Distributed Systems, and want to 
solidify my knowledge of TCP and UDP applications. 

#### JavaFX
I want to experiment with some interesting parts of javaFX like:
- Having multiple windows on the screen at once
- Same FXML file, different controller
- 

## Design Patterns
#### Peer to Peer Networked Model
#### Model View Proxy Networked Model
#### Model View Controller GUI Model
