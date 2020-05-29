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

; #77 - Anagram Finder
(def q-77 (fn [words]
            (->> words
                 (group-by #(apply str (sort %)))
                 (vals)
                 (remove #(= (count %) 1))
                 (map set)
                 (set))))

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

; #171 - Intervals
; Write a function that takes a sequence of integers and returns a sequence of "intervals". Each interval is a a vector
; of two integers, start and end, such that all integers between start and end (inclusive) are contained in the input
; sequence.

(defn add-interval [intervals n]
  (let [[f t] (last intervals)]
        (if (and t (= (inc t) n))
          (conj (vec (drop-last intervals)) [f n])
          (conj intervals (vector n n)))))

(def q-171 (fn [s]
             (loop [[e & r] (->> s
                                 distinct
                                 sort)
                    intervals []]
               (if (some? e)
                 (recur r (add-interval intervals e))
                 intervals)
               )))

(def q-171-new (fn [s]
             (loop [[e & r] (->> s
                                 distinct
                                 sort)
                    intervals []]
               (let [[f t] (last intervals)]
                 )

               (if (some? e)
                 (recur r (add-interval intervals e))
                 intervals)
               )))

; #195 - Parentheses... Again
; Generate all possible combinations of well-formed parentheses
(def q-195 (fn gpc [n]
             (if (> n 0)
               (into #{}
                     (mapcat #(vector (str "()" %) (str "(" % ")") (str % "()")) (gpc (- n 1))))
               #{""})))

(defn gpc [n]
  (if (> n 0)
    (into #{}
          (mapcat #(vector (str "()" %) (str "(" % ")") (str % "()")) (gpc (- n 1))))
    #{""}))


