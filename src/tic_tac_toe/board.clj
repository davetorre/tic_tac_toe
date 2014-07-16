(ns tic-tac-toe.board)

(defn width-of [board]
    (let [width (Math/sqrt (count board))]
        (if (= (mod width 1) 0.0)
            (int width))))
    
(defn gen-board []
	[nil nil nil
	 nil nil nil
	 nil nil nil])
	
(defn set-space [board space player]
	(assoc board space player))
	
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