(ns shared
  (:require [clojure.string :refer [split join]]))

(defn folder-name
  [day-num]
  (if (< day-num 10)
    (str "day0" day-num)
    (str "day" day-num)))

(defn parse-input
  "Read input file content and split on newlines"
  ([day-num]
   (parse-input day-num "input.txt" "\n"))
  ([day-num file-name split-char]
   (split (slurp (join "/" ["src" "aoc_2015" (folder-name day-num) file-name])) (re-pattern split-char))))
