(ns day08.solution1
  (:require [shared :refer :all]))

(defn adjusted-count
  [raw-input]
  (let [num-octal (count (re-seq #"\\x[0-9a-f]{2}" raw-input))
        num-double-bslash (count (re-seq #"\\\\" raw-input))
        num-quotes (count (re-seq #"\"" raw-input))
        deductions (+ (* num-octal 3) num-double-bslash num-quotes)] ;; 2 for octal because \ char is double-counted
    (println "OCTALS: " num-octal " QUOTES: " num-quotes " DOUBLES: " num-double-bslash)
    (- (count raw-input) deductions)))


(defn custom-total
  [content count-func]
  (reduce + (map count-func content)))


(defn -main
  [& args]
  (let [raw-input (parse-input 8 "input.txt" "\n")]
    (println (- (custom-total raw-input count) (custom-total raw-input adjusted-count)))))
