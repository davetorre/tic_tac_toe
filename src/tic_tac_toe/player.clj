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
      
(defn get-smart-move [board]
    (if (game-over? board)
        [nil (get-score board)]
        (let [moves (get-open-spaces board)
              token (get-token board)
              boards (map #(set-space board % token) moves)
              results (map get-smart-move boards)
              scores (map #(second %) results)
              max-score (apply max scores)
              max-index (.indexOf scores max-score)
              min-score (apply min scores)
              min-index (.indexOf scores min-score)]
            
            (if (= token 0)
                [(get (vec moves) max-index) max-score]
                [(get (vec moves) min-index) min-score]))))
                             
(defn make-move [board]
    (if (game-over? board)
        board
        (let [move (first (get-smart-move board))
              token (get-token board)]
            (set-space board move token))))

(defn play-game [board]
    (loop [board board]
        (if (game-over? board)
            board
            (recur (make-move board)))))