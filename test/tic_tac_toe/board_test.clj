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
	        (is (= [nil 1 nil] (get-row updated-board 1)))))
	
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