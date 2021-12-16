(ns movieapp.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [movieapp.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[movieapp started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[movieapp has shut down successfully]=-"))
   :middleware wrap-dev})
