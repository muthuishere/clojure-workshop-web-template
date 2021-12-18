(ns movieapp.users.routes
  (:require [movieapp.users.handler :refer :all]
            [schema.core :as s]
            [movieapp.shared.schema-creator :refer :all]
            [clojure.spec.alpha :as spec]
            [movieapp.users.auth :refer [wrap-jwt-authentication only-logged-in-user only-logged-in-admin]]
            )
  )



(s/defschema LoginRequest
             {
              :email (create-string-with "user@gmail.com")
              :pass (create-string-with "password")
              }

             )

(s/defschema RegisterUserRequest
             {
              :email (create-string-with "newuser@gmail.com")
              :pass (create-string-with "password")
              :first_name (create-string-with "new")
              :last_name (create-string-with "user")
              }

             )



(defn user-routes []
  ["" {:no-doc false
       :swagger {

                 :tags ["users"]
                 }
       }

   ; Exercise
   ; AllUsers should be available only for logged in users
   ["/users" {

               :get {
                     :middleware [wrap-jwt-authentication only-logged-in-user]
                     :swagger {
                               :security [
                                          {:apiAuth []}
                                          ]
                               }
                     :handler handle-all-users
                     }


               }
    ]
   ["/users/login" {

               :post {
                     :handler handle-login
                      :parameters {
                                   :body LoginRequest
                                   }
                     }


               }
    ]

   ["/users/register" {

               :post {
                     :handler handle-register
                      :parameters {
                                   :body RegisterUserRequest
                                   }
                     }


               }
    ]

   ; Exercise
   ; registeradmin should be available only for logged in admins
   ; create a new middleware only-logged-in-admin
   ["/users/registeradmin" {

               :post {
                      :middleware [wrap-jwt-authentication only-logged-in-admin]
                      :swagger {
                                :security [
                                           {:apiAuth []}
                                           ]
                                }
                     :handler handle-register-admin
                      :parameters {
                                   :body RegisterUserRequest
                                   }
                     }


               }
    ]




   ]

  )
