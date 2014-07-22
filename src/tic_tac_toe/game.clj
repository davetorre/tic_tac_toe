(ns tic-tac-toe.game
	(:require [tic-tac-toe.board  :refer :all]
              [tic-tac-toe.rules  :refer :all]
              [tic-tac-toe.player :refer :all]))
 
(defn print-board [board]
    (let [nice-board (replace {nil "-" 0 "X" 1 "O"} board)
          nice-rows (vec (get-rows nice-board))]
          
        (println (get nice-rows 0))
        (println (get nice-rows 1))
        (println (get nice-rows 2))))
       
(defn get-human-move [board]        
    (println "Choose an open space" (get-open-spaces board))

    (let [space (Integer/parseInt (read-line))
          token (get-token board)]

        (if (contains? (set (get-open-spaces board)) space) 
            (set-space board space token))))
              
(defn game-loop [board turn]
    (if (= turn :human)
        (print-board board))

    (if (game-over? board)
        (println "Game Over")
        (if (= turn :human)
            (game-loop (get-human-move board) :cpu)
            (game-loop (make-move board) :human))))
              
(defn -main [& args]
    (println "Would you like to go first? (y/n)")
    
    (if (contains? (set ["Y" "y" "Yes" "YES" "yes"]) (read-line))
        (game-loop (gen-board) :human)
        (game-loop (gen-board) :cpu)))