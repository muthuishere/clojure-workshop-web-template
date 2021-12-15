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
          :result (str " function Created Successfully for" (get-in request [:body :name]))

          }



   }
  )

(defn res-ok [message] {:status 200 :body {:result message}})

(defn add [{:keys [a b]}]
  (+ a b)
  )

(defn handle-add [{:keys [body]}]
  {
   :status 200
   :body {
          :result (add body)
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


       (POST "/insertdata" request       insert-data    )


           ;

           (POST "/add" request handle-add)




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