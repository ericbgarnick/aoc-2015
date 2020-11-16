(ns day07.solution1a
  (:require [clojure.string :refer [lower-case split]])
  (:gen-class))

(declare find-value)

(defn parse-input
  "Read input file content and split on newlines"
  []
  (split (slurp "src/aoc_2015/day07/input-test.txt") #"\n"))

;;;;;;;;;;;;;;;;
;; Operations ;;
;;;;;;;;;;;;;;;;
(def mask 65535)

(defn op-not
  [conversions operands]
  (bit-and mask (bit-not (find-value conversions (operands 0)))))

(defn op-and
  [conversions operands]
  (bit-and mask (bit-and (find-value conversions (operands 0)) (find-value conversions (operands 1)))))

(defn op-or
  [conversions operands]
  (bit-and mask (bit-or (find-value conversions (operands 0)) (find-value conversions (operands 1)))))

(defn op-rshift
  [conversions operands]
  (bit-and mask (bit-shift-right (find-value conversions (operands 1)) (operands 0))))

(defn op-lshift
  [conversions operands]
  (bit-and mask (bit-shift-left (find-value conversions (operands 1)) (operands 0))))

;;;;;;;;;;;;;
;; Parsing ;;
;;;;;;;;;;;;;
(defn set-value
  [instruction-map identifier object-key value]
  (assoc-in instruction-map [identifier object-key] value))

(defn set-operation
  [instruction-map identifier operation operands value]
  (if value
    (assoc-in instruction-map [identifier operation] (vec (cons (Integer/parseInt value) operands)))
    (assoc-in instruction-map [identifier operation] operands)))

(defn set-identifier
  [instruction-map identifier operands value]
  (if (re-matches #"\d+" (str value))
    (assoc instruction-map identifier (Integer/parseInt value))
    (assoc instruction-map identifier (operands 0))))

(defn parse-or
  [raw-source]
  (split raw-source #" OR "))

(defn parse-and
  [raw-source]
  (split raw-source #" AND "))

(defn parse-not
  [raw-source]
  (vec (rest (split raw-source #"NOT "))))

(defn parse-lshift
  [raw-source]
  (split raw-source #" LSHIFT "))

(defn parse-rshift
  [raw-source]
  (split raw-source #" RSHIFT "))

(defn parse-fn
  "Return the parse function identified by operation-name"
  [operation-name]
  (if operation-name
    (ns-resolve 'day07.solution1a (symbol (str "parse-" operation-name)))
    identity))

(defn set-instruction
  [raw-instruction
   instruction-map]
  (let [identifier ((re-find #"-> (\w+)" raw-instruction) 1)
        operation (re-find #"[A-Z]+" raw-instruction)
        dependencies ((parse-fn operation) ((split raw-instruction #" -> ") 0))
        value (re-find #"\d+" raw-instruction)]
    (set-value (set-value (set-value (set-value instruction-map identifier :val value) identifier :deps dependencies) identifier :subs []) identifier :op operation)))

(defn parse-instructions
  [raw-instructions]
  (loop [remaining-instructions raw-instructions
         instruction-map {}]
    (if (empty? remaining-instructions)
      instruction-map
      (recur
        (rest remaining-instructions)
        (set-instruction (first remaining-instructions) instruction-map)))))

;;;;;;;;;;;;;;;;;;;;
;; Resolve values ;;
;;;;;;;;;;;;;;;;;;;;
(defn deref-or-identity
  "Return quarry if it is a number, otherwise return
  the value in conversions for quarry as a key"
  [conversions quarry]
  (if (number? quarry)
    quarry
    (conversions quarry)))

(defn op-fn
  "Return the operation function identified by operation-name"
  [operation-name]
  (ns-resolve 'day07.solution1a (symbol (str "op-" operation-name))))

(defn compute
  [conversions ops]
  (if (string? ops)
    (find-value conversions ops)
    ((op-fn (first (keys ops))) conversions (first (vals ops)))))

(defn find-value
  [conversions quarry]
  (let [value (deref-or-identity conversions quarry)]
    (if (number? value)
      value
      (compute conversions value))))

(defn -main
  [& args]
  (println (find-value (parse-instructions (parse-input)) "a")))
