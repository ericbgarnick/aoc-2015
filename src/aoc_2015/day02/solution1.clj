(ns day02.solution1
  (:require [day02.shared :refer :all])
  (:gen-class))

(require 'day02.shared)
(refer 'day02.shared)

(defn area-for-dimensions
  "Return total surface area plus smallest side extra"
  [[x y z]]
  (+ (* x y)
     (* 2
        (+
          (* x y)
          (* x z)
          (* y z)))))

(defn -main
  [& args]
  (println (total-for-dimensions area-for-dimensions)))
