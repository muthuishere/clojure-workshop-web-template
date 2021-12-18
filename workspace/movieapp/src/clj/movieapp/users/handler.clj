(ns movieapp.users.handler
  (:require
    [movieapp.users.service :as user-service]
    [movieapp.users.auth :refer [create-token]]
    )
  )


(defn handle-all-users [request]
  {:status 200
   :body   (user-service/all-users)
   }
  )





(defn handle-register [request]


  (let

    [
     data (get request :body-params)
     result (user-service/register data)]


    (if (not (nil? result))
      {
       :status 200
       :body  {
               :result result
               }
       }
      {:status 404
       :body   "Could not Register user"
       }
      )

    )


  )


(defn handle-register-admin [request]

(println request)
  (let

    [
     data (get request :body-params)
     result (user-service/register-admin data)]


    (if (not (nil? result))
      {
       :status 200
       :body  {
               :result result
               }
       }
      {:status 404
       :body   "Could not Register admin user"
       }
      )

    )


  )


(comment

  ;(user-service/register {:email "user@gmail.com" :pass "password"})
  ;
  ;(create-user-info-with-token {})

  )
(defn create-user-info-with-token [result]
  (assoc result :token (create-token result))

  )


(defn handle-login [request]


  (let

    [
     data (get request :body-params)
     result (user-service/login data)]


    (if (not (nil? result))
      {
       :status 200
       :body  (create-user-info-with-token result)
       }
      {:status 404
       :body   "Invalid user credentials"
       }
      )

    )


  )




