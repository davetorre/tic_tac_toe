(ns tic-tac-toe.console-io
    (:require [tic-tac-toe.board  :refer :all]
              [tic-tac-toe.rules  :refer :all]
              [tic-tac-toe.player :refer :all]))
 
(defprotocol IOProtocol
    (io-print [this something])
    (io-read [this]))

(deftype ConsoleIO []
    IOProtocol
    (io-print [this something]
        (println something))
    (io-read [this]
        (read-line)))
        
(defn new-console-io []
    (ConsoleIO.))

(defn prompt [io question]
    (io-print io question)
    (io-read io))
 
(defn get-player-string [token]
    (cond 
        (= token 0) "X"
        (= token 1) "O"
        :else "-"))
    
(defn print-board [io board]
    (let [nice-board (map #(get-player-string %) board)
          nice-rows (vec (get-rows nice-board))]

        (doall (map #(io-print io %) nice-rows))))
      
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
          board (gen-board)]
        (if (human-goes-first? io)
            (game-loop io [(new-human-player io) (new-minmax-player)] board)
            (game-loop io [(new-minmax-player) (new-human-player io)] board))))
