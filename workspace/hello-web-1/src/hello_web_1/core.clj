(ns hello-web-1.core
  (:require
    [compojure.core :refer :all]
    [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
    [ring.middleware.json :as middleware]
    ;[ring.adapter.jetty :refer :all]
    [org.httpkit.server :refer [run-server]]

    [compojure.route :as route]
    )
  )
(defn insert-data [request]
  (println request)


  {
   :status 200
   :body   {
            :result (str " function Created Successfully for" (get-in request [:body :name]))

            }

   }
  )



(comment

  (def body {:a 2 :b 3 :c 5 :d 8})

  (keys body)

  (vals body)

  (reduce + (vals body))

  )

(defn res-ok [message] {:status 200 :body {:result message}})

;(defn add [{:keys [a b]}]
;  (+ a b)
;  )

(defn add [body]
  ;(reduce + (vals body))

  (apply + (vals body))

  )



(defn handle-add [{:keys [body]}]
  {
   :status 200
   :body   {
            :result (add body)
            }
   }
  )

; product of n numbers
;{
; a 2
;  b 3
;  c 4
; }

; he result
;{ result 24}

(defroutes app-routes

           (GET "/hello" request (fn [request]
                                   "Hello Clojure Web API with Lein"
                                   ))

           (GET "/hello/:name" request (fn [request]
                                         (println request)
                                         (str "Welcome " (get-in request [:params :name]))
                                         )

                                       )


           (POST "/insertdata" request insert-data)
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

;WrapDefault first argument second
;middleware/wrap-json-response
;middleware/wrap-json-body app-routes {}

(comment

  (macroexpand '(-> app-routes
                   (middleware/wrap-json-body  {:keywords? true})
                    (middleware/wrap-json-response)
                    (wrap-defaults api-defaults)
                   )
               )

  )
(def app
  (-> app-routes
      (middleware/wrap-json-body  {:keywords? true})
      (middleware/wrap-json-response)
      (wrap-defaults api-defaults)
      )
  )


(defn -main [& args]

  (run-server app {
                  :port 3000
                   :event-logger (fn [eventname]
                                   (println eventname)
                                   )
                   })

  )