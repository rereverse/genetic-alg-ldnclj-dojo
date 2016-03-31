# genetic-alg

Maximizing addition of N elements using simple genetic algorithm.

A exercise proposed on a Clojure Dojo organised at Thoughtworks on
29/03/16 in London.

If you want to continue where the group left off checkout the branch
end-of-dojo, master branch contains finished solution.

## Usage

To run the example:

lein uberjar
java -jar target genetic-alg.jar

## Problem

The algorithm maximizes sum of 10 variables.
Each variable can hold a value between 1 and 100 000, therefore
the best possible solution is 1 000 000. The algorithm stops when
solution higher than 999 000 is reached.

This particular problem can be solved analytically easily. The goal
of this project is to exercise Clojure.

## Genetic Algorithms

Resource that helped me personally learn about GA and GP is:
http://www.obitko.com/tutorials/genetic-algorithms/index.php

There is a lot of other resources around the web. Good place to start
would be the web page of GA and GP inventor - John Koza:
http://www.genetic-programming.com/

## License

Copyright © 2016 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
