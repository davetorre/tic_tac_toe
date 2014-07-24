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
			 
(deftest console-io-test

    (testing "prompt prints out its question argument"
        (let [questions ["Hello!" "Have you ever transcended space and time?"]]
        
            (is (= (map #(get-prompt-print-out %) questions) questions))))

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
            
    (testing "get-human-move returns unchanged board on bad user input"
        (let [board (board-with-spaces [5] 0)
              bad-inputs ["5" "should not work"] 
              outputs (map #(with-in-str % (get-human-move board)) bad-inputs)]
              
            (is (every? #(= % board) outputs))))
    
    
)