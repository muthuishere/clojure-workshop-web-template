(ns movieui.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [movieui.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[movieui started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[movieui has shut down successfully]=-"))
   :middleware wrap-dev})
