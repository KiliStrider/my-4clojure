(ns my-4clojure.misc)

(defn get-current-position [board]
  (let [max-turn (apply max (flatten board))]
    (first (for [r (range 8)
                 c (range 8)
                 :when (= max-turn (get-in board [r c]))]
             [r c]))))

(defn valid-move? [board [row col]]
  (let [board-length (count board)
        in-board? #(and (<= 0 %) (< % board-length))]
    (and
      (in-board? row)
      (in-board? col)
      (= 0 (get-in board [row col])))))

(defn get-next-moves [board]
  (let [[cur-row cur-col] (get-current-position board)
        above [[(- cur-row 2) (- cur-col 1)] [(- cur-row 2) (+ cur-col 1)]]
        below [[(+ cur-row 2) (- cur-col 1)] [(+ cur-row 2) (+ cur-col 1)]]
        right [[(- cur-row 1) (+ cur-col 2)] [(+ cur-row 1) (+ cur-col 2)]]
        left  [[(+ cur-row 1) (- cur-col 2)] [(- cur-row 1) (- cur-col 2)]]
        all-moves (concat above below right left)]
    (filter #(valid-move? board %) all-moves)))

(defn move-knight [board [row-dest col-dest]]
  (let [next-move (+ 1 (apply max (flatten board)))
        board-length (count board)]
    (vec (for [r (range board-length)]
           (if (= row-dest r)
             (vec (for [c (range board-length)]
                    (if (= col-dest c)
                      next-move
                      (get-in board [r c]))))
             (get board r))
           ))))

(defn move-accessibility [board move]
  (count (get-next-moves (move-knight board move))))


(defn solve-knights-tour-rec [board]
  (let [board-length (count board)
        next-move (+ 1 (apply max (flatten board)))
        completed-tour (> next-move (* board-length board-length))
        next-moves (sort-by (partial move-accessibility board) (get-next-moves board))]
    (if completed-tour
      board
      (first (filter some? (map #(solve-knights-tour-rec (move-knight board %)) next-moves))))))

(defn solve-knights-tour [n]
       (let [board (vec (cons (into [1] (vec (repeat (- n 1) 0)))
                              (vec (repeat (- n 1) (vec (repeat n 0))))))]
         (solve-knights-tour-rec board)))

(defmacro time-sec
  "Evaluates expr and prints the time it took.  Returns the value of
 expr."
  {:added "1.0"}
  [expr]
  `(let [start# (. System (nanoTime))
         ret# ~expr]
     (prn (str "Elapsed time: " (/ (double (- (. System (nanoTime)) start#)) 1000000.0) " msecs"))
     ret#))


(comment
  ((time-sec solve-knights-tour) 5))