(ns brave-clojure.ch-4-test
  (:require [clojure.test :refer :all]
            [brave-clojure.ch-4-fwpd :refer :all]))

(deftest glitter-filter-test
  (testing "There should be 3 names in the list with glitter over 3"
    (is
      (= 3 (count (glitter-filter-list 3 initial-suspects))))))

(deftest append-test
  (testing "When we find another suspect we can add it with the append method"
    (is 
      (= 6 (count (append initial-suspects {:name "foo" :glitter-index 100})) ))))

(deftest append-test-fail
  (testing "Validation fails for malformed suspect and they are not added to suspect list"
    (is
      (= 5 (count (append initial-suspects {:bad "foo" :glitter-index 100})) ))))


