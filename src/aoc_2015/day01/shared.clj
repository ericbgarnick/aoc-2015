(ns day01.shared
  (:gen-class))

(defn add-paren
  [total paren]
  (if (= paren "(")
    (+ total 1)
    (- total 1)))

(defn parse-input
  []
  (clojure.string/split (slurp "src/aoc_2015/day01/input.txt") #""))
