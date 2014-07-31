(ns tic-tac-toe.game
    (:require [tic-tac-toe.io     :refer :all]
              [tic-tac-toe.board  :refer :all]
              [tic-tac-toe.rules  :refer :all]
              [tic-tac-toe.player :refer :all]))
            
(defn human-goes-first? [io]
    (let [answer (prompt io "Would you like to go first? (y/n)")
          yeses #{"y" "Y" "Yes" "YES" "yes"}
          noes  #{"n" "N" "No"  "NO"  "no"}]
          
        (cond
            (contains? yeses answer) true
            (contains? noes answer) false
            :else (human-goes-first?))))

(defn print-game-result [io board]
    (print-board io board)
    (let [token (get-winner board)
          player (get-player-string token)]
        (if token
            (io-print io (str "Game over. " player " wins!"))
            (io-print io "Game over. Draw."))))
        
(defn game-loop [io players board]
    (if (game-over? board)
        (print-game-result io board)
        (let [board (make-move (first players) board)]
            (game-loop io (reverse players) board))))  
       
(defn -main [& args]
    (let [io (new-console-io)
          board (gen-board)
          players [(new-human-player io) (new-minmax-player)]]
          
        (if (human-goes-first? io)
            (game-loop io players board)
            (game-loop io (reverse players) board))))
