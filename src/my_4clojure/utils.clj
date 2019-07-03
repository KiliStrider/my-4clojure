(ns my-4clojure.utils)


(defn adv-sort-by [key-ord-pairs col]
  (let [key-fns (take-nth 2 key-ord-pairs)
        key-to-ord (apply hash-map key-ord-pairs)
        key-to-idx (zipmap key-fns (range (count key-fns)))]
    (sort-by
      (apply juxt key-fns)
      (fn [x y]
        (let [juxt-manipulator #(case (key-to-ord %3)
                                  :ascending (%1 (key-to-idx %3))
                                  :descending (%2 (key-to-idx %3)))
              x-juxt (mapv (partial juxt-manipulator x y) key-fns)
              y-juxt (mapv (partial juxt-manipulator y x) key-fns)]
          (compare x-juxt y-juxt)))
      col)))

(comment
  (sort-by
    (juxt :a :b :c)
    (fn [x y] (compare (vec (cons (first y) (rest x))) (vec (cons (first x) (rest y)))))
    [{:a "aa0" :b "bb0" :c "cc1" :d "dd0"}
     {:a "ab0" :b "bb0" :c "cc0" :d "dd0"}
     {:a "ac0" :b "bb0" :c "cc0" :d "dd0"}
     {:a "aa0" :b "bb0" :c "cc0" :d "dd0"}])
  (adv-sort-by
    [:a :descending :b :ascending :c :ascending]
    [{:a "aa0" :b "bb0" :c "cc1" :d "dd0"}
     {:a "ab0" :b "bb0" :c "cc0" :d "dd0"}
     {:a "ac0" :b "bb0" :c "cc0" :d "dd0"}
     {:a "aa0" :b "bb0" :c "cc0" :d "dd0"}]))
