(ns my-4clojure.t-easy
  (:require [midje.sweet      :refer :all]
            [my-4clojure.easy :refer :all]))

(fact
  (q-26 3) => '(1 1 2)
  (q-26 6) => '(1 1 2 3 5 8)
  (q-26 8) => '(1 1 2 3 5 8 13 21))