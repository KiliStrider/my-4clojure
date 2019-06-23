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

#_(defn find-longest-increasing-sub-seq
  [s]
  (reduce #(if (> (count %2) (count %1)) %2 %1)
          []
          (filter
            #(> (count %) 1)
            (reduce (fn [r e]
                      (if (and (not= (last r) nil) (> e (last (last r))))
                        (conj (apply vector (drop-last r)) (conj (last r) e))
                        (conj r [e])))
                    []
                    s))))

(deftest q-53-test-1
  (is (= (q-53 [1 0 1 2 3 0 4 5]) [0 1 2 3])))

(deftest q-53-test-2
  []
  (is (= (q-53 [5 6 1 3 2 7]) [5 6])))

(deftest q-53-test-3
  []
  (is (= (q-53 [2 3 3 4 5]) [3 4 5])))

(deftest q-53-test-4
  []
  (is (= (q-53 [7 6 5 4]) [])))


(run-tests)