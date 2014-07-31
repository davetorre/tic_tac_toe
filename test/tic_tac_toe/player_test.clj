(ns tic-tac-toe.player-test
    (require [tic-tac-toe.board  :refer :all]
             [tic-tac-toe.rules  :refer :all]
             [tic-tac-toe.player :refer :all]
             [clojure.test       :refer :all]))

(defn play-minmax-game [board]
    (loop [board board]
        (if (game-over? board)
            board
            (recur (make-move (new-minmax-player) board)))))
                         
(deftest player-test
    
    (testing "MinMaxPlayer makes one move in not-yet-finished game board"
        (let [board (-> (gen-board)
                    (set-spaces [0 1 5 6] 0)
                    (set-spaces [2 3 4 7] 1))
              result (make-move (new-minmax-player) board)]
              
            (is (= (- (num-open-spaces board) 1) (num-open-spaces result)))))

    (testing "MinMaxPlayer doesn't make move in finished game board"
        (let [board (board-with-spaces [0 1 2] 0)]
        
            (is (= board (make-move (new-minmax-player) board)))))
    
    (testing "MinMaxPlayer makes move with correct token (X)"
        (let [board (-> (gen-board)
                        (set-spaces [0 1 5 6] 0)
                        (set-spaces [2 3 4 7] 1))
              result (make-move (new-minmax-player) board)]

            (is (= (+ (occurences board 0) 1) (occurences result 0)))))       
            
    (testing "MinMaxPlayer makes move with correct token (Y)"
        (let [board (-> (gen-board)
                        (set-spaces [0 1 5 6] 0)
                        (set-spaces [2 3 4] 1))
              result (make-move (new-minmax-player) board)]
              
            (is (= (+ (occurences board 1) 1) (occurences result 1)))))

    (testing "Game of two MinMaxPlayers should result in a full board, no winner"
        (let [full-board (play-minmax-game (gen-board))]

            (is (= (num-open-spaces full-board) 0))
            (is (nil? (get-winner full-board)))))
            
    (testing "if possible, MinMaxPlayer should win immediately"
        (let [board (-> (gen-board)
                        (set-spaces [1 3 5] 0)
                        (set-spaces [0 4] 1))]
                        
            (is (game-over? (make-move (new-minmax-player) board))))))