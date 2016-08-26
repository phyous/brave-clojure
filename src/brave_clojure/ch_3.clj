(ns brave-clojure.ch-3)

;Write a function that takes a number and adds 100 to it.
(defn add-hundred 
  "Adds 100 to a number"
  [num] 
  (+ num 100))

;Write a function, dec-maker, that works exactly like the function inc-maker except with subtraction:
;(def dec9 (dec-maker 9))
;(dec9 10)
; => 1
(defn dec-maker [num]
  (fn [x] (- x num)))