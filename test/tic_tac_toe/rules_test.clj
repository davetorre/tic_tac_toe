(ns tic-tac-toe.rules-test
	(require [tic-tac-toe.board :refer :all]
					 [tic-tac-toe.rules :refer :all]
					 [clojure.test      :refer :all]))
					
(deftest rules-test
	(testing "[1 1 1] in row has a horizontal winner"
		(let [board (-> (gen-board)
		                (set-space 0 1)
		                (set-space 1 1)
		                (set-space 2 1))]
			(is (horizontal-winner? board)))
		(let [board (-> (gen-board)
		                (set-space 3 1)
		                (set-space 4 1)
		                (set-space 5 1))]
			(is (horizontal-winner? board)))
		(let [board (-> (gen-board)
		                (set-space 6 1)
		                (set-space 7 1)
		                (set-space 8 1))]
			(is (horizontal-winner? board))))
			
	(testing "[0 0 0] in row has a horizontal winner"
    	(let [board (-> (gen-board)
    	                (set-space 0 0)
    	                (set-space 1 0)
    	                (set-space 2 0))]
    		(is (horizontal-winner? board)))
    	(let [board (-> (gen-board)
    	                (set-space 3 0)
    	                (set-space 4 0)
    	                (set-space 5 0))]
    		(is (horizontal-winner? board)))
		(let [board (-> (gen-board)
    	                (set-space 6 0)
    	                (set-space 7 0)
    	                (set-space 8 0))]
    		(is (horizontal-winner? board))))		
    		
	(testing "empty board does not have a horizontal winner"
		(let [board (gen-board)]
			(is (not (horizontal-winner? board)))))
	
    (testing "[nil 1 nil] in row does not have a horizontal winner"
        ; nor should any heterogeneous row
		(let [board (-> (gen-board)
		                (set-space 1 1))]
			(is (not (horizontal-winner? board))))
		(let [board (-> (gen-board)
		                (set-space 4 1))]
			(is (not (horizontal-winner? board))))
		(let [board (-> (gen-board)
		                (set-space 7 1))]
			(is (not (horizontal-winner? board)))))

)