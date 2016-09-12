(ns brave-clojure.ch-5-functional-programming)


; 1) You used (comp :intelligence :attributes) to create a function that returns a character’s intelligence. 
; Create a new function, attr, that you can call like (attr :intelligence) and that does the same thing.
(defn attr
  [key]
  (comp key :attributes))

; 2) Implement the comp function.
(defn my-comp
  ([] identity)
  ([f] f)
  ([f g]
   (fn
     ([] (f (g))) 
     ([x] (f (g x))) 
     ([x y] (f (g x y))) 
     ([x y & args] (f (apply g x y args)))))
  ([f g & fns]
    (reduce my-comp (list* f g fns))))

; 3) Implement the assoc-in function. 
; Hint: use the assoc function and define its parameters as [m [k & ks] v].
(defn my-assoc-in
  [m [k & ks] v]
  (if ks
    (assoc m k (my-assoc-in (get m k) ks v)) 
    (assoc m k v)))

; 4+5) Look up and use the update-in function. Implement update-in.
(defn my-update-in
  [m [k & ks] f & args]
  (if ks
    (assoc m k (apply my-update-in (get m k) ks f args))
    (assoc m k (apply f (get m k) args))))