(ns my-4clojure.easy)
(use 'clojure.test)

; #26 - Fibonacci Sequence
(def q-26 (fn
            [n]
            (take n ((fn nf
                       [a b]
                       (lazy-seq (cons b (nf b (+ a b))))) 0 1 ))))

; #32 - Duplicate a Sequence
(def q-32 (fn [s]
            (reduce into (apply map vector (repeat 2 s)))))


; #33 - Replicate a Sequence
(def q-33 (fn [s n]
            (reduce into (apply map vector (repeat n s)))))

; #34 - Implement range
(def q-34 (fn
            [n m]
            (take (- m n)
                  ((fn nf [a]
                       (lazy-seq (cons a (nf (inc a))))) n ))))

; #41 - Drop Every Nth Item
(def q-41 (fn
            [s n]
            (reduce #(concat %1 (take (- n 1) %2))
                    []
                    (partition-all n s))
            ))

; #42 - Factorial Fun
(def q-42 #(reduce * (range 1 (+ % 1))))
