(ns brave-clojure.ch-3)

;2) Write a function that takes a number and adds 100 to it.
(defn add-hundred 
  "Adds 100 to a number"
  [num] 
  (+ num 100))

;3) Write a function, dec-maker, that works exactly like the function inc-maker 
; except with subtraction:
;(def dec9 (dec-maker 9))
;(dec9 10)
; => 1
(defn dec-maker [num]
  (fn [x] (- x num)))

;4) Write a function, mapset, that works like map except the return value is a set:
;(mapset inc [1 1 2 2])
;; => #{2 3}
(defn mapset [f l]
  (into #{} (map f l)))

;5+6) Create a function that’s similar to symmetrize-body-parts except that it has to 
;work with weird space aliens with radial symmetry. Instead of two eyes, arms, 
;legs, and so on, they have five.
(defn matching-multi-part [part n]
  (defn gen-names [part]
    (if (re-matches #"^left-.*" (:name part))
      (map (fn [x] {:name (clojure.string/replace (:name part) #"^left-" (str x "-")) :size (:size part)} ) 
           (range 1 (+ 1 n) 1))
      [part]
      ))
  (gen-names part))

(defn symmetrize-space-alien
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
              (into final-body-parts (matching-multi-part part 5)))
            []
            asym-body-parts))