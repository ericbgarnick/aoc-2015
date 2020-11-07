(ns day01.shared
  (:gen-class))


(defn add-paren
  [total paren]
  (if (= paren "(")
    (+ total 1)
    (- total 1)))
