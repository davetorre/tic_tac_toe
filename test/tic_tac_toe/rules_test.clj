(ns tic-tac-toe.rules-test
	(require [tic-tac-toe.board :refer :all]
					 [tic-tac-toe.rules :refer :all]
					 [clojure.test      :refer :all]))
					
(deftest rules-test
	(testing "[1 1 1] row has a horizontal winner"
		(let [board (-> (gen-board)
		                (set-space 0 1)
		                (set-space 1 1)
		                (set-space 2 1))]
			(is (horizontal-winner? board))))
			
	(testing "empty row does not have a horizontal winner"
		(let [board (gen-board)]
			(is (not (horizontal-winner? board)))))
	
    (testing "[nil 1 nil] row does not have a horizontal winner"
		(let [board (-> (gen-board)
		                (set-space 0 1))]
			(is (not (horizontal-winner? board)))))    		
)