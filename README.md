# GeneralSearchAlgorithim- 

The galaxy is in the midst of a civil war. Spies for the Rebel Alliance have stolen plans from the Death Star;
a heavily armed space station capable of destroying an entire planet. Unfortunately, the rebels’ ship was 
soon captured by the Death Star’s imperial forces guarding the Death Star. Before the rebels were captured, 
they were able to hide the plans in the memory of a droid called R2-D2. It is R2-D2’s mission to try escaping its prison in the 
Death Star and deliver the stolen plans to the rebels’ planet to help destroy the Death Star once and for all. 

In this project, SEARCH is used to help R2-D2 escape. 
The prison is an m ⇥ n grid of cells. Some of the grid cells are occupied by unmovable obstacles.
Luckily for R2-D2, there is an inactivated teleportal (a device for instantaneous travel) in one of the grid cells that can take it 
to the rebel’s planet. However, R2-D2 can not use the teleportal to escape unless it is activated. To activate the teleportal, 
R2-D2 has to push big rocks onto pressure pads lying all around the grid. Each of the rocks and the pressure pads occupies a single 
cell in the grid. When all the pressure pads have rocks pushed on top of them, R2-D2 can head straight away to the activated teleportal 
to escape. R2-D2 can only move in the four directions (north, south, east, and west) and push rocks around, but it can not pass through 
or push the unmovable obstacles or the teleportal.
Using search,a plan was formulated that R2-D2 can follow to escape. An optimal plan is one with as few steps as possible.  

The following search algorithms were implemented and each was used to help R2-D2:
a) Breadth-first search. b) Depth-first search.
c) Iterative deepening search. d) Uniform-cost search.
e) Greedy search with at least two heuristics.
f) A⇤ search with at least two admissible heuristics. 

Each of these algorithms were tested and compared in terms of the number of search tree nodes expanded.
