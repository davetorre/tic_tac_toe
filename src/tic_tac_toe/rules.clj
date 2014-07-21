(ns tic-tac-toe.rules
	(:require [tic-tac-toe.board :refer :all]))

(defn winning-line? [line]	
	(or (every? #(= %1 0) line)
		(every? #(= %1 1) line)))

(defn winner? [line]
    (cond 
        (every? #(= % 0) line) 0
        (every? #(= % 1) line) 1
        :else nil))

(defn get-winner [board]
    (let [row-winners (map winner? (get-rows board))
          column-winners (map winner? (get-columns board))
          diagonal-winners (map winner? (get-diagonals board))
          winners (concat row-winners column-winners diagonal-winners)]
        (first (filter identity winners))))

(defn get-score [board]
    (let [winner (get-winner board)]
        (cond 
            (not (= winner nil)) (if (= winner 0) 10 -10)                        
            (= (count (get-open-spaces board)) 0) 0
            :else nil)))