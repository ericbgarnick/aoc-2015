(ns day03.solution2
  (:require [clojure.set :refer [union]])
  (:require [day03.shared :refer :all])
  (:gen-class))

(defn follow-2-santas
  "2 Santas make deliveries, alternating which direction to follow"
  [directions]
  (union
    (follow-directions (take-nth 2 directions))
    (follow-directions (take-nth 2 (rest directions)))))

(defn -main
  [& args]
  (println (count (follow-2-santas (parse-input)))))
