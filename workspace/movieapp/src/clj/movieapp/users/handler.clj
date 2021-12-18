(ns movieapp.users.handler
  (:require [movieapp.users.service :as user-service])
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

(defn handle-login [request]


  (let

    [
     data (get request :body-params)
     result (user-service/login data)]


    (if (not (nil? result))
      {
       :status 200
       :body  {
               :result result
               }
       }
      {:status 404
       :body   "Invalid user credentials"
       }
      )

    )


  )




