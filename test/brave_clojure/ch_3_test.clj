(ns brave-clojure.ch-3-test
  (:require [clojure.test :refer :all]
            [brave-clojure.ch-3-ex :refer :all]))

(deftest add-hundred-test
  (testing "Check we can add 100 to a number"
    (is (= (add-hundred 1) 101))))

(deftest dec-maker-test
  (testing "Can we create a function that decrements by 5"
    (is (= ((dec-maker 5) 10) 5))))

(deftest mapset-test
  (testing "can we map a vector into a set"
    (is (= #{2 3 4} (mapset inc [1 2 3])))))

(defn in?
  "true if coll contains elm"
  [coll elm]
  (some #(= elm %) coll))

(deftest symmetrize-space-alien-test
  (testing "can we map an aliens body parts"
    (def parts [{:name "head" :size 3}
                {:name "left-eye" :size 1}])
    ; Check that there are 6 parts after symmetrization
    (def sym-parts (symmetrize-space-alien parts))
    (is (= 6 (count sym-parts)))
    ; there should be 5 eyes & 1 head
    (def part-names (mapv (fn [x] (:name x)) sym-parts))
    (println (str part-names) )
    (is (= true (in? part-names "1-eye")))
    ))