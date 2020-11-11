(ns day06.solution1
  (:require [clojure.string :refer [split]])
  (:gen-class))

(defn parse-input
  "Read input file content and split on newlines"
  []
  (split (slurp "src/aoc_2015/day06/input.txt") #"\n"))

;;;;;;
;; Manipulate grid values
;;;;;;
(defn toggle
  [grid point]
  (update grid point not))

(defn turn-off
  [grid point]
  (assoc grid point false))

(defn turn-on
  [grid point]
  (assoc grid point true))

;;;;;;
;; Interpret instructions
;;;;;;
(defn points-for-range
  [[x-start y-start] [x-end y-end]]
  (for [x-val (range x-start (+ x-end 1))
        y-val (range y-start (+ y-end 1))]
    [x-val y-val]))

(defn set-points
  [grid start-point end-point operation]
  (reduce operation grid (points-for-range start-point end-point)))

(defn operation-name
  [instruction]
  (clojure.string/replace (clojure.string/trim (re-find #"[a-z ]+" instruction)) #" " "-"))

(defn extract-operation
  [instruction]
  (-> (operation-name instruction) symbol resolve))

(defn extract-start-point
  [instruction]
  (map #(Integer/parseInt %) (clojure.string/split (re-find #"\d+,\d+" instruction) #",")))

(defn extract-end-point
  [instruction]
  (map #(Integer/parseInt %) (clojure.string/split (re-find #"\d+,\d+$" instruction) #",")))

(defn execute-line
  [line grid]
  (set-points grid (extract-start-point line) (extract-end-point line) (extract-operation line)))

(defn execute-instructions
  [instructions]
  (loop [instructions instructions
         grid {}]
    (if (empty? instructions)
      grid
      (recur (rest instructions) (execute-line (first instructions) grid)))))

(defn count-switched-on
  [grid]
  (count (filter true? (map val grid))))

(defn -main
  [& args]
  (println (count-switched-on (execute-instructions (parse-input)))))
