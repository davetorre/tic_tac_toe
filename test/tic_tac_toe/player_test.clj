(ns tic-tac-toe.player-test
    (require [tic-tac-toe.board  :refer :all]
			 [tic-tac-toe.rules  :refer :all]
			 [tic-tac-toe.player :refer :all]
			 [clojure.test		 :refer :all]))

(deftest player-test
    
    (testing "player makes one move in not-yet-finished game board"
        (let [board (-> (gen-board)
 	                (set-spaces [0 1 5 6] 0)
 		            (set-spaces [2 3 4 7] 1))
              result (make-move board)]
              
            (is (= (- (num-open-spaces board) 1) (num-open-spaces result)))))

    (testing "player doesn't make move in finished game board"
        (let [board (board-with-spaces [0 1 2] 0)]
        
            (is (= board (make-move board)))))
    
    (testing "player makes move with correct token (X)"
        (let [board (-> (gen-board)
     	                (set-spaces [0 1 5 6] 0)
     		            (set-spaces [2 3 4 7] 1))
              result (make-move board)]

            (is (= (+ (occurences board 0) 1) (occurences result 0)))))       
            
    (testing "player makes move with correct token (Y)"
        (let [board (-> (gen-board)
     	                (set-spaces [0 1 5 6] 0)
     		            (set-spaces [2 3 4] 1))
              result (make-move board)]
              
            (is (= (+ (occurences board 1) 1) (occurences result 1)))))

;    (testing "play-game should result in a full board, no winner"
;        (let [full-board (play-game (gen-board))]

;            (is (= (num-open-spaces full-board) 0))
;            (is (nil? (get-winner full-board)))))
            
;    (testing "if possible, smart-move should result in a win"
;	    (let [board (-> (gen-board)
;					    (set-spaces [1 3 5] 0)
;					    (set-spaces [0 4] 1))]
)