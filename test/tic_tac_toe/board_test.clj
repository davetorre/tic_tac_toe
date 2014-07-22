(ns tic-tac-toe.board-test
	(require [tic-tac-toe.board :refer :all]
			 [clojure.test		:refer :all]))
          							
(deftest board-test
	(testing "creating a new board"
		(let [expected-board [nil nil nil
							  nil nil nil
							  nil nil nil]]
			(is (= expected-board (gen-board)))))
			
	(testing "setting a board space"
		(let [board (gen-board)
			  updated-board (set-space board 0 0)]
			(is (= 0 (first updated-board)))))
			
	(testing "getting a board space"
		(let [board (gen-board)
			  updated-board (set-space board 4 2)]
			(is (= 2 (get-space updated-board 4)))))
			
	(testing "getting board rows, columns and diagonals"
		(let [board (-> (gen-board)
     	                (set-spaces [0 1 5 6 8] 0)
     		            (set-spaces [2 3 4 7] 1))]
     		            
			(is (= [[0 0 1] [1 1 0] [0 1 0]] (get-rows board)))
			(is (= [[0 1 0] [0 1 1] [1 0 0]] (get-columns board)))
			(is (= [[1 1 0] [0 1 0]] (get-diagonals board)))))
	
	(testing "getting open spaces"
		(let [board (-> (gen-board)
						(set-space 0 0)
						(set-space 2 0)
						(set-space 4 1)
						(set-space 8 1))]
			(is (= '(1 3 5 6 7) (get-open-spaces board))))
		(let [board (-> (gen-board)
						(set-space 0 0)
						(set-space 1 0))]
			(is (= '(2 3 4 5 6 7 8) (get-open-spaces board))))
		(let [board (-> (gen-board)
						(set-space 5 1)
						(set-space 7 1))]
			(is (= '(0 1 2 3 4 6 8) (get-open-spaces board)))))
			
)