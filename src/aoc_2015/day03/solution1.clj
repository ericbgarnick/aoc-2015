(ns day03.solution1
  (:gen-class))

(defn parse-input
  "Read input file content and split each character"
  []
  (slurp "src/aoc_2015/day03/input.txt"))

(defn next-x
  [cur-x direction]
  (if (= direction \<)
    (- cur-x 1)
    (+ cur-x 1)))

(defn next-y
  [cur-y direction]
  (if (= direction \v)
    (- cur-y 1)
    (+ cur-y 1)))

(defn next-coords
  "Return the next coordinate pair to visit following the given direction"
  [cur-x cur-y direction]
  (if (or (= direction \<) (= direction \>))
    (vector (next-x cur-x direction) cur-y)
    (vector cur-x (next-y cur-y direction))))

(defn follow-directions
  "Return the total number of locations visited at least once"
  [directions]
  (loop [visited #{} [cur-x cur-y] [0 0] directions directions]
    (if-let [next-dir (first directions)]
      (recur
        (conj visited [cur-x cur-y])
        (next-coords cur-x cur-y next-dir)
        (rest directions))
      (count visited))))

(defn -main
  [& args]
  (println (follow-directions (parse-input))))
