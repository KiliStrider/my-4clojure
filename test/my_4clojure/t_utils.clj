(ns my-4clojure.t-utils
  (:use [midje.sweet]
        [my-4clojure.utils]))

(def test-coll-1 [{:a "aa0" :b "bb0" :c "cc1" :d "dd0"}
                  {:a "ab0" :b "bb0" :c "cc0" :d "dd1"}
                  {:a "ac0" :b "bb0" :c "cc0" :d "dd2"}
                  {:a "aa0" :b "bb0" :c "cc0" :d "dd3"}])

(def test-coll-2 [[3 4] [2 3] [5 5] [2 2] [2 4]])

(facts "order-by tests"
       (tabular
         (fact "order-by test-coll"
               (order-by ?keys-order ?coll)
               =>
               ?test-output)
         ?keys-order  ?coll ?test-output
         [:a :desc :b :asc :c :asc] test-coll-1 '({:a "ac0" :b "bb0" :c "cc0" :d "dd2"}
                                                  {:a "ab0" :b "bb0" :c "cc0" :d "dd1"}
                                                  {:a "aa0" :b "bb0" :c "cc0" :d "dd3"}
                                                  {:a "aa0" :b "bb0" :c "cc1" :d "dd0"})
         [:a :asc :b :asc :c :asc] test-coll-1 '({:a "aa0" :b "bb0" :c "cc0" :d "dd3"}
                                                 {:a "aa0" :b "bb0" :c "cc1" :d "dd0"}
                                                 {:a "ab0" :b "bb0" :c "cc0" :d "dd1"}
                                                 {:a "ac0" :b "bb0" :c "cc0" :d "dd2"})
         [:a :desc :b :desc :c :desc] test-coll-1 '({:a "ac0" :b "bb0" :c "cc0" :d "dd2"}
                                                    {:a "ab0" :b "bb0" :c "cc0" :d "dd1"}
                                                    {:a "aa0" :b "bb0" :c "cc1" :d "dd0"}
                                                    {:a "aa0" :b "bb0" :c "cc0" :d "dd3"})
         [first :desc second :desc] test-coll-2 '([5 5] [3 4] [2 4] [2 3] [2 2])
         [first :asc second :asc] test-coll-2 '([2 2] [2 3] [2 4] [3 4] [5 5])))
