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

Package `p004`, resourse file `004.txt`, JUnit tests at `ForkliftTest.java`.

The task is pretty simple and does not require any optimization.

Had to use class inheritance instead of interface implementations for the first time.

Also added a master class for file reading because got tired of writing the same code over and over again.

# Day 5: Cafeteria

> Find the number of fresh ingridients given the ID ranges of freshness.

[Detailed description](https://adventofcode.com/2025/day/5)

Started / Finished `2025/12/07`, ~way-too-many-hours for the both parts.

Package `p005`, resourse file `005.txt`, JUnit tests at `IngredientsTest.java`.

My vision was blurry, so I spent too much time not seeing an obvious bug.

TreeMap with keys representing the beginning of each range were used for simple binary search and sorting of ranges.

# Day 6: Trash Compactor

> Apply some math to the columns of a table.

[Detailed description](https://adventofcode.com/2025/day/6)

Started `2025/12/07`. Part 1 finished `2025/12/07`, part 2 finished `2025/12/08` (by midnight).

Package `p006`, resourse file `006.txt`, JUnit tests at `TableTest.java`....

# Day 7: Laboratories

> Simulate the behaviour of an emitted beam that can split itself.

[Detailed description](https://adventofcode.com/2025/day/7)

Started / Finished `2025/12/08`, half of the day for the both parts.

Package `p007`, resourse file `007.txt`, JUnit tests at `BeamsTest.java`.

It was the most interesting task by now; my PC was going to explode from either Heap Memory exhaustion or CPU overheating.
But suddenly I decided to visit [reddit](https://www.reddit.com/r/adventofcode/comments/1pgxv5w/year_2025_day_7_no_memoization_still_runs_in_10_%C2%B5s/) and realized
that the solution is very simple and poetic, indeed.

# Day 8: Playground

> Connect all junction boxes (nodes) together by connecting the pairs of boxes based on their closed distance to each other.

[Detailed description](https://adventofcode.com/2025/day/8)

Started `2025/12/08` / Finished `2025/12/10`. 

Package `p008`, resourse file `008.txt`, JUnit tests at `CircuitTest.java`.

The problem with this task was that I didn't understand the description.

At first I tried to connect the first 10 (test) / 1000 (main) boxes to their closest pair (which isn't them and isn't connected to them), and funnily enough, the result was correct for the test case, which is a dumb coincidance.

Then, when I rewrote all the code, started from scratch and made ALL possible connections (sorting and then limitting to the 10/1000 first), it just poofed and voila: 2 hours to complete by using almost the same strategy (except that the code is more compact) and the experience from the 3 days of pain.