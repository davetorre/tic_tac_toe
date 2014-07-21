(ns tic-tac-toe.player-test
    (require [tic-tac-toe.board  :refer :all]
			 [tic-tac-toe.rules  :refer :all]
			 [tic-tac-toe.player :refer :all]
			 [clojure.test		 :refer :all]))

(deftest player-test
    
    (testing "player makes one move in empty board"
        (let [board (gen-board)
              board-size (count board)
              board-after-move (vec (make-move board))]
              
            (is (= (count (filter nil? board-after-move)) (- board-size 1)))))

    (testing "player makes move in last free space in board"
        (let [board (board-with-spaces [0 1 2 3 4 5 6 7] 0)]
            (is (= (num-open-spaces (make-move board)) 0))))
    
    (testing "player makes move with correct token (X)"
        (let [x-turn-board (gen-board)
              x-turn-result (make-move x-turn-board)]

            (is (= (occurences x-turn-result 0) 1))))       
            
    (testing "player makes move with correct token (Y)"
        (let [y-turn-board (board-with-spaces [0] 0)
              y-turn-result (make-move y-turn-board)]
              
            (is (= (occurences y-turn-result 1) 1))))

    (testing "play-game should result in a full board"
        (let [full-board (play-game (gen-board))]

            (is (= (num-open-spaces full-board) 0))))

    (testing "play-game should not have a winner"
        (let [full-board (play-game (gen-board))]

            (is (nil? (get-winner full-board)))))                   
)