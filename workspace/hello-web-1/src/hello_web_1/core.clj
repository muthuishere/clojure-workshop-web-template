(ns hello-web-1.core
  (:require
    [compojure.core :refer :all]
    [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
    [ring.adapter.jetty :refer :all]
    [compojure.route :as route]
    )
  )

(defroutes app-routes

  (GET "/hello" request (fn [request]
                          "Hello Clojure Web API with Lein"

                          ))

  (GET "/hello/:name"  request (fn [request]

                                 (println request)
                                 "Testing"

                                 ; Exercise
                                 ; Get the name and print Welcome {name}
                                 ))

    (route/not-found "Url Not found")
  )


(def app (wrap-defaults  app-routes api-defaults))


(defn -main [& args]

  (run-jetty app {
                  :port 3000
                  })

  )