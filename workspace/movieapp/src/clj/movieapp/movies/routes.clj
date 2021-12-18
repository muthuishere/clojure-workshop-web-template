(ns movieapp.movies.routes
  (:require [schema.core :as s]
            [movieapp.shared.schema-creator :refer :all]
            [movieapp.movies.handler :refer :all]
            [clojure.spec.alpha :as spec]
            ))



;Home Assignment
;Fix Swagger Requests
(s/defschema InsertMovieRequest
             {
              :id (create-spec int? "247")
              :title (create-string-with "A Separation")

              ; String requires double quoted for allowing to automatically changing integers
              :year (create-string-with "\"2011\"")
              :runtime (create-string-with "\"123\"")
              :genres (create-spec (spec/coll-of string?) ["Drama" "Mystery"])
              :director (create-string-with "Asghar Farhadi")
              :actors (create-string-with "Peyman Moaadi, Leila Hatami, Sareh Bayat, Shahab Hosseini")
              :plot (create-string-with "A married couple are faced with a difficult decision - to improve the life of their child by moving to another country or to stay in Iran and look after a deteriorating parent who has Alzheimer's disease.")
              :posterUrl (create-string-with "http://ia.media-imdb.com/images/M/MV5BMTYzMzU4NDUwOF5BMl5BanBnXkFtZTcwMTM5MjA5Ng@@._V1_SX300.jpg")

              }

             )

(s/defschema UpdateMovieRequest
             {
              :title (create-string-with "A Separation")

              ; String requires double quoted for allowing to automatically changing integers
              :year (create-string-with "\"2011\"")
              :runtime (create-string-with "\"123\"")
              :genres (create-spec (spec/coll-of string?) ["Drama" "Mystery"])
              :director (create-string-with "Asghar Farhadi")
              :actors (create-string-with "Peyman Moaadi, Leila Hatami, Sareh Bayat, Shahab Hosseini")
              :plot (create-string-with "A married couple are faced with a difficult decision - to improve the life of their child by moving to another country or to stay in Iran and look after a deteriorating parent who has Alzheimer's disease.")
              :posterUrl (create-string-with "http://ia.media-imdb.com/images/M/MV5BMTYzMzU4NDUwOF5BMl5BanBnXkFtZTcwMTM5MjA5Ng@@._V1_SX300.jpg")

              }

             )

(defn movie-routes []
  ["" {:no-doc false
       :swagger {

                 :tags ["movies"]
                 }
       }
   ["/movies" {

               :get {
                     :handler handle-all-movies
                     }

               :post {

                      :handler handle-insert-movie
                      :parameters {

                                   :body InsertMovieRequest
                                   }
                      }
               }
    ]

   ["/movies/:id" {

                   :get {
                         :handler handle-movie-by-id
                         :parameters {

                                      :path {:id int?}
                                      }

                         }

                   :put {
                         :handler handle-update-movie
                         :parameters {

                                      :path {:id int?}
                                      :body UpdateMovieRequest
                                      }
                         }

                   :delete {
                            :handler handle-delete-movie-by-id
                            :parameters {

                                         :path {:id int?}
                                         }
                            }


                   }
    ]


   ]

  )