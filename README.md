#  World Cup Score Board
Library which represents logic for Football Score Board and supports several features

## Features
1. Start a new game with intial score 0-0. It's impossible to start a game with invalid home/away teams names. I assume that this is one of edge cases and it has to be from 4 to 20 characters and could contain spaces.
2. Remove a game from the board
3. Change score for Home/Away team separately. That means that each match represents sequential stream of events, where events are represented by scoared goal. We can't jump from 0-0 to 0-3 or 2-5 in one single moment, that means that for each scored goal corresponding method for home/away team score must be invoked. This approach helps to avoid necessity to validate score for negative values/inconsistencies.
4. Show total statistis ranked by total scored goals, which means that top scored games would be on the top of the Board. If total score is same for several matches, then on the top would be the most recently added.

## Notes
1. To run code start MatchServiceImplTest.java
2. TDD approach was followed up during development
3. Some edge/unhighlighted parts were handled such as invalid home/away teams names, inconsistent game score change, removal match by invalid match id, updating score by invalid match id.
