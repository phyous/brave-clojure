(ns brave-clojure.ch-5-test
  (:require [clojure.test :refer :all]
            [brave-clojure.ch-5-functional-programming :refer :all]))

(def character
  {:name       "Smooches McCutes"
   :attributes {:intelligence 10
                :strength     4
                :dexterity    5
                :perks        {:happy 10}}})

(deftest attr-test
  (testing "can access intelligence attribute"
    (is (= 10 ((attr :intelligence) character)))))

(deftest my-comp-test
  (testing "can compose 3 functions with my-comp")
  (is (= 10 ((my-comp :happy :perks :attributes) character))))

(def enhanced-character (my-assoc-in character [:attributes :perks :blood] 1)) 

(deftest my-assoc-in-test
  (testing "can add value using my-assoc-in")
  (is (= 1 ((my-comp :blood :perks :attributes) enhanced-character))))

(def changed-character (my-update-in character [:attributes :perks :happy] inc))

(deftest my-update-in-test
  (testing "can change a value in a map")
  (is (= 11 ((my-comp :happy :perks :attributes) changed-character))))