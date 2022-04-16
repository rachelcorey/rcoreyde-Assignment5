## Introduction

My RPG simulator will be a robot-themed dungeon crawler
- 3 different classes
- 4 different specifications (“races”)
- There will be no shop, equipment, or money implemented in this design.
- There will be basic items and basic status effects.
- The player will traverse through 5 dungeons.
- Each dungeon consists of 10 floors.
- Each Boss on the 10th floor drops a unique item of varying strength and sometimes more items.
- Players who lose all of their HP are sent back to the first floor of the dungeon they died in.

## Chosen Requirements & Their Respective Chosen Design Patterns

### Observer:
- Potions should never give more Health or Mana than you have as a maximum.
- After each floor (and after each possible random encounter with a shop) you should go back to the top floor if your health is below 15%. Going to the top floor gives you back all of your health and mana
- During combat:
  - Attacks and item usage should happen in a turn based fashion, the participant with the highest speed should go first.
  - When a participant reaches 0 or less HP, they lose the fight, if the loser is you, you lose an amount of money  **(CHANGING THIS)** you lose all your items and go back to the top floor and only gain 20
  - During either participants turn, they can either attack or use a consumable item.

### Factory Method:
- You must be able to choose a class for your character (or have it assigned randomly), each class has a different subset of skills or magic. A class would constitute something along the lines of ’fighter’, ’mage’, ’thief’, get creative with it!
- You should be able to choose a type for your character (or have it assigned randomly), each race should have one passive skill that gets better after leveling up or that gives them advantage over another. rate A type would constitute something like, ’orc’,’elf’, ’dwarf’, ’human’. Perhaps orcs are stronger naturally, perhaps elves are the most nimble and can dodge better, maybe humans have less HP but gain exp at a faster, get creative with it!
- Your character should have at minimum:
  - Stats: Attack, Health, Mana/Other Resource such as cooldown, Defense, and speed. Others can be added.
  - Should have at least 1 skill or magic power they can use
  - Physical attacks should have a chance to critically strike (double damage)
  - All attacks should have a chance to fail/miss
  - Attacks should deal a min damage of 1

### Builder:
- You should encounter a randomly generated ’small’ enemy every floor, a ’medium’ enemy every 5 floors, and a ’BOSS’ every 10 floors.
- All enemies should get harder for each floor you travel to, for example, an enemy on floor 2 should be much, much weaker than an enemy on floor 48!
- Your simulation should include at least 3 temporary status effect and at least 1 permanent status effect that either hinders or helps your character in some way. Status effects could include: poisoned, burned, asleep, paralyzed, strengthened, quickened, crippled, bleeding, curse


## Needed Classes

#### Entity
  - Stats
    - Level (stat modifier)
    - HP
    - Attack
    - Speed
    - Special Resource
    - Exp (Player only)
  - Player
    - Player Classes
      - RoboGolem
        - Resource: Steam
        - Physically strong
        - Physical Attacks get stronger w/ level up
        - Weakness: Is slow
      - RoboHacker
        - Resource: Quantum Ions
        - Can hack enemies
        - Hacks do more passive damage w/ level up
        - Weakness: Has low HP
      - Nanobots
        - Resource: Magnetic Cohesion
        - Control each bot on turn to either attack or build another nanobot
        - Each nanobot needs to be destroyed to lose HP, more HP and damage for each nano w/ level up
        - Weakness: AOE attacks are strong against them
    - Player Types
        - HoverBot
          - Is faster
        - CapacitorBot
          Has extra resource
        - TungstenBot
          Has extra HP
        - RadioactiveBot
          Has stronger attacks
  - Enemy
    - Small, Medium, Boss

#### Dungeon
- Number (Enemy strength modifier)
  - Floor
  - Number (Enemy strength modifier again)
  - Enemy (Entity)
  - Small, Medium, Boss


#### Item
  - Stats
    - Level modifier
    - Duration
    - Amount
  - Repair Kit (Heal) (Cannot use at max hp)
  - Power Conduit (Temporary Power Up 1-5 battles)
  - Upgrade Module (Level Up 1-5 levels, Only dropped by Bosses)
  - Grappling Claw (Escape from battle and advance 1-5 floors) (Cannot use on boss)


#### Skills
  - Stats
    - Damage amount
    - number of hits
    - % to miss
    - number of turns to complete
    - number of turns it lasts
    - type of Status Effect (can be NONE)
  - RoboGolem: Physical Attack
    - Heavy blow (high single target dmg)
    - Multi-slash (low dmg multiple attacks w/ chance of up to 5 consecutive attacks)
  - RoboHacker: Hack Enemy
    - Slow hack (2 turns, lasts the rest of the battle)
    - Fast hack (1 turn, lasts 2 turns max)
  - Nanobots: Build more nanobots
    - Build one (1 turn)
    - Build three (2 turns)
  - Enemy Skills
    - Heavy blow (high single target dmg)
    - Shrapnel shot (AOE dmg)
    - Salt Spray (inflicts rust DOT)
    - Hack (inflicts stun and DOT)

  - Status Effects
    - Rust (Poison/DOT)
    - Hacked (Stun & DOT)
    - Charged Up (Extra resource)
    - Lucky (Extra money and items dropped)
