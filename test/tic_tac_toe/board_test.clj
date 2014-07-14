(ns tic-tac-toe.board-test
	(require [tic-tac-toe.board :refer :all]
			 [clojure.test      :refer :all]))
					
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
			
	(testing "getting a board row"
	    (let [board (gen-board)
	          updated-board (set-space board 4 1)]
	        (is (= [nil 1 nil] (get-row updated-board 1))))
	)
)