(ns tic-tac-toe.rules
	(require [tic-tac-toe.board :refer :all]))

(defn winner? [row]	
	(or (every? #(= %1 0) row)
		(every? #(= %1 1) row)))

(defn horizontal-winner? [board]
	(let [rows (get-rows board)]
	    (contains? (set (map winner? rows)) true)))
			
(defn vertical-winner? [board]
    (let [columns (get-columns board)]
        (contains? (set (map winner? columns)) true)))
			
(defn diagonal-winner? [board]
    (let [diagonals (get-diagonals board)]
	    (contains? (set (map winner? diagonals)) true)))
		
(defn game-over? [board]
	(or (horizontal-winner? board)
	    (vertical-winner? board)
		(diagonal-winner? board)
		(= 0 (count (get-open-spaces board)))))