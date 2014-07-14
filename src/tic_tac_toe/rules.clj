(ns tic-tac-toe.rules
    (require [tic-tac-toe.board :refer :all]))

(defn horizontal-winner? [board]
    (def row0 (get-row board 0))
    (def row1 (get-row board 1))
    (def row2 (get-row board 2))

    (or (or (every? #(= %1 0) row0)
            (every? #(= %1 1) row0))
        (or (every? #(= %1 0) row1)
            (every? #(= %1 1) row1))
        (or (every? #(= %1 0) row2)
            (every? #(= %1 1) row2)))
)