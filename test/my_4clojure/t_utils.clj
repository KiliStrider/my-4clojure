(ns my-4clojure.t-utils
  (:use [midje.sweet]
        [my-4clojure.utils])
  (:require [miner.herbert :as h]
            [miner.herbert.generators :as hg]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.properties :as prop]))


(def test-coll-1 [{:a "aa0" :b "bb0" :c "cc1" :d "dd0"}
                  {:a "ab0" :b "bb0" :c "cc0" :d "dd1"}
                  {:a "ac0" :b "bb0" :c "cc0" :d "dd2"}
                  {:a "aa0" :b "bb0" :c "cc0" :d "dd3"}])

(def test-coll-2 [[3 4] [2 3] [5 5] [2 2] [2 4]])

(facts "order-by tests"
       (tabular
         (fact "order-by test-coll"
               (order-by ?keys-direction ?coll)
               =>
               ?test-output)
         ?keys-direction  ?coll ?test-output
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

(defspec check-order-by 10000
         (hg/property (fn [v]
                        (let [keyfn-direction-pairs [#(mod % 3) :asc #(mod % 2) :desc #(mod % 5) :asc #(mod % 19) :desc]
                              keyfns (take-nth 2 keyfn-direction-pairs)
                              order-by-v (order-by keyfn-direction-pairs v)
                              sort-by-v (sort-by
                                          (apply juxt keyfns)
                                          (fn [[x1 x2 x3 x4] [y1 y2 y3 y4]]
                                            (compare [x1 y2 x3 y4] [y1 x2 y3 x4]))
                                          v)]
                          (and
                            (= order-by-v sort-by-v))))
                      '[int+]))
