(ns my-4clojure.easy)
(use 'clojure.test)

; #26 - Fibonacci Sequence
(def q-26 (fn
            [n]
            (take n ((fn nf
                       [a b]
                       (lazy-seq (cons b (nf b (+ a b))))) 0 1 ))))
