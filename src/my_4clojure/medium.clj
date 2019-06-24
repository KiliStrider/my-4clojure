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

(deftest q-74-test-1
         (is (= (q-74 "4,5,6,7,8,9") "4,9")))

(deftest q-74-test-2
         (is (= (q-74 "15,16,25,36,37") "16,25,36")))


(run-tests)