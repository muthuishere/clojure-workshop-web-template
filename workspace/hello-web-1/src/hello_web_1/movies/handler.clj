(ns hello-web-1.movies.handler
  (:require [hello-web-1.movies.service :as movie-service])
  )


(defn handle-all-movies [request]
  {:status 200
   :body   (movie-service/all-movies)
   }
  )



;Exercise
(defn handle-movie-by-id [request]


  (let [
        id (Integer/parseInt (get-in request [:params :id]))
        result (movie-service/movie-by-id id)]

    (if result
      {
       :status 200
       :body   result
       }
      {:status 404
       :body   "Invalid Movie ID"
       }
      )

    )

  )





(defn handle-insert-movie [{:keys [body-params]}]
  (println body-params)
  {
   :status 201
   :body   (movie-service/insert-movie body-params)
   }

  )




;Exercise do update
;movies/:id
; Body Holds the update response
(defn handle-update-movie [request]
(println request)

  (let [
        id  (Integer/parseInt (get-in request [:params :id]))
        data (get request :body-params)
        ]

    {
     :status 200
     :body (movie-service/update-movie id data)
     }
    )

  )

(defn handle-delete-movie-by-id [request]

  )
;(sw/GET "/api/movies" request handle-all-movies)
;(sw/GET "/api/movies/:id" request handle-movie-by-id)
;(sw/POST "/api/movies" request handle-insert-movie)
;(sw/PUT "/api/movies/:id" request handle-update-movie)
;(sw/DELETE "/api/movies/:id" request handle-delete-movie-by-id)