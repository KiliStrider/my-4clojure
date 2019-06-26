(ns my-4clojure.hard)
(use 'clojure.test)

; #53 - Longest Increasing Sub-Seq
(def q-53 (fn [s]
            (reduce #(if (> (count %2) (count %1)) %2 %1)
                    []
                    (filter
                      #(> (count %) 1)
                      (reduce (fn [r e]
                                (if (and (not= (last r) nil) (> e (last (last r))))
                                  (conj (apply vector (drop-last r)) (conj (last r) e))
                                  (conj r [e])))
                              []
                              s)))))


; #73 - Analyze a Tic-Tac-Toe Board
(def q-73 (fn [r]
            (let [c (apply map list r)
                  d [(for [i (range 3)] (get (get r (- 2 i)) i))
                     (for [i (range 3)] (get (get r i) i))]]
              (->> (map
                     (fn [s]
                       (reduce #(when (and (= %1 %2) (not (= %1 :e))) %2) s))
                     (concat r c d))
                   (filter some?)
                   (first)))))