(ns tic-tac-toe.rules
	(require [tic-tac-toe.board :refer :all])
	(require [tic-tac-toe.player :refer :all]))

(defn winning-line? [line]	
	(or (every? #(= %1 0) line)
		(every? #(= %1 1) line)))

(defn horizontal-winner? [board]
	(let [rows (get-rows board)]
	    (contains? (set (map winning-line? rows)) true)))
			
(defn vertical-winner? [board]
    (let [columns (get-columns board)]
        (contains? (set (map winning-line? columns)) true)))
			
(defn diagonal-winner? [board]
    (let [diagonals (get-diagonals board)]
	    (contains? (set (map winning-line? diagonals)) true)))
		
(defn has-winner? [board]
    (or (horizontal-winner? board)
        (vertical-winner? board)
        (diagonal-winner? board)))

(defn game-over? [board]
	(or (horizontal-winner? board)
	    (vertical-winner? board)
		(diagonal-winner? board)
		(= 0 (num-open-spaces board))))
		
(defn play-game [board]
    (loop [board board]
        (if (= (num-open-spaces board) 0)
            board
            (recur (make-move board)))))