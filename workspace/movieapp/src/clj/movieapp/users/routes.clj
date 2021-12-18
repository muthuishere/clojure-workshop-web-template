(ns movieapp.users.routes
  (:require [movieapp.users.handler :refer :all]
            [schema.core :as s]
            [movieapp.shared.schema-creator :refer :all]
            [clojure.spec.alpha :as spec]
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
   ["/users" {

               :get {
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
   ["/users/registeradmin" {

               :post {
                     :handler handle-register-admin
                      :parameters {
                                   :body RegisterUserRequest
                                   }
                     }


               }
    ]




   ]

  )
