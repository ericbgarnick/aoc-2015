(ns day07.dfs
  (:require [clojure.set :refer [intersection]]))

(def g {"a" {:d [] :p nil :v nil :s nil :f nil}
        "b" {:d ["e" "d" "c"] :p nil :v nil :s nil :f nil}
        "c" {:d ["d" "g"] :p nil :v nil :s nil :f nil}
        "d" {:d ["f"] :p nil :v nil :s nil :f nil}
        "e" {:d ["f"] :p nil :v nil :s nil :f nil}
        "f" {:d ["g"] :p nil :v nil :s nil :f nil}
        "g" {:d ["h"] :p nil :v nil :s nil :f nil}
        "h" {:d ["a"] :p nil :v nil :s nil :f nil}})

(defn unknowns
  "Return graph keys whose values are unknown"
  [graph]
  (filter #(nil? (get-in graph [% :p])) (keys graph)))

(defn visit
  [graph node]
  (println (str "VISITING: " node))
  (let [descs (intersection (set (get-in graph [node :d])) (set (unknowns graph)))]
    (do
      (map #(assoc-in graph [% :p] node) descs)
      (map #(visit graph %) descs)
      graph)))

(defn dfs
  [graph]
  (map #(visit graph %) (unknowns graph)))
