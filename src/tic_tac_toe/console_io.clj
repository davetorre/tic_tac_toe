(ns tic-tac-toe.console-io
	(:require [tic-tac-toe.board  :refer :all]
              [tic-tac-toe.rules  :refer :all]
              [tic-tac-toe.player :refer :all]))
              
(defn prompt [question]
    (println question)
    (read-line))
    
(defn get-human-move [board]
    (let [user-input (prompt "What's your move?")
          open-spaces-strings (map #(str %) (get-open-spaces board))]
        
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
         
        
(defn -main [& args])