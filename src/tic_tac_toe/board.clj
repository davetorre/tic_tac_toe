(ns tic-tac-toe.board
    (:require [tic-tac-toe.io :refer :all]))

(defn width-of [board]
    (let [width (Math/sqrt (count board))]
        (if (= (mod width 1) 0.0)
            (int width))))
    
(defn gen-board []
    [nil nil nil
     nil nil nil
     nil nil nil])
            
(defn set-space [board space token]
    (assoc board space token))

(defn board-with-spaces [spaces token]
    (reduce #(set-space %1 %2 token) (gen-board) spaces))
    
(defn set-spaces [board spaces token]
    (reduce #(set-space %1 %2 token) board spaces))
                    
(defn get-space [board space]
    (nth board space))
    
(defn get-rows [board]
    (partition (width-of board) board))
    
(defn get-column [board column-number]
    (take-nth (width-of board) (subvec board column-number)))
    
(defn get-columns [board]
    (map #(get-column board %1) (range (width-of board))))

(defn get-upward-diag [board]
    (let [space-between (- (width-of board) 1)
          sub-board (subvec board space-between (- (count board) space-between))]
        (take-nth space-between sub-board)))

(defn get-downward-diag [board]  
    (take-nth (+ (width-of board) 1) board))

(defn get-diagonals [board]
    (if (odd? (width-of board))
        [(get-upward-diag board) (get-downward-diag board)]))

(defn get-open-spaces [board]
    (keep-indexed #(when (nil? %2) %1) board))
    
(defn num-open-spaces [board]
    (count (filter nil? board)))
    
(defn get-player-string [token]
    (cond 
        (= token 0) "X"
        (= token 1) "O"
        :else "-"))
    
(defn print-board [io board]
    (let [nice-board (map #(get-player-string %) board)
          nice-rows (vec (get-rows nice-board))]

        (doall (map #(io-print io %) nice-rows))))