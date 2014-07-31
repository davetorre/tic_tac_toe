(ns tic-tac-toe.game-test
    (require [tic-tac-toe.io            :refer :all]
             [tic-tac-toe.board         :refer :all]
             [tic-tac-toe.player        :refer :all]
             [tic-tac-toe.game          :refer :all]
             [tic-tac-toe.sample-boards :refer :all]
             [clojure.test              :refer :all]))
                     
(deftest game-test
            
    (testing "human-goes-first? returns true if user responds Y"
        (is (human-goes-first? (new-test-io "Y"))))
        
    (testing "human-goes-first? returns false if user responds N"
        (is (not (human-goes-first? (new-test-io "N")))))
        
    (testing "print-game-result prints out winner if X wins"
        (let [board (board-with-spaces [0 1 2] 0)
              output "Game over. X wins!"
              io (new-test-io "5")]
            
            (is (= (print-game-result io board) output))))
            
    (testing "print-game-result prints out 'Draw' if no winner"
        (let [board (cat-board)
              output "Game over. Draw."
              io (new-test-io "0")]
             
            (is (= (print-game-result io board) output)))))
              
              
            