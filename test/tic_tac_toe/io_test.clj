(ns tic-tac-toe.game-test
    (require [tic-tac-toe.io :refer :all]
             [clojure.test   :refer :all]))
             
(deftest io-test

    (testing "prompt outputs the user's answer"
        (let [io (TestIO. "Blue")]
            (is (= (prompt io "What is your favorite color?") "Blue")))))