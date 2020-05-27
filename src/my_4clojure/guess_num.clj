(ns my-4clojure.guess-num)


(defn guess-num [[start end] cmp-fn]
      (loop [curr-start start
             curr-end end
             result {}]
            (let [half (int (/ (+ curr-start curr-end) 2))
                  compared-to-mid-way (cmp-fn half)]
                  (case compared-to-mid-way
                        1 (recur half curr-end result)
                        -1 (recur curr-start half result)
                        0 (assoc result :guess half)))))

(guess-num [0 100] (partial compare 1))