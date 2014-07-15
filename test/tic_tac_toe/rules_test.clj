(ns tic-tac-toe.rules-test
	(require [tic-tac-toe.board :refer :all]
					 [tic-tac-toe.rules :refer :all]
					 [clojure.test		:refer :all]))

(defn board-with-spaces [spaces, player]
    (reduce #(set-space %1 %2 player) (gen-board) spaces))

(deftest rules-test
	(testing "[1 1 1] in row has a horizontal winner"
		(let [first-row-board (board-with-spaces [0 1 2] 1)
			  second-row-board (board-with-spaces [3 4 5] 1)
			  third-row-board (board-with-spaces [6 7 8] 1)
			  boards [first-row-board second-row-board third-row-board]]
			(is (every? horizontal-winner? boards))))
			
	(testing "[0 0 0] in row has a horizontal winner"
		(let [first-row-board (board-with-spaces [0 1 2] 0)
			  second-row-board (board-with-spaces [3 4 5] 0)
			  third-row-board (board-with-spaces [6 7 8] 0)
			  boards [first-row-board second-row-board third-row-board]]
			(is (every? horizontal-winner? boards))))		
			
	(testing "empty board does not have a horizontal winner"
		(let [board (gen-board)]
			(is (not (horizontal-winner? board)))))
	
	(testing "[nil 1 nil] in row does not have a horizontal winner"
		; nor should any heterogeneous row
		(let [first-row-board (board-with-spaces [1] 1)
			  second-row-board (board-with-spaces [4] 1)
			  third-row-board (board-with-spaces [7] 1)
			  boards [first-row-board second-row-board third-row-board]]
			(is (not (every? horizontal-winner? boards)))))

	(testing "vertical winner"
	    (let [board (board-with-spaces [0 3 6] 1)]
	        (is (vertical-winner? board))))
        	        
	(testing "board has upward diagonal winner"
		(let [upward-zeros (board-with-spaces [2 4 6] 0)
			  upward-ones (board-with-spaces [2 4 6] 1)
			  boards [upward-zeros upward-ones]]
			(is (every? diagonal-winner? boards))))
			
	(testing "board has downward diagonal winner"
		(let [downward-zeros (board-with-spaces [0 4 8] 0)
			  downward-ones (board-with-spaces [0 4 8] 1)
			  boards [downward-zeros downward-ones]]
			(is (every? diagonal-winner? boards))))
			
	(testing "game is over with horizontal winner"
		(let [board (board-with-spaces [0 1 2] 1)]
			(is (game-over? board))))
			
	(testing "game is over with vertical winner"
		(let [board (board-with-spaces [0 3 6] 1)]
			(is (game-over? board))))
			
	(testing "game is over with diagonal winner"
		(let [board (board-with-spaces [0 4 8] 1)]
			(is (game-over? board))))
			
	(testing "game is over with full board"
		(let [board (-> (gen-board)
						(set-space 0 0)
						(set-space 1 0)
						(set-space 2 1)
						(set-space 3 1)
						(set-space 4 1)
						(set-space 5 0)
						(set-space 6 0)
						(set-space 7 1)
						(set-space 8 0))]
			(is (game-over? board))))
	
	(testing "game is not over with empty board"
	    (let [board (gen-board)]
	        (is (not (game-over? board)))))
)