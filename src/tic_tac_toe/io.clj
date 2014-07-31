(ns tic-tac-toe.io)
              
(defprotocol IOProtocol
    (io-print [this something])
    (io-read [this]))

(deftype ConsoleIO []
    IOProtocol
    (io-print [this something]
        (println something))
    (io-read [this]
        (read-line)))
        
(defn new-console-io []
    (ConsoleIO.))

(deftype TestIO [input]
    IOProtocol
    (io-print [this something]
        something)
    (io-read [this]
        input))
        
(defn new-test-io [input]
    (TestIO. input))
            
(defn prompt [io question]
    (io-print io question)
    (io-read io))
    
