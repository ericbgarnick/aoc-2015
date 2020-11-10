(ns day06.solution1
  (:gen-class))

;;;;;;
;; Manipulate grid values
;;;;;;
(defn toggle
  [grid point]
  (update grid point not))

(defn turn-off
  [grid point]
  (assoc grid point false))

(defn turn-on
  [grid point]
  (assoc grid point true))

;;;;;;
;; Interpret instructions
;;;;;;
(defn set-points
  [grid [x-start y-start] [x-end y-end] operation]
  (for [x-val (range x-start (+ x-end 1))
        y-val (range y-start (+ y-end 1))]
    (operation grid [x-val y-val])))

;; TODO: get operation function by name
(defn extract-operation
  [instruction]
  (clojure.string/replace (clojure.string/trim (re-find #"[a-z ]+" instruction)) #" " "-"))

(defn extract-start-point
  [instruction]
  (clojure.string/split (re-find #"\d+,\d+" instruction) #","))

(defn extract-end-point
  [instruction]
  (clojure.string/split (re-find #"\d+,\d+$" instruction) #","))

(defn execute-line
  [line grid]
  (set-points grid (extract-start-point line) (extract-end-point line) (extract-operation line)))
