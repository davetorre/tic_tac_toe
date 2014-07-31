(ns tic-tac-toe.sample-boards
    (require [tic-tac-toe.board :refer :all]))
    
(defn cat-board []
    (-> (gen-board)
        (set-spaces [0 1 5 6 8] 0)
        (set-spaces [2 3 4 7] 1)))