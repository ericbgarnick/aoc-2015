(ns day05.solution2
  (:require [clojure.string :refer [join split]])
  (:gen-class))

(def vowels #{\a \e \i \o \u})
(def min-vowels 3)
(def bad-sequences #{"ab" "cd" "pq" "xy"})

(defn parse-input
  "Read input file content and split on newlines"
  []
  (split (slurp "src/aoc_2015/day05/input.txt") #"\n"))

(defn is-repeated-pair?
  [pair? search-space]
  (and
    (= (first pair?) (last pair?))
    (re-find (re-pattern pair?) search-space)))

(defn has-repeated-pairs?
  [candidate]
  (loop [pair? (subs candidate 0 2)
         remaining (subs candidate 1)]
    (if (empty? (subs remaining 1))
      false
      (or
        (is-repeated-pair? pair? (subs remaining 1))
        (recur (subs remaining 0 2) (subs remaining 1))))))

(defn has-a-sandwich?
  [candidate]
  ())

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
