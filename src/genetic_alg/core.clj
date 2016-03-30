(ns genetic-alg.core)

(def max-int 1000) ; best possible chromosome is 4000
(def survivors 10)
(def population 20) ; should be even

(defn random-chromosome [] (take 4 (repeatedly #(+ 1 (rand-int 1000)))))

; [[1 2 3 4] [6 5 7 8] [123 123 123 123]]

(def fitness-funtion (partial apply +))

(defn select-survivors
  [population ff]
  (take survivors (sort-by ff > population)))

(defn crossover [x y]
  (let [pivot (rand-int (count x))]
    [(concat (take pivot x) (drop pivot y))
     (concat (take pivot y) (drop pivot x))]))

(defn next-population [selected]
  (apply concat
    (repeatedly (/ population 2) #(crossover (rand-nth selected) (rand-nth selected))))

(defn evolve [p ff pred]
  "p - is a population"
  "ff - is a fitness function that evaluates one chromosome"
  "pred - accepts best chromosome and returns true if it is sufficient"
  (loop [currentPopulation p]
    (let [selected (select-survivors currentPopulation ff)
          best (first selected)]
      (if (pred best)
        best
        (recur (next-population selected))))))

