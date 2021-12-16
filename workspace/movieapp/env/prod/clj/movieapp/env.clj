(ns movieapp.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[movieapp started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[movieapp has shut down successfully]=-"))
   :middleware identity})
