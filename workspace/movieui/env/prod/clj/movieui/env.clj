(ns movieui.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[movieui started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[movieui has shut down successfully]=-"))
   :middleware identity})
