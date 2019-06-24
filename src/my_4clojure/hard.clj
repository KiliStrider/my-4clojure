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

(deftest q-53-test-1
  (is (= (q-53 [1 0 1 2 3 0 4 5]) [0 1 2 3])))

(deftest q-53-test-2
  (is (= (q-53 [5 6 1 3 2 7]) [5 6])))

(deftest q-53-test-3
  (is (= (q-53 [2 3 3 4 5]) [3 4 5])))

(deftest q-53-test-4
  (is (= (q-53 [7 6 5 4]) [])))


; #73 - Analyze a Tic-Tac-Toe Board
(def q-73 (fn [r]
            (let [cg (map (fn [n] (fn [s] (apply vector (map #(get % n) s)))) (range 3))
                  c (map #(% r) cg)
                  dg (map (fn [n] (fn [s] (get s n))) (range 3))
                  d [(apply vector (map #(%1 %2) dg r)) (apply vector (map #(%1 %2) dg (reverse r)))] ]
              (first (filter
                       #(not (= % nil))
                       (map
                         (fn [s]
                           (reduce #(when (and (= %1 %2) (not (= %1 :e))) %2) s))
                         (concat r c d))))
              )))

(deftest q-73-test-1
  (is (= nil (q-73 [[:e :e :e]
                    [:e :e :e]
                    [:e :e :e]]))))

(deftest q-73-test-2
  (is (= :x (q-73 [[:x :e :o]
                   [:x :e :e]
                   [:x :e :o]]))))

(deftest q-73-test-3
  (is (= :o (q-73 [[:e :x :e]
                   [:o :o :o]
                   [:x :e :x]]))))

(deftest q-73-test-4
  (is (= nil (q-73 [[:x :e :o]
                    [:x :x :e]
                    [:o :x :o]]))))

(deftest q-73-test-5
  (is (= :x (q-73 [[:x :e :e]
                   [:o :x :e]
                   [:o :e :x]]))))

(deftest q-73-test-6
  (is (= :o (q-73 [[:x :e :o]
                   [:x :o :e]
                   [:o :e :x]]))))

(deftest q-73-test-7
  (is (= nil (q-73 [[:x :o :x]
                    [:x :o :x]
                    [:o :x :o]]))))

(run-tests)