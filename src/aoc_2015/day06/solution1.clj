(ns day06.solution1
  (:require [day06.shared :refer :all])
  (:gen-class))

(defn toggle
  [grid point]
  (update grid point not))

(defn turn-off
  [grid point]
  (assoc grid point false))

(defn turn-on
  [grid point]
  (assoc grid point true))

(defn count-switched-on
  [grid]
  (count (filter true? (map val grid))))

(defn -main
  [& args]
  (println (count-switched-on (execute-instructions (parse-input) 'day06.solution1))))
