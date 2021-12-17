(ns movieapp.db.core
  (:require
    [conman.core :as conman]
    [mount.core :refer [defstate]]
    [movieapp.config :refer [env]]
    )
  )


(defstate ^:dynamic *db*
          :start (conman/connect! {:jdbc-url (get env :database-url)})
          :stop (conman/disconnect! *db*)
          )

(conman/bind-connection *db* "sql/movies.sql" "sql/users.sql" )