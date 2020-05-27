(ns my-4clojure.hard
  (:require [criterium.core :refer [bench]]))

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
                  d [(map #(get-in r [(- 2 %) %]) (range 3))
                     (map #(get-in r [% %]) (range 3))]]
              (->> (map
                     (fn [s]
                       (reduce #(when (and (= %1 %2) (not (= %1 :e))) %2) s))
                     (concat r c d))
                   (filter some?)
                   (first)))))

(defn deletions [word]
  (let [length (count word)]
    (for [i (range length)
          :let [before (take i word)
                after  (take-last (- length (inc i)) word)]]
      (apply str (concat before after)))))

(defn deletion-diff? [w1 w2]
  (->> w1
       deletions
       (some #(= % w2))
       some?))

(defn insertion-diff? [w1 w2]
  (deletion-diff? w2 w1))

(defn substitution-diff? [w1 w2]
  (loop [i 0]
    (let [w1-before (take i w1)
          w1-after (take-last (- (count w1) (inc i)) w1)
          w2-before (take i w2)
          w2-after (take-last (- (count w2) (inc i)) w2)
          char-changed? (not= (get w1 i) (get w2 i))
          ]
      (if char-changed?
        (and (= w1-before w2-before) (= w1-after w2-after))
        (recur (inc i))))))

(defn my-chained? [w1 w2]
  (or (insertion-diff? w1 w2)
      (deletion-diff? w1 w2)
      (substitution-diff? w1 w2)))

(defn permutations [s]
  (lazy-seq
    (if (seq (rest s))
      (apply concat (for [x s]
                      (map #(cons x %) (permutations (remove #{x} s)))))
      (vector (seq s)))))

(defn v1-word-chain? [words]
  (->> words
       permutations
       (map (fn [p] (reduce #(if (my-chained? %1 %2) %2 nil) p)))
       (some some?)
       some?))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn levenshtein-distance [s t]
  "For all i and j, d[i][j] will hold the Levenshtein distance between
  the first i characters of s and the first j characters of t
  note that d has (m+1) * (n+1) values"
  (let [m (count s)
        n (count t)]
    (loop [i 1
           j 1
           d (as-> ;source prefixes can be transformed into empty string by dropping all characters
                   n %
                   (apply vector (range (inc %)))

                   (cons % (apply vector
                                  (for [i (range 1 (inc m))]
                                    ;target prefixes can be reached from empty source prefix by inserting every character
                                    (apply vector (cons i (repeat n 0))))))
                   (apply vector %))]
      (if (> i m)
        (get-in d [m n])
        (let [[ni nj] (if (= j n)
                        [(inc i) 1]
                        [i (inc j)])
              substitution-cost (if (= (get s (dec i)) (get t (dec j))) 0 1)]
          (recur ni
                 nj
                 (assoc-in d [i j] (min (+ (get-in d [(dec i) j]) 1) ;deletion
                                        (+ (get-in d [i (dec j)]) 1) ;insertion
                                        (+ (get-in d [(dec i) (dec j)]) substitution-cost) ;substitution
                                        )
                           )
                 )
          )
        )
      )
    )
  )

(defn chained? [w1 w2]
  (= (levenshtein-distance w1 w2) 1))

(defn all-chained? [words]
  (loop [s words]
    (if (seq (rest s))
      (if (chained? (first s) (second s))
        (recur (rest s))
        false)
      true)))

(defn v2-word-chain? [words]
  (->> words
       permutations
       (map all-chained?)
       (some true?)
       (some?)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
