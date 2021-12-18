(ns movieapp.users.auth
  (:require
    [buddy.sign.jwt :as jwt]
    [movieapp.config :refer [env]]
    [clj-time.core :as time]
    [buddy.auth.middleware :refer [wrap-authentication]]
    [buddy.auth.backends :as backends]
    [buddy.auth :refer [authenticated?]]

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
    ;    (if (nil? (get request :identity))
      (if (authenticated? request)
        (handler request)
        {:status 401 :body {:error "Should be logged in with Bearer token to use this api"}}
        )
      )
    )

; {:identity : {:first_name user, :last_name lastname, :email user@gmail.com, :admin false, :exp 1639829260} }
(comment

  (def request-user {:identity  {:first_name "user", :last_name "lastname", :email "user@gmail.com", :admin false, :exp 1639829260} })
  (def request-admin {:identity  {:first_name "user", :last_name "lastname", :email "user@gmail.com", :admin true, :exp 1639829260} })
  (def request-empty {})


  (boolean (get-in request-user [:identity :admin] ))
  (boolean (get-in request-empty [:identity :admin] ))
  (boolean (get-in request-admin [:identity :admin] ))

  )


(defn is-admin? [request]
  (boolean (get-in request [:identity :admin] ))
  )
(defn only-logged-in-admin [handler]
  (fn [request]
    (if (is-admin? request  )
      (handler request)
      {:status 401 :body {:error "Should be an admin to use this functionality" }}
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
