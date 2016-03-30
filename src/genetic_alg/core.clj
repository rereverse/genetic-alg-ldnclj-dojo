(ns genetic-alg.core
  (:require [clojure.pprint :refer [pprint]])
  (:gen-class))

(def population-size 20)                                    ;; has to be even (simplification)
(def survivors-size (/ population-size 2))
(def chromosome-len 10)
(def max-gene-value 100000)
(def mutation-prob 0.1)

(defn rand-boolean [probability-true]
  (>= (rand) (- 1.0 probability-true)))

(defn rand-gene [] (inc (rand-int max-gene-value)))
(defn rand-chromosome [] (repeatedly chromosome-len rand-gene))
(defn rand-population [] (repeatedly population-size rand-chromosome))

(defn select-survivors
  [population fitness-f]
  (take survivors-size (sort-by fitness-f > population)))

(defn crossover [x y]
  (let [pivot (rand-int chromosome-len)]
    [(concat (take pivot x) (drop pivot y))
     (concat (take pivot y) (drop pivot x))]))

(defn mutate-chromosome [chromosome]
  (assoc (vec chromosome) (rand-int chromosome-len) (rand-gene)))

(defn mutate-population [population]
  (map #(if (rand-boolean mutation-prob) (mutate-chromosome %1) %1) population))

(defn next-population [selected]
  (->> (repeatedly survivors-size #(crossover (rand-nth selected) (rand-nth selected)))
    (mapcat identity)
    (mutate-population)))

(defn evolve [population fitness-f pred]
  (loop [current-population population]
    (let [selected (select-survivors current-population fitness-f)
          best (first selected)]
      (if (pred best)
        [best current-population]
        (recur (next-population selected))))))

(defn -main []
  (let [initial-population (rand-population)
        fitness-function (partial apply +)
        best-possible-solution (fitness-function (repeat chromosome-len max-gene-value))
        acceptable-solution (* 0.999 best-possible-solution)
        end-condition #(> (fitness-function %) acceptable-solution)]
    (println "Initial population: ")
    (pprint initial-population)
    (println "Evolving...")
    (let [[accepted-solution end-population]
          (time (evolve initial-population fitness-function end-condition))]
      (println "Accepted solution: ")
      (pprint accepted-solution)
      (println "Final population: ")
      (pprint end-population))))
