(ns my-4clojure.medium)

; #43 - Reverse Interleave
(def q-43 #(map (fn [s] (take-nth %2 (drop s %1))) (range %2)))

; #44 - Rotate Sequence
(def q-44 (fn [n s]
            (flatten (map #(% (mod n (count s)) s)
                          [drop take]))))

; #70 - Word Sorting
(def q-70 #(sort-by clojure.string/lower-case (re-seq #"\w+" %)))

; #74 - Filter Perfect Squares
(def q-74 (fn [s]
            (clojure.string/join
              ","
              (filter
                (fn [n]
                  (> (count (filter #(= n (* % %)) (range n))) 0))
                (map read-string (clojure.string/split s #","))))
            ))

; #85 - Power Set
(def q-85 (fn ss [s]
            (if (empty? s)
              #{#{}}
              (let [e #{(first s)}
                    ssr  (ss (set (rest s)))]
                (clojure.set/union
                  (set (map #(clojure.set/union e %) ssr))
                  ssr) )
              )))
