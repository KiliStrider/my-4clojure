(ns my-4clojure.easy)
(use 'clojure.test)

; #26 - Fibonacci Sequence
(def q-26 (fn
            [n]
            (take n ((fn nf
                       [a b]
                       (lazy-seq (cons b (nf b (+ a b))))) 0 1 ))))

(deftest q-26-test-1
  (is (= (q-26 3) '(1 1 2))))

(deftest q-26-test-2
  (is (= (q-26 6) '(1 1 2 3 5 8))))

(deftest q-26-test-3
  (is (= (q-26 8) '(1 1 2 3 5 8 13 21))))



(run-tests)