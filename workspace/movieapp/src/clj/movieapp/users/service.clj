(ns movieapp.users.service
  (:require [movieapp.db.core :as db]
            [clojure.tools.logging :as log])
  )


(defn all-users []
  (db/get-all-users)
  )

(defn register [data]
  (db/register-user! data)
  )

(defn register-admin [data]
  (db/register-admin! data)
  )

(defn login [data]
    (db/login data)
  )

