(ns day04.shared
  (:require [digest :refer [md5]])
  (:require [clojure.string :refer [join]])
  (:gen-class))

(def input-key "ckczppom")

(defn find-hash-num
  ([num-zeros]
   (loop [num-key 1
          hash-result (md5 (str input-key num-key))]
     (if (= (subs hash-result 0 num-zeros) (join (repeat num-zeros "0")))
       num-key
       (recur
         (+ num-key 1)
         (md5 (str input-key (+ num-key 1))))))))
