# Advent of Code 2025

[Wikipedia](https://en.wikipedia.org/wiki/Advent_of_Code)

Advent of Code is an annual set of Christmas-themed computer programming challenges that follow an Advent calendar. It has been running since 2015.

The programming puzzles cover a variety of skill sets and skill levels and can be solved using any programming language. Participants also may compete based on speed on private leaderboards.

The event was founded and is maintained by software engineer Eric Wastl.

# Conclusion

I have failed. The X-mas is ruined; Santa got stuck in the chimney; his sleigh were lost in the snow; the elves send everyone boxes of empty wrapping paper.

While the tasks weren't really hard, I feel really tired and can't go on anymore (even though there are still 4 days remaining until the end of the challenge).

But I am not complaining. I've had a really good time of my life during these 15 days, and I've got a lot of useful experience and knowledge too.

The challenge reminds a real job: you have a couple of new tasks each day, you have a deadline, and you are free to do whatever you want with it as long as you don't screw it up.

The summary of this challenge for me:
- The solution is usually simpler than you want it to be;
- The private access modifier exists to save your eyes when you open an autofill tool-tip in your IDE (rather than actually hiding the logic of your classes);
- Interfaces exist to save you from boiler-plating (who would've guessed?);
- Sleeping for 12 hours every second day is not that difficult;
- Coding all night after you are done with the other problems in your life is not that difficult;
- At some moment it becomes really difficult;
- Geometry isn't difficult;
- Matrices aren't difficult;
- Math is fun;
- Coding is fun.

Thanks to everyone who suggested me to participate in AoC and walked this path with me.

# Overview

- **10/12 days (20/24 tasks) have been complete in 15 real days** (`2025/12/05`-`2025/12/20`);
- Each day has its own package, unit test class and two resource files (one for the tests, the other for the actual solution);
- Contents:
    - The main code can be found in the `src/main/java/aoc` directory;
    - The unit tests can be found in the `src/test/java/aoc25` directory;
    - The main resource files (_puzzle inputs_) are in the `src/main/resources` directory;
    - The test resource files (_puzzle inputs_) are in the `src/test/resources` directory;
- Packages are named using the pattern: `p0XX`, where "XX" is the day of the puzzle;
- JUnit tests are named using the pattern: `Day0XX.java`, where "XX" is the day of the puzzle;
- The main class for each day is inside the `Main.java` script of the corresponding package;
- There are some scripts that are shared by multiple tasks, these scripts are inside the main package (`src/main/java/aoc`).

Each section of the README contains some brief overview on the specific day.

# Day 1: Secret Entrance

### `2025/12/05` 
#### ~3.5h both parts

> Find the password to the entrance from the combinations to a safe with a rounded dial.

[Detailed description](https://adventofcode.com/2025/day/1)

The number of full rotation cycles of the dial is obtained using a `while` loop (it works, right?).

# Day 2: Gift Shop

### `2025/12/06` 
#### ~1h each part

> Find the sum of all the invalid IDs using one of the validation strategies.

[Detailed description](https://adventofcode.com/2025/day/2)

To check whether an ID consists of a repeated subpart, it is simply divided
by a number that starts from 2 (two halves) and is incremented as long as the subpart
is at least 1 character long.

# Day 3: Lobby

### `2025/12/06` (_evening_)-`2025/12/07` (_night_) 
#### ~1h pt1; ~2h pt2

> Find the sum of battery bank joltages using one of the calculation strategies.

[Detailed description](https://adventofcode.com/2025/day/3)

Since the task assumes heavy calculations in order to find the highest joltage, binary search was used.

# Day 4: Printing Department

### `2025/12/07`
#### ~45m each part

> Collect all accessible rolls of paper on the map and count them.

[Detailed description](https://adventofcode.com/2025/day/4)

Had to use class inheritance instead of interface implementations for the first time.

Also added a master class for file reading because got tired of writing the same code over and over again.

# Day 5: Cafeteria

### `2025/12/07`
#### Whole day for both parts

> Find the number of fresh ingridients given the ID ranges of freshness.

[Detailed description](https://adventofcode.com/2025/day/5)

My vision was blurry, so I spent too much time not seeing an obvious bug.

TreeMap with keys representing the beginning of each range were used for simple binary searching and sorting of ranges.

# Day 6: Trash Compactor

### `2025/12/07` (day) -`2025/12/08` (midnight)
#### (time spent is unknown)

> Apply some math to the columns of a table.

[Detailed description](https://adventofcode.com/2025/day/6)

_Description is not provided._

# Day 7: Laboratories

### `2025/12/08`
#### ~8 hours both parts

> Simulate the behaviour of an emitted beam that can split itself.

[Detailed description](https://adventofcode.com/2025/day/7)

It was the most interesting task by now; my PC was going to explode from either Heap Memory exhaustion or CPU overheating.
But suddenly I decided to visit [reddit](https://www.reddit.com/r/adventofcode/comments/1pgxv5w/year_2025_day_7_no_memoization_still_runs_in_10_%C2%B5s/) and realized
that the solution is very simple and poetic, indeed.

# Day 8: Playground

### `2025/12/08`-`2025/12/10`

> Connect all junction boxes (nodes) together by connecting the pairs of boxes based on their closed distance to each other.

[Detailed description](https://adventofcode.com/2025/day/8)

The problem with this task was that I didn't understand the description.

At first I tried to connect the first 10 (test) / 1000 (main) boxes to their closest pair (which isn't them and isn't connected to them), and funnily enough, the result was correct for the test case, which is a dumb coincidance.

Then, when I rewrote all the code, started from scratch and made ALL possible connections (sorting and then limitting to the 10/1000 first), it just poofed and voila: 2 hours to complete by using almost the same strategy (except that the code is more compact) and the experience from the 3 days of pain.

# Day 9: Movie Theater

### `2025/12/10`-`2025/12/12`

> Given a polygon (made of 1x1 tiles) find two largest areas formed by rectangles made of pairs of tiles:
> one is the largest possible, the other is the largest inner (i.e. inside the polygon).

[Detailed description](https://adventofcode.com/2025/day/9)

- Good news that I knew the theoretical solution from the first day (it is related to CW/CCW turns of the corners);
- Bad news is that I started thinking it is not the correct solution (you can find the `Raycasting` approach in repo history; that didn't work either);
- Good news is that I realized it actually was the correct solution;
- Bad news is that I'm bad at math and couldn't calculate the cross product for two days;
- Good news is that **now I know the formula of the cross product by heart**;
- And this task was cool, too.

# Day 10: Factory

### `2025/12/12`-`2025/12/19`

A few words about this task as the preview:

- I was really exhausted and didn't understand what I'm doing (_hence the weird class/method names_);
- The task wasn't really difficult;
- I would still never knew that the task is not difficult if not [this](https://www.reddit.com/r/adventofcode/comments/1plzhps/2025_day_10_part_2_pivot_your_way_to_victory/) reddit post;
- The task would be a lot easier if I had at least some basic concentration for it;
- At some moment my sleep pattern switched to 36 hours awake / 12 asleep;
- After I was done with the matrix, the puzzle was basically solved; but the matrix code had several bugs, and I needed about 4-5 days to understand that the real problem is the matrix itself and not the way I solve the task.

Now, some documentation is probably desired to be provided:

- `MachineFactory` is a weird pun; it is _the factory where the elves work with some machines_, but it is also a Factory pattern that parses the information about the `Machine`s from a file and produces the required objects;
- The `Elf` works at this factory; the poor guy presses every button possible in numerous, very absurd ways using the `tryToStartEmMachines()` and `tryToFixEmJoltages` methods; it is a way to make the lower level code more distant from the higher level code, since I had to rewrite the low-level implementation several times and didn't want to change all of the other code for this reason only (this way I only needed to update the `Elf` class);
- The `factory` package has some `Machine`s in it:
    - A `Machine` is an abstract class, a data object, that has an abstract `solve()` method for a specific part of the task; it is absolutely unnecessary, I might've simply move all the logic outside (but I haven't);
    - An `IdleMachine` is a machine that must be started (part 1);
    - A `StartedMachine` is a machine that must be configured (part 2);
- The `solvers` package contains the logic for the problem solving.

About the _solvers_ in particular:

- The `BitmaskSolver` is for the part 1; since this part is not really hard (I finished it in an hour), not much can be said about it except that the approach is almost the peak of absurdity;
- The `JoltageSolver` is for the part 2, and it depends on some other sub-solvers from the `matrix` package:
    - The `Matrix` is the reason I needed to spend so much time on the task; while the way it works is absolutely straightforwrad, I had serious (and weird) issues with RREF transformation;
    - The `Matrix` also has a method to check if there aren't any free variables in it, it is the `checkObviousSolution()` method;
    - And the `print()` method too, for debugging (a life saver);

The other classes for part 2 include: 

- `MatrixBruteforce` bruteforces the matrix. For bruteforcing it uses...
- `Solution`. This class checks if the passed values of free variables are valid. And calculates the solution for the machine if they are valid. And to get these exact values of the variables we use...
- `SolutionIterator`. While the `BitmaskSolver` is _almost_ the peak of absurdity, this iterator **is** the actual peak. The task assumes that we must write a **Depth-First Search (DFS) engine**; but instead I just write an iterator to iterate over all of the possible values. It works the same way as a DFS, but: no recursion needed; no `Stack<T>`s used; only a `for-each` loop; surprisingly fast. And it is pretty similar to the `BitmaskSolver` too.

.

.

Don't scroll down here, nuffin' to look at.