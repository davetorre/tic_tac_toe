(ns tic-tac-toe.console-io-test
    (require [tic-tac-toe.board      :refer :all]
			 [tic-tac-toe.rules      :refer :all]
			 [tic-tac-toe.player     :refer :all]
			 [tic-tac-toe.console-io :refer :all]
			 [clojure.test		     :refer :all]))
			 
(defn get-prompt-print-out [question]
    (with-out-str (with-in-str "--" (prompt question))))
    
(defn get-prompt-output [answer]
    (with-in-str answer (prompt "--")))

(defn make-input-sequence [coll]
    (apply str (interleave coll (repeat "\n"))))
      			 
(deftest console-io-test

    (testing "prompt prints out its question argument"
        (let [questions ["Hello!" "Have you ever transcended space and time?"]
              questions-w-newline (map #(str % "\n") questions)]
        
            (is (= (map #(get-prompt-print-out %) questions) questions-w-newline))))

    (testing "prompt outputs the user's answer"
        (let [answers ["Hi" "5"]]
        
            (is (= (map #(get-prompt-output %) answers) answers))))
        
    (testing "get-human-move marks user's move when going first"
        (let [board (gen-board)
              board-after (board-with-spaces [0] 0)]
              
            (is (= (with-in-str "0" (get-human-move board)) board-after))))
            
    (testing "get-human-move marks user's move when going second"
        (let [board (board-with-spaces [0] 0)
              board-after (-> (gen-board)
      						  (set-space 0 0)
      						  (set-space 4 1))]
          
            (is (= (with-in-str "4" (get-human-move board)) board-after))))    
    
    (testing "get-human-move asks until receiving valid move"
        (let [board (board-with-spaces [5] 0)
              input-sequence (make-input-sequence ["5" "not valid" "8"]) 
              result (set-space board 8 1)]
            
            (is (= (with-in-str input-sequence (get-human-move board)) result)))) 
    
    (testing "human-goes-first? returns true if user responds Y"
        (is (with-in-str "Y" (human-goes-first?))))
        
    (testing "human-goes-first? returns false if user responds N"
        (is (not (with-in-str "N" (human-goes-first?)))))
            
    (testing "human-goes-first? asks until receiving valid answer"
        (is (with-in-str "nobody\n27\nY" (human-goes-first?))))

)