(ns my-4clojure.medium)
(use 'clojure.test)


; #74 - Filter Perfect Squares
(def q-74 (fn [s]
            (clojure.string/join
              ","
              (filter
                (fn [n]
                  (> (count (filter #(= n (* % %)) (range n))) 0))
                (map read-string (clojure.string/split s #","))))
            ))
