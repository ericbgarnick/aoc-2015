(ns day05.solution1
  (:require [clojure.string :refer [join split]])
  (:gen-class))

(def vowels #{\a \e \i \o \u})
(def min-vowels 3)
(def bad-sequences #{"ab" "cd" "pq" "xy"})

(defn parse-input
  "Read input file content and split on newlines"
  []
  (split (slurp "src/aoc_2015/day05/input.txt") #"\n"))

(defn has-enough-vowels?
  [candidate]
  (> (count (filter #(contains? vowels %) candidate)) (- min-vowels 1)))

(defn has-any-pairs?
  [candidate]
  (loop [first-letter (first candidate)
        remaining (rest candidate)]
    (if (empty? remaining)
      false
      (or
        (= first-letter (first remaining))
        (recur (first remaining) (rest remaining))))))

(defn has-no-bad-sequences?
  [candidate]
  (loop [candidate candidate]
    (if (< (count candidate) 2)
      true
      (and (not (contains? bad-sequences (join (take 2 candidate))))
          (recur (rest candidate))))))

(defn is-nice?
  [candidate]
  (and
    (has-enough-vowels? candidate)
    (has-any-pairs? candidate)
    (has-no-bad-sequences? candidate)))

(defn count-nice
  [candidates]
  (count (filter is-nice? candidates)))

(defn -main
  [& args]
  (println (count-nice (parse-input))))
