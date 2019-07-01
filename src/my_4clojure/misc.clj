(ns my-4clojure.misc)

(defn get-current-position-number [board]
  (->> board
       (apply concat)
       (apply max)))

(defn get-current-position [board]
  (let [max-turn (get-current-position-number board)
        board-length (count board)]
    (first (for [r (range board-length)
                 c (range board-length)
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
  (let [next-move (+ 1 (get-current-position-number board))
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

(defn completed-tour? [board ]
  (let [board-length (count board)
        next-move (inc (get-current-position-number board))]
    (> next-move (* board-length board-length))))

(defn solve-knights-tour-rec [board]
  (let [next-moves (sort-by (partial move-accessibility board) (get-next-moves board))]
    (if (completed-tour? board)
      board
      (first (filter some? (map #(solve-knights-tour-rec (move-knight board %)) next-moves))))))

(defn create-random-board [n]
  (vec (map vec (partition n
                           (-> (repeat (* n n) 0)
                               vec
                               (assoc (rand-int (* n n)) 1))))))

(defn solve-knights-tour [n]
       (let [board (create-random-board n)]
         (solve-knights-tour-rec board)))


(comment
  (solve-knights-tour 8)
  (create-random-board 9)
  (solve-knights-tour-rec [[1 0 0 0 0 0 0 0 0]
                           [0 0 0 0 0 0 0 0 0]
                           [0 0 0 0 0 0 0 0 0]
                           [0 0 0 0 0 0 0 0 0]
                           [0 0 0 0 0 0 0 0 0]
                           [0 0 0 0 0 0 0 0 0]
                           [0 0 0 0 0 0 0 0 0]
                           [0 0 0 0 0 0 0 0 0]
                           [0 0 0 0 0 0 0 0 0]]))

