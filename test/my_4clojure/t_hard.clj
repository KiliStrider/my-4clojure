(ns my-4clojure.t-hard
  (:require [midje.sweet      :refer :all]
            [my-4clojure.hard :refer :all]))



(fact
  (q-53 [1 0 1 2 3 0 4 5]) => [0 1 2 3]
  (q-53 [5 6 1 3 2 7]) => [5 6]
  (q-53 [2 3 3 4 5]) => [3 4 5]
  (q-53 [7 6 5 4]) => [])

(fact
  (q-73 [[:e :e :e]
         [:e :e :e]
         [:e :e :e]]) => nil

  (q-73 [[:x :e :o]
         [:x :e :e]
         [:x :e :o]]) => :x

  (q-73 [[:e :x :e]
         [:o :o :o]
         [:x :e :x]]) => :o

  (q-73 [[:x :e :o]
         [:x :x :e]
         [:o :x :o]]) => nil

  (q-73 [[:x :e :e]
         [:o :x :e]
         [:o :e :x]]) => :x

  (q-73 [[:x :e :o]
         [:x :o :e]
         [:o :e :x]]) => :o

  (q-73 [[:x :o :x]
         [:x :o :x]
         [:o :x :o]]) => nil
  )

(fact
  (q-91 #{[:a :a]}) => true
  (q-91 #{[:a :b]}) => true
  (q-91 #{[1 2] [2 3] [3 1] [4 5] [5 6] [6 4] [3 4]}) => true
  (q-91 #{[1 2] [2 3] [3 1] [4 5] [5 6] [6 4]}) => false
  (q-91 #{[:a :b] [:b :c] [:c :d] [:x :y] [:d :a] [:b :e]}) => false
  (q-91 #{[:a :b] [:b :c] [:c :d] [:x :y] [:d :a] [:b :e] [:x :a]}) => true)