(ns my-4clojure.t-utils
  (:require [midje.sweet        :refer :all]
            [my-4clojure.utils :refer :all]))

(def test-data [{:a "aa0" :b "bb0" :c "cc1" :d "dd0"}
                {:a "ab0" :b "bb0" :c "cc0" :d "dd1"}
                {:a "ac0" :b "bb0" :c "cc0" :d "dd2"}
                {:a "aa0" :b "bb0" :c "cc0" :d "dd3"}])

(fact
  (adv-sort-by
    [:a :descending :b :ascending :c :ascending]
    test-data) =>
  (sort-by
    (juxt :a :b :c)
    (fn [[xa xb xc] [ya yb yc]] (compare [ya xb xc] [xa yb yc]))
    test-data)

  (adv-sort-by
    [:a :ascending :b :ascending :c :ascending]
    test-data) =>
  (sort-by
    (juxt :a :b :c)
    test-data)

  (adv-sort-by
    [:a :descending :b :descending :c :descending]
    test-data) =>
  (sort-by
    (juxt :a :b :c)
    #(compare %2 %1)
    test-data)
  )
