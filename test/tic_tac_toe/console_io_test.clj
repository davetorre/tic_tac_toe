(ns tic-tac-toe.console-io-test
    (require [tic-tac-toe.board      :refer :all]
			 [tic-tac-toe.rules      :refer :all]
			 [tic-tac-toe.player     :refer :all]
			 [tic-tac-toe.console-io :refer :all]
			 [clojure.test		     :refer :all]))
			 
(deftest console-io-test

    (testing "prompt prints out its question argument"
        (is (= (with-out-str (with-in-str "--" (prompt "Hello!"))) "Hello!\n")))

    (testing "prompt outputs user's input"
        (is (= (with-in-str "y" (prompt "Do you want to go first?")) "y")))
        
    (testing "get-human-move marks the user's move in a board"
        (let [board (gen-board)
              board-after (board-with-spaces [0] 0)]
              
            (is (= (with-in-str "0" (get-human-move board)) board-after))))
            
    
    
    
    
)