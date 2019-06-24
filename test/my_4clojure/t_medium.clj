(ns my-4clojure.t-medium
  (:require [midje.sweet        :refer :all]
            [my-4clojure.medium :refer :all]))


(fact
  (q-74 "4,5,6,7,8,9") => "4,9"
  (q-74 "15,16,25,36,37") => "16,25,36")
