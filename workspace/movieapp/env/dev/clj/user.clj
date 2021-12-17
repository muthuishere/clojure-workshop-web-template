(ns user
  "Userspace functions you can run by default in your local REPL."
  (:require
   [movieapp.config :refer [env]]
    [clojure.pprint]
    [clojure.spec.alpha :as s]
    [expound.alpha :as expound]
    [mount.core :as mount]
    [movieapp.core :refer [start-app]]
    [luminus-migrations.core :as migrations]
    [conman.core :as conman]
   ))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(add-tap (bound-fn* clojure.pprint/pprint))

(defn start
  "Starts application.
  You'll usually want to run this on startup."
  []
  (mount/start-without #'movieapp.core/repl-server))

(defn stop
  "Stops application."
  []
  (mount/stop-except #'movieapp.core/repl-server))

(defn restart
  "Restarts application."
  []
  (stop)
  (start))


(defn create-migration [name]
  (migrations/create name (select-keys env [:database-url]))
  )
(defn migrate []
  (migrations/migrate ["migrate"]  (select-keys env [:database-url]))
  )