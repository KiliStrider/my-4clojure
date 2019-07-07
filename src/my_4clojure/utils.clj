(ns my-4clojure.utils)


(defn order-by
  "
  Receives [keyfn1 direction1 keyfn2 direction1 ...] coll
  Returns a sorted sequence of the items in coll, where the sort
  order is determined by comparing (keyfn1 item) with direction
  direction1, then by comparing (keyfn2 item) with direction
  direction2 ...

  Usage example:
    (order-by [first :desc second :asc] [[9 7] [9 4] [2 5] [9 2]])
  "
  [keyfn-direction-pairs coll]
  {:pre [((comp even? count) keyfn-direction-pairs)
         (->> keyfn-direction-pairs
              rest
              (take-nth 2)
              (every? #{:asc :desc}))]}
  (let [keyfns (take-nth 2 keyfn-direction-pairs)
        directions (take-nth 2 (rest keyfn-direction-pairs))]
    (sort-by
      (apply juxt keyfns)
      (fn [x y]
        (->> (interleave directions x y)
             (partition 3)
             (reduce (fn [[x' y'] [order xi yi]]
                       (case order
                         :asc [(conj x' xi) (conj y' yi)]
                         :desc [(conj x' yi) (conj y' xi)])) [[] []])
             (apply compare)))
      coll)))

(comment
  (sort-by
    (juxt :a :b :c)
    (fn [x y] (compare (vec (cons (first y) (rest x))) (vec (cons (first x) (rest y)))))
    [{:a "aa0" :b "bb0" :c "cc1" :d "dd0"}
     {:a "ab0" :b "bb0" :c "cc0" :d "dd0"}
     {:a "ac0" :b "bb0" :c "cc0" :d "dd0"}
     {:a "aa0" :b "bb0" :c "cc0" :d "dd0"}])
  (order-by
    [:a :desc :b :asc :c :asc]
    [{:a "aa0" :b "bb0" :c "cc1" :d "dd0"}
     {:a "ab0" :b "bb0" :c "cc0" :d "dd0"}
     {:a "ac0" :b "bb0" :c "cc0" :d "dd0"}
     {:a "aa0" :b "bb0" :c "cc0" :d "dd0"}]))
