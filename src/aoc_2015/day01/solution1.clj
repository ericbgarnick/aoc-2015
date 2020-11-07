(ns day01.solution1
  (:require [day01.shared :refer :all])
  (:gen-class))

(defn sum-parens
  [parens]
  (reduce add-paren 0 parens))

(defn -main
  [& args]
  (println (sum-parens (parse-input))))
