(ns tic-tac-toe.rules-test
	(require [tic-tac-toe.board :refer :all]
			 [tic-tac-toe.rules :refer :all]
			 [clojure.test		:refer :all]))

(defn cat-board []
    (-> (gen-board)
  	    (set-spaces [0 1 5 6 8] 0)
  		(set-spaces [2 3 4 7] 1)))
              		    
(deftest rules-test
	(testing "[1 1 1] in row has correct winner"
		(let [first-row-board (board-with-spaces [0 1 2] 1)
			  second-row-board (board-with-spaces [3 4 5] 1)
			  third-row-board (board-with-spaces [6 7 8] 1)
			  boards [first-row-board second-row-board third-row-board]]
			(every? #(= % 1) (map #(get-winner %) boards))))			

			
	(testing "[0 0 0] in row has correct winner"
		(let [first-row-board (board-with-spaces [0 1 2] 0)
			  second-row-board (board-with-spaces [3 4 5] 0)
			  third-row-board (board-with-spaces [6 7 8] 0)
			  boards [first-row-board second-row-board third-row-board]]
			(every? #(= % 0) (map #(get-winner %) boards))))		
	
	(testing "[1 1 1] in column has correct winner"
		(let [first-col-board (board-with-spaces [0 3 6] 1)
			  second-col-board (board-with-spaces [1 4 7] 1)
			  third-col-board (board-with-spaces [2 5 8] 1)
			  boards [first-col-board second-col-board third-col-board]]
			(every? #(= % 1) (map #(get-winner %) boards))))		

	(testing "[0 0 0] in column has correct winner"
		(let [first-col-board (board-with-spaces [0 3 6] 0)
			  second-col-board (board-with-spaces [1 4 7] 0)
			  third-col-board (board-with-spaces [2 5 8] 0)
			  boards [first-col-board second-col-board third-col-board]]
  			(every? #(= % 0) (map #(get-winner %) boards))))		

	(testing "[1 1 1] in diagonal has correct winner"
		(let [upward-ones (board-with-spaces [2 4 6] 1)
			  downward-ones (board-with-spaces [0 4 8] 1)
			  boards [upward-ones downward-ones]]
  			(every? #(= % 1) (map #(get-winner %) boards))))      	        

	(testing "[0 0 0] in diagonal has correct winner"
		(let [upward-zeros (board-with-spaces [2 4 6] 0)
		      downward-zeros (board-with-spaces [0 4 8] 0)
			  boards [upward-zeros downward-zeros]]
    		(every? #(= % 0) (map #(get-winner %) boards))))      	        
			
	(testing "empty board does not have any winners"
		(let [board (gen-board)]
			(is (nil? (get-winner board)))))
			
	(testing "cat board does not have any winners"
		(let [board (cat-board)]
			(is (nil? (get-winner board))))) 

    (testing "if X wins, score is 10"
        (let [board (board-with-spaces [2 4 6] 0)]
            (is (= (get-score board) 10))))
            
    (testing "if O wins, score is -10"
        (let [board (board-with-spaces [0 4 8] 1)]
            (is (= (get-score board) -10))))
            
    (testing "cat board's score is 0"
        (let [board (cat-board)]
            (is (= (get-score board) 0))))
            
    (testing "empty board's score is nil"
        (is (nil? (get-score (gen-board)))))
)