(ns day05.solution2
  (:require [clojure.string :refer [join split]])
  (:gen-class))

(defn parse-input
  "Read input file content and split on newlines"
  []
  (split (slurp "src/aoc_2015/day05/input.txt") #"\n"))

(defn has-repeated-pairs?
  [candidate]
  (loop [pair? (subs candidate 0 2)
         remaining (subs candidate 1)]
    (if (empty? (subs remaining 1))
      false
      (or
        (re-find (re-pattern pair?) (subs remaining 1))
        (recur (subs remaining 0 2) (subs remaining 1))))))

(defn has-a-sandwich?
  [candidate]
  (loop [first-letter (first candidate)
         remaining (rest candidate)]
    (if (empty? (rest remaining))
      false
      (or (= first-letter (nth remaining 1))
          (recur (first remaining) (rest remaining))))))

(defn is-nice?
  [candidate]
  (and
    (has-repeated-pairs? candidate)
    (has-a-sandwich? candidate)))

(defn count-nice
  [candidates]
  (count (filter is-nice? candidates)))

(defn -main
  [& args]
  (println (count-nice (parse-input))))
