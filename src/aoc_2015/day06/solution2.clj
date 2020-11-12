(ns day06.solution2
  (:require [day06.shared :refer :all])
  (:gen-class))

(defn increase
  [grid point value]
  (if (get grid point)
    (update grid point #(+ % value))
    (assoc grid point value)))

(defn toggle
  [grid point]
  (increase grid point 2))

(defn turn-off
  [grid point]
  (if-let [cur-val (get grid point)]
    (assoc grid point (max (- cur-val 1) 0))
    grid))

(defn turn-on
  [grid point]
  (increase grid point 1))

(defn sum-brightness
  [grid]
  (apply + (map val grid)))

(defn -main
  [& args]
  (println (sum-brightness (execute-instructions (parse-input) 'day06.solution2))))
