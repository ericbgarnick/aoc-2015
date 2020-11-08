(ns day03.solution1
  (:require [day03.shared :refer :all])
  (:gen-class))

(defn -main
  [& args]
  (println (count (follow-directions (parse-input)))))
