(ns brave-clojure.ch-3-test
  (:require [clojure.test :refer :all]
            [brave-clojure.ch-3 :refer :all]))

(deftest add-hundred-test
  (testing "Check we can add 100 to a number"
    (is (= (add-hundred 1) 101))))

(deftest dec-maker-test
  (testing "Can we create a function that decrements by 5"
    (is (= ((dec-maker 5) 10) 5))))