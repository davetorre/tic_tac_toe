(ns tic-tac-toe.player
    (:require [tic-tac-toe.io    :refer :all]
              [tic-tac-toe.board :refer :all]
              [tic-tac-toe.rules :refer :all]))

(defprotocol Player
    (make-move [this board]))

(defn occurences [board token]
    (count (filter #(= % token) board)))
           
(defn get-token [board]
    (let [x-count (occurences board 0)
          y-count (occurences board 1)]
        (if (<= x-count y-count)
            0
            1)))             
    
(defn do-weighting [score]
    (cond 
        (> score 0) (dec score)
        (< score 0) (inc score)
        :else 0))
        
(defn get-minmax-move [board]
    (if (game-over? board)
        [nil (get-score board)]
        (let [moves (get-open-spaces board)
              token (get-token board)
              boards (map #(set-space board % token) moves)
              results (map get-minmax-move boards)
              scores (map #(second %) results)
              weighted-scores (map do-weighting scores)
              max-score (apply max weighted-scores)
              max-index (.indexOf weighted-scores max-score)
              min-score (apply min weighted-scores)
              min-index (.indexOf weighted-scores min-score)]
            
            (if (= token 0)
                [(get (vec moves) max-index) max-score]
                [(get (vec moves) min-index) min-score]))))
    
(deftype MinMaxPlayer []
    Player
    (make-move [this board]
        (if (game-over? board)
            board
            (let [move (first (get-minmax-move board))
                  token (get-token board)]
                (set-space board move token)))))
                
(defn new-minmax-player []
    (MinMaxPlayer.))
    
(defn get-human-move [io board]
    (print-board io board)
    (let [open-spaces (seq (get-open-spaces board))
          user-input (prompt io (str "What's your move? " open-spaces))
          open-spaces-strings (map #(str %) open-spaces)]
        
        (if (contains? (set open-spaces-strings) user-input)
            (Integer/parseInt user-input)
            (get-human-move io board))))
              
(deftype HumanPlayer [io]
    Player
    (make-move [this board]
        (if (game-over? board)
            board
            (let [move (get-human-move io board)
                  token (get-token board)]
                (set-space board move token)))))
                 
(defn new-human-player [io]
    (HumanPlayer. io))
        
