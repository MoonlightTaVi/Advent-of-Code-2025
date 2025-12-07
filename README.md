# Advent of Code 2025

[Wikipedia](https://en.wikipedia.org/wiki/Advent_of_Code)

Advent of Code is an annual set of Christmas-themed computer programming challenges that follow an Advent calendar. It has been running since 2015.

The programming puzzles cover a variety of skill sets and skill levels and can be solved using any programming language. Participants also may compete based on speed on private leaderboards.

The event was founded and is maintained by software engineer Eric Wastl.

# Day 1: Secret Entrance

> Find the password to the entrance from the combinations to a safe with a rounded dial.

[Detailed description](https://adventofcode.com/2025/day/1)

Started / Finished `2025/12/05`, ~3.5 hours.

Package `p001`, resourse file `001.txt`, JUnit tests at `SafeTest.java`.

The number of full rotation cycles of the dial is obtained using a `while` loop (it works, right?).

# Day 2: Gift Shop

> Find the sum of all the invalid IDs using one of the validation strategies.

[Detailed description](https://adventofcode.com/2025/day/2)

Started / Finished `2025/12/06`, ~1 hour each part.

Package `p002`, resourse file `002.txt`, JUnit tests at `IdValidation.java`.

To check whether an ID consists of a repeated subpart, it is simply divided
by a number that starts from 2 (two halves) and is incremented as long as the subpart
is at least 1 character long.

# Day 3: Lobby

> Find the sum of battery bank joltages using one of the calculation strategies.

[Detailed description](https://adventofcode.com/2025/day/3)

Started `2025/12/06` (evening) / Finished `2025/12/07` (night). 

~1 hour for the 1st part; ~2 hours for the 2nd part.

Package `p003`, resourse file `003.txt`, JUnit tests at `BatteryTest.java`.

Since the task assumes heavy calculations in order to find the highest joltage, binary search was used.

# Day 4: Printing Department

> Collect all accessible rolls of paper on the map and count them.

[Detailed description](https://adventofcode.com/2025/day/4)

Started / Finished `2025/12/07`, ~45 minutes each part.

The task is pretty simple and does not require any optimization.

Had to use class inheritance instead of interface implementations for the first time.

Also added a master class for file reading because got tired of writing the same code over and over again.
