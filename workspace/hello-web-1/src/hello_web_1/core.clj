(ns hello-web-1.core
  (:require
    [compojure.core :refer :all]
    [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
    [ring.middleware.json :as middleware]
    [ring.adapter.jetty :refer :all]
    [compojure.route :as route]
    )
  )
(defn insert-data [request]
  (println request)


  {
   :status 200
   :body {
          :result (str "Created Successfully for" (get-in request [:body :name]))

          }



   }
  )
(defroutes app-routes

  (GET "/hello" request (fn [request]
                          "Hello Clojure Web API with Lein"

                          ))

  (GET "/hello/:name"  request (fn [request]

                                 (println request)
                                 (str "Welcome " (get-in request [:params :name]))
                                 )

                               )


           (POST "/insertdata" request  (fn [request]

                                          (insert-data request)
                                          )
                                        )




    (route/not-found "Url Not found")
  )

; Home Assignment
; A Post Request /add
;{
; "a":23,
; "b":45
; }

; Result Should be
;
;{
; "result" : 68
; }
(def app (wrap-defaults


           (middleware/wrap-json-response
             (middleware/wrap-json-body app-routes {:keywords? true})
             )

           api-defaults))


(defn -main [& args]

  (run-jetty app {
                  :port 3000
                  })

  )