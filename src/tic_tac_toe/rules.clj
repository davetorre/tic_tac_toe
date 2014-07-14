(ns tic-tac-toe.rules
    (require [tic-tac-toe.board :refer :all]))

(defn winner? [row]
    (and (= 3 (count row))  
        (or (every? #(= %1 0) row)
            (every? #(= %1 1) row))))

(defn horizontal-winner? [board]
    (def row0 (get-row board 0))
    (def row1 (get-row board 1))
    (def row2 (get-row board 2))

    (or (winner? row0) 
        (winner? row1) 
        (winner? row2)))
            
(defn diagonal-winner? [board]
    (def upward-diag 
        (keep-indexed #(when (contains? #{2 4 6} %1) %2) board))
    (def downward-diag 
        (keep-indexed #(when (contains? #{0 4 8} %1) %2) board))  
        
    (or (winner? upward-diag)
        (winner? downward-diag)))