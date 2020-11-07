(ns day01.solution1
  (:gen-class))

(require 'day01.shared)
(refer 'day01.shared)

(defn sum-parens
  [parens]
  (reduce add-paren 0 parens))

(defn -main
  [& args]
  (println (sum-parens (parse-input))))
