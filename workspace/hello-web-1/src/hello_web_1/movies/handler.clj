(ns hello-web-1.movies.handler
  (:require [hello-web-1.movies.service :as movie-service])
  )


(defn handle-all-movies [request]
  {:status 200
   :body   (movie-service/all-movies)
   }
  )

(defn handle-movie-by-id [request]

  )

(defn handle-insert-movie [request]

  )

(defn handle-update-movie [request]

  )

(defn handle-delete-movie-by-id [request]

  )
;(sw/GET "/api/movies" request handle-all-movies)
;(sw/GET "/api/movies/:id" request handle-movie-by-id)
;(sw/POST "/api/movies" request handle-insert-movie)
;(sw/PUT "/api/movies/:id" request handle-update-movie)
;(sw/DELETE "/api/movies/:id" request handle-delete-movie-by-id)