(ns tic-tac-toe.console-io-test
    (require [tic-tac-toe.board      :refer :all]
             [tic-tac-toe.rules      :refer :all]
             [tic-tac-toe.player     :refer :all]
             [tic-tac-toe.console-io :refer :all]
             [tic-tac-toe.rules-test :refer :all]
             [clojure.test           :refer :all]))
             
(deftype TestIO [input]
    IOProtocol
    (io-print [this something]
        something)
    (io-read [this]
        input))
                     
(deftest console-io-test

    (testing "prompt outputs the user's answer"
        (let [io (TestIO. "Blue")]
            (is (= (prompt io "What is your favorite color?") "Blue"))))
            
    (testing "HumanPlayer marks user's move when going first"
        (let [board (gen-board)
              board-after (board-with-spaces [0] 0)
              io (TestIO. "0")]
              
            (is (= (make-move (new-human-player io) board) board-after))))
    
    (testing "HumanPlayer marks user's move when going second"
        (let [board (board-with-spaces [0] 0)
              board-after (-> (gen-board)
                              (set-space 0 0)
                              (set-space 4 1))
              io (TestIO. "4")]
          
            (is (= (make-move (new-human-player io) board) board-after))))
            
    (testing "human-goes-first? returns true if user responds Y"
        (is (human-goes-first? (TestIO. "Y"))))
        
    (testing "human-goes-first? returns false if user responds N"
        (is (not (human-goes-first? (TestIO. "N")))))
        
    (testing "game-loop prints out winner if X wins"
        (let [board (board-with-spaces [0 1 2] 0)
              output "Game over. X wins!"
              io (TestIO. "5")]
            
            (is (= (game-loop io board :cpu) output))))
            
    (testing "game-loop prints out 'Draw' if no winner"
        (let [board (cat-board)
              output "Game over. Draw."
              io (TestIO. "0")]
             
            (is (= (game-loop io board :cpu) output)))))
              
              
            