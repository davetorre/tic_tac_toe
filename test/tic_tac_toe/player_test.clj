(ns tic-tac-toe.player-test
    (require [tic-tac-toe.io     :refer :all]
             [tic-tac-toe.board  :refer :all]
             [tic-tac-toe.rules  :refer :all]
             [tic-tac-toe.player :refer :all]
             [clojure.test       :refer :all]))

(defn make-minmax-move [board]
    (make-move (new-minmax-player) board))

(defn play-minmax-game [board]
    (loop [board board]
        (if (game-over? board)
            board
            (recur (make-minmax-move board)))))
                         
(deftest player-test
    
    (testing "MinMaxPlayer makes one move in not-yet-finished game board"
        (let [board (-> (gen-board)
                    (set-spaces [0 1 5 6] 0)
                    (set-spaces [2 3 4 7] 1))
              result (make-minmax-move board)]
              
            (is (= (- (num-open-spaces board) 1) (num-open-spaces result)))))

    (testing "MinMaxPlayer doesn't make move in finished game board"
        (let [board (board-with-spaces [0 1 2] 0)]
        
            (is (= board (make-minmax-move board)))))
    
    (testing "MinMaxPlayer makes move with correct token (X)"
        (let [board (-> (gen-board)
                        (set-spaces [0 1 5 6] 0)
                        (set-spaces [2 3 4 7] 1))
              result (make-minmax-move board)]

            (is (= (+ (occurences board 0) 1) (occurences result 0)))))       
            
    (testing "MinMaxPlayer makes move with correct token (Y)"
        (let [board (-> (gen-board)
                        (set-spaces [0 1 5 6] 0)
                        (set-spaces [2 3 4] 1))
              result (make-minmax-move board)]
              
            (is (= (+ (occurences board 1) 1) (occurences result 1)))))

    (testing "game of two MinMaxPlayers should result in a full board, no winner"
        (let [full-board (play-minmax-game (gen-board))]

            (is (= (num-open-spaces full-board) 0))
            (is (nil? (get-winner full-board)))))
            
    (testing "if possible, MinMaxPlayer should win immediately"
        (let [board (-> (gen-board)
                        (set-spaces [1 3 5] 0)
                        (set-spaces [0 4] 1))]
                        
            (is (game-over? (make-minmax-move board)))))
            
    (testing "HumanPlayer marks user's move when going first"
        (let [board (gen-board)
              board-after (board-with-spaces [0] 0)
              io (new-test-io "0")]
              
            (is (= (make-move (new-human-player io) board) board-after))))
    
    (testing "HumanPlayer marks user's move when going second"
        (let [board (board-with-spaces [0] 0)
              board-after (-> (gen-board)
                              (set-space 0 0)
                              (set-space 4 1))
              io (new-test-io "4")]
          
            (is (= (make-move (new-human-player io) board) board-after)))))