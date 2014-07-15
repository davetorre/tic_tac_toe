(ns tic-tac-toe.rules
	(require [tic-tac-toe.board :refer :all]))

(defn winner? [row]
	(and (= width (count row))	
		(or (every? #(= %1 0) row)
			(every? #(= %1 1) row))))

(defn horizontal-winner? [board]
	(def rows (get-rows board))
	(contains? (set (map winner? rows)) true))
			
(defn vertical-winner? [board]
    (def columns (get-columns board))
    (contains? (set (map winner? columns)) true))
			
(defn diagonal-winner? [board]
	(def upward-diag 
		(keep-indexed #(when (contains? #{2 4 6} %1) %2) board))
	(def downward-diag 
		(keep-indexed #(when (contains? #{0 4 8} %1) %2) board))  
		
	(or (winner? upward-diag)
		(winner? downward-diag)))
		
(defn game-over? [board]
	(or (horizontal-winner? board)
		(diagonal-winner? board)
		(count (get-open-spaces board))))