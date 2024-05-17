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

The choice of implementing an inventory helps simplify how weapons are implemented when associated to a player,
allowing a player to choose what weapon to use and makes it easier to add and remove weapon, as now instead of
changing a parameter in the player's class, we now simply add it to the array that represents the inventory.
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

This is done for two reasons, firstly to give realism to this version of Final-reality,as it is expected that
a weapon cannot assign itself an owner, instead a player is whom chooses whether or not to have a weapon in 
their inventory. Secondly, by doing this, assigning an owner to a weapon and a weapon to an owner is more direct,
as if weapons also could do this, the instructions would go back and forth assigning themselves as owner and weapon
owned.

Weapons are divided into two, magic and non-magic weapons. The only thing that currently makes them different, is
the fact that magic weapons also have magic points, while non-magic weapons don´t. 

## II.I Weapon Types
Weapons are divided into 5 types, sword,axe and bow for non-magic weapons and wand and staff for magic weapons. Each
of these weapons have certain classes of players that can have them, for example a paladin can hold a sword, but can´t
hold a wand.

To ensure this, the double dispatch method was used, where each character class has 5 methods, one for receiving each
type of weapon (receiveSword(sword),receiveAxe(Axe)...). These functions are called when said players wants to receive
that type of weapon, where receiveWeapon calls giveToOwner in that weapon´s class, that later calls their respective
method for their weapon type. This way, the code ensures the least amount of use of isInstanceOf and ifs when giving a 
player a weapon.

## III. Party
Parties are a group of 5 players (was defined initially as 3, but for this version, parties will be allowed 
to be bigger), that will later fight in combat as a team. Currently, a party is allowed to add and remove players,
as well as checking if the party has been defeated. A party is defeated if it is either empty, or all the players
in the party have their hitpoints at 0.

## IV. Turn Scheduling
A very important aspect of Final Reality is the ability to fight between players and enemies. This turn scheduler
will take all current players and enemies ready to fight, and construct the sequence in which each turn will be
taken.

Two arrays are created to begin with. The first one is an array that holds all entities action bars during combat, that
will be crucial to checking whose turn it will be. The second one is an array of turns, which will serve as waiting 
list, sorted according to whose turn it is next. In other words, if an entity is the first element of the list, that 
means it is their turn next.

Each entity in combat has an action bar, beginning at zero that fills up when it reaches a certain value. This value
is given by:
<p align="center">maxBar = entityWeight + 0.5 * weaponWeight</p> 
To calculate this, a method is defined that stores all the maximum action bars for each active entity. For enemies, as
they cannot hold a weapon, their bar is only defined by their weight. The turn scheduler is able to add simultaneously 
to all action bars a constant value, filling up each one. 

To check whether an entity has filled up their action bar, a method compares the value in the array with the current 
amount that their bar is filled up, with their maximum bar stored in another array. Every time this method is called, 
it recalculates the maximum bar for all entities. This is done for one specific purpose. As both arrays mentioned before
should be of the same length (as both hold all entities in combat), if one character were to lose all their hp, or leave
combat, the position in the first array for each entity could change, meaning that the maximum bar for everyone used 
before a character left, would no longer be of the same length as the active bar array. As that operation should not be
so long, repeating is not very costly for the program.

Once an entity is shown to have surpassed their maximum bar, their active bar is set to 0, and added to the turn array.
This turn array is later checked by another method that simply has to remove the first entity from the array to see 
whose turn it is next.
This project is licensed under the
[Creative Commons Attribution 4.0 International License](https://creativecommons.org/licenses/by/4.0/).