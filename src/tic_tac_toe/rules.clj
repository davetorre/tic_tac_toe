(ns tic-tac-toe.rules
    (require [tic-tac-toe.board :refer :all]))

(defn horizontal-winner? [board]
    (def row1 (get-row board 0))
    (every? #(= %1 1) row1)
)