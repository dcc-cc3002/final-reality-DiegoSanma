# Final Reality

Final Reality is a simplified clone of the renowned game, Final Fantasy. Its main purpose is to
serve as an educational tool, teaching foundational programming concepts.

This version of Final Reality, shall implement various aspects of Final Fantasy, but also add 
different ways and methods to implement actions and situations

## I. Entities
An entity in Final Reality are whom will take part primarily in the game. This class will be used mainly to 
group the two main counterparts of the game, playable characters and enemies. In this case, all entities are 
defined by their name, hitpoints, defense and weight. Not including attack as a parameter is later explained 
when weapons are taken into consideration. Also implements main getters for the parameters mentioned before.

## I.I Playable Characters
A player will be allowed to choose from a variety of different classes, that can be separated into two 
categories, Mages and Characters. Each one of them will be restricted to maximum values to their stats,
different for each class, to ensure that different players with different classes specialize in different 
aspects during combat.
These players will also have an inventory, that begins empty as well as an active weapon, also empty. The
inventory of a playable character serves the purpose of holding weapons, a maximum of three. The fact that a
weapon is currently in a player´s inventory does not necessarily mean the player´s using said weapon.
### I.I.I Characters
This category of player can be separated will be separated into three classes, paladins, guerreros(warriors)
and ninjas. In particular, guerreros will be heavy hitters with higher defense, at the cost of weighing more 
than the rest, while ninjas will be lighter, but with less attack and defense. Paladins will be considered an
option for an all round good character.
### I.I.II Mages
This category divides into two, white and black mages. What makes them different from other characters, is the
use of mana and magic/ an aspect of the game that will be implemented later on. 
For mages, the main difference between them is that white mages have a higher maximum mana, while black mages
have higher attack and defense.

## I.II Enemies
Enemies add to entities the attack parameter, but nothing else.

## I.III Attacking
Every entity will have the capability of attacking another entity, no matter if they are both enemies, both 
playable characters or different. When attacking, the amount of damage done to an entity is given by:
<p align="center">damage = attack - defense</p> 
For enemies, this attack parameter is already known, so that is the one that is used in this case. Nevertheless,
for players, their attack will be given by the current weapon they are holding (explained later). If a player is 
not holding a weapon, once they attack, no damage will be done and they will lose their turn, so be careful when
attacking!
Important to mention, as an entity´s hp cannot go lower than 0, if the damage they took is greater than their
current health, that value will be left at 0.

## II. Weapons
Weapons can only be held by playable characters, not enemies. Weapons can only be named once, and that name
remains permanent forever. A weapon has a weight and attack points, which will serve as the parameter to be used
when a player inflicts damage in combat.
A weapon may also have an owner, which means it is in that person´s inventory. When a weapon is dropped or added
to someone´s inventory, this must be done ONLY from the player´s methods of dropping or adding a weapon. In other
words, a weapon can´t be assigned an owner directly, only characters and mages are the ones capable changing the
owner.
Weapon´s are divided into two, magic and non-magic weapons. The only thing that currently makes them different, is
the fact that magic weapons also have magic points, while non-magic weapons don´t. 

## III. Party
Parties are a group of 5 players (was defined initially as 3, but for this version, parties will be allowed 
to be bigger), that will later fight in combat as a team. Currently, a party is allowed to add and remove players,
as well as checking if the party has been defeated. A party is defeated if it is either empty, or all the players
in the party have their hitpoints at 0.

## IV. Turn Scheduling
A very important aspect of Final Reality is the ability to fight between players and enemies. This turn scheduler
will take all current players and enemies ready to fight, and construct the sequence in which each turn will be
taken.
Each entity in combat has an action bar, beginning at zero that fills up when it reaches a certain value. This value
is given by:
<p align="center">maxBar = entityWeight + 0.5 * weaponWeight</p> 

For enemies, as they cannot hold a weapon, their bar is only defined by their weight. The turn scheduler is able to
add simultaneously to all action bars a constant value, filling up each one. Once a bar is full, the turn scheduler 
take the entity that filled their action bar, empty it and put him in line waiting for their turn to be had.
To find whose turn it is next, the turn scheduler simply takes the first entity in line, removes him from it, and 
informs the user who that is.

This project is licensed under the
[Creative Commons Attribution 4.0 International License](https://creativecommons.org/licenses/by/4.0/).