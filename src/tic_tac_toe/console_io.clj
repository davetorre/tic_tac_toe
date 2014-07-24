(ns tic-tac-toe.console-io
	(:require [tic-tac-toe.board  :refer :all]
              [tic-tac-toe.rules  :refer :all]
              [tic-tac-toe.player :refer :all]))
              
(defn prompt [question]
    (println question)
    (read-line))
    
(defn get-human-move [board]
    (let [move (Integer/parseInt (prompt "What's your move?"))
          token (get-token board)]
          
        (set-space board move token)))