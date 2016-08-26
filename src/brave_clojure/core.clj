(ns brave-clojure.core
  (:gen-class)
  (use brave-clojure.hobbit))

(declare
  ; chapter 2 - basics
  foo eq and-test severity-msg map-fn map-nested set-member
  map-test arity fav-things chooser treasure-announcer
  greet test-ana test-let test-loop
  )

(defn -main
  "I don't do a whole
   lot ... yet."
  [& args]
  (println (str "Here's our hobbit: " (better-symmetrize-body-parts asym-hobbit-body-parts)))
  (println (str "Hobbit hit! " (hit asym-hobbit-body-parts)))
  (println "Hello, World!!"))

; test usage of the loop special form
(defn test-loop []
  (loop [it 0] 
    (println (str "Iteration " it))
    (if (> it 3)
      (println "Goodbye!")
      (recur (inc it)))))

; testing the use of let (binds value in a new scope)
(defn test-let [x]
  (let [y 1] (+ x y)))

; compact form of anonymous functions
(defn test-ana [x]
  (map #(+ % 1) x)
  )

; anonymous function example
(defn greet [people]
  (map (fn [name] (str "Hello, " name)) people ) )

; deconstruction example using map input
(defn treasure-announcer [{lat :lat lng :lng}]
  (println (str "Treasure latitude: " lat))
  (println (str "Treasure longitude: " lng)))

; Deconstruct input array by namming inner elements
(defn chooser [[first second & others]]
  '((str "Your first choice is: " first)
    (str "Your second choice is: " second)
    (str "Other choices: " (clojure.string/join " ," others))
    )
  )

(defn fav-things [name & things]
  (str "Hi " name ". My favorite things are: "
  (clojure.string/join ", " things)
  ))

;Define a funciton that can take multiple nubmer of params (differing arity)
(defn arity
  ([] (str "none"))
  ([s1] (str s1))
  ([s1 s2] (str s1 s2)))

; Test the use of the map funciton
(defn map-test [list]
  (map inc list)
  )

; Check membership of a hash set
(defn set-member []
  (def my-set #{1 2 3 "cat"})
  (contains? my-set 1)) ; should return true

; acces nested elements in a map
(defn map-nested [l1 l2]
  (def m {:a 1 :b 2 :c {:d 3}})
  (get-in m [l1 l2]))

; store a set of funcitons in a map and get them
(defn map-fn [op v1 v2]
  (def fns {:add + :sub -})
  ((get fns op) v1 v2))

; Make use of def to bind values
(defn severity-msg [severity]
  (def base-msg "Things are ")
  (def err-msg (if (= severity :bad) "real bad" "ok"))
  (str base-msg err-msg))

; wraps and funciton
(defn and-test [a b]
  (and a b))

(defn eq []
  (if (= 1 1) (println "foo")))

(defn foo []
  (if true
    (do (println "true"))
    (do (println "false"))
  ))
