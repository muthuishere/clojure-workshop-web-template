(ns movieapp.users.auth
  (:require
    [buddy.sign.jwt :as jwt]
    [movieapp.config :refer [env]]
    [clj-time.core :as time]
    [buddy.auth.middleware :refer [wrap-authentication]]
    [buddy.auth.backends :as backends]

    )
  )

(def secret "mysecret" )

(defn get-expiration-time []
  (time/plus (time/now) (time/hours 5) )
  )


(def backend-auth (backends/jws {:secret secret :token-name "Bearer"}))

(defn wrap-jwt-authentication [handler]
  (wrap-authentication handler backend-auth )
  )

(defn create-token [payload]
  (let [exp-payload (assoc payload :exp (get-expiration-time))]
    (jwt/sign exp-payload  secret))
  )
(defn only-logged-in-user [handler]
  (fn [request]
    (if (nil? (get request :identity) )
      {:status 401 :body {:error "Should be logged in with Bearer token to use this api"}}
      (handler request)
      )
    )
  )


;:jwt-secret



(comment



  (jwt/sign {:user "mrx" :admin false :exp (get-expiration-time)} secret)

  (jwt/unsign "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoibXJ4IiwiYWRtaW4iOmZhbHNlfQ.LDJ5E-vyB8vywU-fvStFmkydHipUa9JqDDa0ixkz6Es"
              secret
              )
  )
