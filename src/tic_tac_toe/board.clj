(ns tic-tac-toe.board)

(defn gen-board []
	[nil nil nil
	 nil nil nil
	 nil nil nil])
	
(defn set-space [board space player]
	(assoc board space player))
	
(defn get-space [board space]
	(nth board space))
	
(defn get-row [board row]
    (def start (* 3 row))
    (subvec board start (+ 3 start))
)