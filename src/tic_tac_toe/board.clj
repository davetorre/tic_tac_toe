(ns tic-tac-toe.board)

(def width 3)

(defn gen-board []
	[nil nil nil
	 nil nil nil
	 nil nil nil])
	
(defn set-space [board space player]
	(assoc board space player))
	
(defn get-space [board space]
	(nth board space))
	
(defn get-rows [board]
    (partition width board))

(defn get-open-spaces [board]
    (keep-indexed #(when (nil? %2) %1) board))