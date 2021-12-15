(ns hello-web-1.core
  (:require
    ;[compojure.core :refer :all]
    [compojure.api.sweet :as sw]
    [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
    [ring.middleware.json :as middleware]
    ;[ring.adapter.jetty :refer :all]
    [org.httpkit.server :refer [run-server]]

    [compojure.route :as route]

    [hello-web-1.movies.handler :refer :all]
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
(defn multiply [body]

  (apply * (vals body))

  )
(defn handle-multiplication [request]

  (print request)
  {
   :status 200
   :body   {
            :result (multiply (get request :body-params))
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
;
;(defroutes app-routes
;
;           (GET "/hello" request (fn [request]
;                                   "Hello Clojure Web API with Lein"
;                                   ))
;
;           (GET "/hello/:name" request (fn [request]
;                                         (println request)
;                                         (str "Welcome " (get-in request [:params :name]))
;                                         )
;
;                                       )
;
;
;           (POST "/insertdata" request insert-data)
;           (POST "/add" request handle-add)
;           (POST "/product" request handle-multiplication)
;           (route/not-found "Url Not found")
;
;           )

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
  (sw/api

    (sw/GET "/api/movies" request handle-all-movies)
    (sw/GET "/api/movies/:id" request handle-movie-by-id)
    (sw/POST "/api/movies" request handle-insert-movie)
    (sw/PUT "/api/movies/:id" request handle-update-movie)
    (sw/DELETE "/api/movies/:id" request handle-delete-movie-by-id)
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