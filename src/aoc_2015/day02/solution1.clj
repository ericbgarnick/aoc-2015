(ns day02.solution1
  (:gen-class))

(defn parse-input
  "Read input file content and split on newlines"
  []
  (clojure.string/split (slurp "src/aoc_2015/day02/input.txt") #"\n"))

(defn parse-dimensions
  "Split dimensions string on 'x', interpret values as integers and return sorted result"
  [dimensions]
  (sort (map #(Integer/parseInt %) (clojure.string/split dimensions #"x"))))

(defn area-for-dimensions
  "Return total surface area plus smallest side extra"
  [[x y z]]
  (+ (* x y)
     (* 2
        (+
          (* x y)
          (* x z)
          (* y z)))))

(defn total-wrapping-paper
  []
  (apply + (map (comp area-for-dimensions parse-dimensions) (parse-input))))

(defn -main
  [& args]
  (println (total-wrapping-paper)))
