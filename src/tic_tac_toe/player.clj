(ns tic-tac-toe.player
	(:require [tic-tac-toe.board :refer :all]
              [tic-tac-toe.rules :refer :all]))
	
(defn occurences [board token]
    (count (filter #(= % token) board)))
           
(defn get-token [board]
    (let [x-count (occurences board 0)
          y-count (occurences board 1)]
        (if (<= x-count y-count)
            0
            1)))             
            
(defn make-move [board]
    (assoc board (first (get-open-spaces board)) (get-token board)))     

(defn play-game [board]
    (loop [board board]
        (if (= (num-open-spaces board) 0)
            board
            (recur (make-move board)))))