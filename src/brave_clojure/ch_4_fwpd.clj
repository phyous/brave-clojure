(ns brave-clojure.ch-4-fwpd
  (:require [clojure.java.io :as io]))

(def data-file (io/file
                 (io/resource
                   "ch_4_suspects.csv")))

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name          identity
                  :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

(def initial-suspects (mapify (parse (slurp data-file))))

; 1) Turn the result of your glitter filter into a list of names.
(defn glitter-filter-list
  [minimum-glitter records]
  (keys (glitter-filter minimum-glitter records)))

; 3) Write a function, validate, which will check that :name 
; and :glitter-index are present when you append.
(defn validate
  "Make sure an entry has a :name and :glitter-index"
  [entry]
  (and (contains? entry :name) (contains? entry :glitter-index)))

; 2) Write a function, append, which will append a new suspect to your list of suspects.
(defn append
  [current-suspects new-suspect]
  (if (validate new-suspect)
    (conj current-suspects new-suspect)
    current-suspects
    )
  )

; 4) Write a function that will take your list of maps and convert it back to a CSV string. 
; You’ll need to use the clojure.string/join function.
(def select-values (comp vals select-keys))

(defn suspects->csv
  "takes a list of maps and converts them to csv"
  [suspects]
  (def suspect-values
    (map #(select-values % [:name, :glitter-index]) suspects))
  (reduce (fn [input-str values]
            (str input-str (clojure.string/join "," values) "\n"))
          ""
          suspect-values))

(println (suspects->csv initial-suspects))