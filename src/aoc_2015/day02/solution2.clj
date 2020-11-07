(ns day02.solution2
  (:gen-class))

(require 'day02.shared)
(refer 'day02.shared)

(defn length-for-dimensions
  "Return perimeter of the smallest side plus the volume of the whole"
  [[x y z]]
  (+ (* 2 (+ x y))
     (* x y z)))

(defn -main
  [& args]
  (println (total-for-dimensions length-for-dimensions)))
