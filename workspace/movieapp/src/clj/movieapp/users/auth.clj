(ns movieapp.users.auth
  (:require
    [buddy.sign.jwt :as jwt]
    [movieapp.config :refer [env]]
    [clj-time.core :as time]
    )
  )

(def secret (get env :jwt-secret) )

(defn get-expiration-time []
  (time/plus (time/now) (time/hours 5) )
  )

(comment



  (jwt/sign {:user "mrx" :admin false :exp (get-expiration-time)} secret)

  (jwt/unsign "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoibXJ4IiwiYWRtaW4iOmZhbHNlfQ.LDJ5E-vyB8vywU-fvStFmkydHipUa9JqDDa0ixkz6Es"
              secret
              )
  )

(defn create-token [payload]


  )


;:jwt-secret

