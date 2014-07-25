(ns tic-tac-toe.console-io
    (:require [tic-tac-toe.board  :refer :all]
              [tic-tac-toe.rules  :refer :all]
              [tic-tac-toe.player :refer :all]))
                  
(defn print-board [board]
    (let [nice-board (replace {nil "-" 0 "X" 1 "O"} board)
          nice-rows (vec (get-rows nice-board))]

        (doall (map println nice-rows))))
              
(defn prompt [question]
    (println question)
    (read-line))
    
(defn get-human-move [board]
    (let [open-spaces (seq (get-open-spaces board))
          user-input (prompt (str "What's your move? " open-spaces))
          open-spaces-strings (map #(str %) open-spaces)]
        
        (if (contains? (set open-spaces-strings) user-input)
            (let [move (Integer/parseInt user-input)
                  token (get-token board)]    
                (set-space board move token))
            (get-human-move board))))
            
(defn human-goes-first? []
    (let [answer (prompt "Would you like to go first? (y/n)")
          yeses #{"y" "Y" "Yes" "YES" "yes"}
          noes  #{"n" "N" "No"  "NO"  "no"}]
          
        (cond
            (contains? yeses answer) true
            (contains? noes answer) false
            :else (human-goes-first?))))

(defn game-loop [board turn]
    (if (= turn :human)
        (print-board board))

    (if (game-over? board)
        (println "Game over. Bye!")
        (if (= turn :human)
            (game-loop (get-human-move board) :cpu)
            (game-loop (make-move board) :human))))  
       
(defn -main [& args]
    (if (human-goes-first?)
        (game-loop (gen-board) :human)
        (game-loop (gen-board) :cpu)))
