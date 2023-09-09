# Sesjopoli serwer
This is server application of the network game inspired by Monopoly with the theme of our university. 

The role of the server is to handle connections between up to 4 players and keep state of the game

Server is created with Spring/SpringBoot

Repo of the game: [https://github.com/b4rtm/Sesjopoli](https://github.com/b4rtm/Sesjopoli)

## Rules
Our game is similar to Monopoly game but there are some features in comparison to this.

#### Rules:
- it is possible to play in 2,3 or 4 people
- player starts the game with 30 ECTS points
- player receives 3 ECTS points for passing "START" field every time
- player can buy some fields for certain amount of ECTS points
- if player enters sb's field he has to pay the owner
- if player has all fields of one color, punishment for entering this field is multiplied by 2
- after entering field "Quiz" player has to answer the study knowledge question, if the answer is correct he gets 4 points
- after entering field "Poprawka" player has to skip next round
- after entering field "Innowacja" player gets or loses random number of points
- player loses if he has 0 ECTS points
- payer wins if he is only player who did not bankrupt
- player can surrender at any time and watch the game

## Board
The board is 3D. You can rotate and zoom it.

![image](https://github.com/b4rtm/Sesjopoli/assets/97225620/d3b1128f-2558-448c-b0d2-23bf84fa1f7a)
