(ns day02.shared
  (:gen-class))

(defn parse-input
  "Read input file content and split on newlines"
  []
  (clojure.string/split (slurp "src/aoc_2015/day02/input.txt") #"\n"))

(defn parse-dimensions
  "Split dimensions string on 'x', interpret values as integers and return sorted result"
  [dimensions]
  (sort (map #(Integer/parseInt %) (clojure.string/split dimensions #"x"))))

(defn total-for-dimensions
  [calculation-function]
  (apply + (map (comp calculation-function parse-dimensions) (parse-input))))
