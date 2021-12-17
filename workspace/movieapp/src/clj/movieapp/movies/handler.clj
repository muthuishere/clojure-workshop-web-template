(ns movieapp.movies.handler
  (:require [movieapp.movies.service :as movie-service])
  )


(defn handle-all-movies [request]
  {:status 200
   :body   (movie-service/all-movies)
   }
  )



;Exercise
(defn handle-movie-by-id [request]


  (println request)
  (let [
        id (Integer/parseInt (get-in request [:path-params :id]))
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





(defn handle-insert-movie [request]


  (let

    [
     data (get request :body-params)
     result (movie-service/insert-movie data)]


    (if (not (nil? result))
      {
       :status 200
       ; Always use map or string for a body ;
       :body  {
               :result result
               }
       }
      {:status 404
       :body   "Could not Insert Movie"
       }
      )

    )


  )




;Exercise do update
;movies/:id
; Body Holds the update response
(defn handle-update-movie [request]
(println request)

  (let [
        id  (Integer/parseInt (get-in request [:path-params :id]))
        data (get request :body-params)
        ]

    {
     :status 200
     :body (movie-service/update-movie id data)
     }
    )

  )

;Exercise
; Implement Delete By ID
(defn handle-delete-movie-by-id [request]
  (let [
        id  (Integer/parseInt (get-in request [:path-params :id]))

        ]

    {
     :status 200
     :body (movie-service/delete-movie id)
     }
    )
  )
