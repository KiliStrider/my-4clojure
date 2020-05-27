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
  (q-70  "Have a nice day.") => ["a" "day" "Have" "nice"]
  (q-70  "Clojure is a fun language!") => ["a" "Clojure" "fun" "is" "language"]
  (q-70  "Fools fall for foolish follies.") => ["fall" "follies" "foolish" "Fools" "for"])

(fact
  (q-74 "4,5,6,7,8,9") => "4,9"
  (q-74 "15,16,25,36,37") => "16,25,36")

(fact
  (q-77 ["meat" "mat" "team" "mate" "eat"]) => #{#{"meat" "team" "mate"}}
  (q-77 ["veer" "lake" "item" "kale" "mite" "ever"]) => #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}})

(fact
  (q-85 #{1 :a}) => #{#{1 :a} #{:a} #{} #{1}}
  (q-85 #{}) => #{#{}}
  (q-85 #{1 2 3}) => #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}}
  (count (q-85 (into #{} (range 10)))) => 1024)

(fact
  (q-171 [1 2 3]) => [[1 3]]
  (q-171 [10 9 8 1 2 3]) => [[1 3] [8 10]]
  (q-171 [1 1 1 1 1 1 1]) => [[1 1]]
  (q-171 []) => [])

#_(fact
  (map (fn [n] (q-195 n)) [0 1 2]) => [#{""} #{"()"} #{"()()" "(())"}]
  (q-195 3) => #{"((()))" "()()()" "()(())" "(())()" "(()())"}
  (count (q-195 10)) => 16796
  (nth (sort (filter #(.contains ^String % "(()()()())") (q-195 9))) 6) => "(((()()()())(())))"
  (nth (sort (q-195 12)) 5000) => "(((((()()()()()))))(()))")