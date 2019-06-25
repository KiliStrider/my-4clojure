(ns my-4clojure.t-medium
  (:require [midje.sweet        :refer :all]
            [my-4clojure.medium :refer :all]))

(fact
  (q-43 [1 2 3 4 5 6] 2) => '((1 3 5) (2 4 6))
  (q-43 (range 9) 3) => '((0 3 6) (1 4 7) (2 5 8))
  (q-43 (range 10) 5) => '((0 5) (1 6) (2 7) (3 8) (4 9)))

(fact
  (q-44 2 [1 2 3 4 5]) => '(3 4 5 1 2)
  (q-44 -2 [1 2 3 4 5]) => '(4 5 1 2 3)
  (q-44 6 [1 2 3 4 5]) => '(2 3 4 5 1)
  (q-44 1 '(:a :b :c)) => '(:b :c :a)
  (q-44 -4 '(:a :b :c)) => '(:c :a :b))

(fact
  (q-74 "4,5,6,7,8,9") => "4,9"
  (q-74 "15,16,25,36,37") => "16,25,36")
