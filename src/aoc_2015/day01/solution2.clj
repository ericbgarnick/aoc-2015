(ns day01.solution2
  (:gen-class))

(require 'day01.shared)
(refer 'day01.shared)

(defn find-basement
  ([parens]
   (find-basement parens 1 0))
  ([parens position total]
   (if (= -1 (add-paren total (first parens)))
     position
     (find-basement
       (rest parens)
       (+ position 1)
       (add-paren total (first parens))))))

(defn -main
  [& args]
  (println (find-basement (parse-input))))
