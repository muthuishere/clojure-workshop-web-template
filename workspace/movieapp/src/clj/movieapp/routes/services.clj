(ns movieapp.routes.services
  (:require
    [reitit.swagger :as swagger]
    [reitit.swagger-ui :as swagger-ui]
    [reitit.ring.coercion :as coercion]
    [reitit.coercion.spec :as spec-coercion]
    [reitit.ring.middleware.muuntaja :as muuntaja]
    [reitit.ring.middleware.multipart :as multipart]
    [reitit.ring.middleware.parameters :as parameters]
    [movieapp.middleware.formats :as formats]
    [ring.util.http-response :refer :all]
    [movieapp.movies.handler :refer :all]
    [schema.core :as s]
    [clojure.spec.alpha :as spec]
    [spec-tools.core :as spec-core]
    [clojure.java.io :as io]))

;(defn is-production []
;
;  )

; {
;      "id": 145,
;      "title": "A Separation",
;      "year": "2011",
;      "runtime": "123",
;      "genres": [
;        "Drama",
;        "Mystery"
;      ],
;      "director": "Asghar Farhadi",
;      "actors": "Peyman Moaadi, Leila Hatami, Sareh Bayat, Shahab Hosseini",
;      "plot": "A married couple are faced with a difficult decision - to improve the life of their child by moving to another country or to stay in Iran and look after a deteriorating parent who has Alzheimer's disease.",
;      "posterUrl": "http://ia.media-imdb.com/images/M/MV5BMTYzMzU4NDUwOF5BMl5BanBnXkFtZTcwMTM5MjA5Ng@@._V1_SX300.jpg"
;    }

(defn create-spec [data-type default-value]
  (spec-core/spec
    {
     :spec data-type
     :swagger/default default-value
     }
    )

  )
;InsertMovieRequest
(s/defschema InsertMovieRequest
  {
   :id (create-spec int? "247")
   :title (spec-core/spec
            {
             :spec string?
             :swagger/default "A Separation"
             }
            )
   :genres (spec-core/spec
             {
              :spec (spec/coll-of string?)
              :swagger/default ["Drama" "Mystery"]
              }
             )
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

(defn service-routes []
  ["/api"
   {:coercion spec-coercion/coercion
    :muuntaja formats/instance
    :swagger {:id ::api}
    :middleware [;; query-params & form-params
                 parameters/parameters-middleware
                 ;; content-negotiation
                 muuntaja/format-negotiate-middleware
                 ;; encoding response body
                 muuntaja/format-response-middleware
                 ;; exception handling
                 coercion/coerce-exceptions-middleware
                 ;; decoding request body
                 muuntaja/format-request-middleware
                 ;; coercing response bodys
                 coercion/coerce-response-middleware
                 ;; coercing request parameters
                 coercion/coerce-request-middleware
                 ;; multipart
                 multipart/multipart-middleware]}

   ;; swagger documentation
   ["" {:no-doc true
        :swagger {:info {:title "my-api"
                         :description "https://cljdoc.org/d/metosin/reitit"}}}

    ["/swagger.json"
     {:get (swagger/create-swagger-handler)}]

    ["/api-docs/*"
     {:get (swagger-ui/create-swagger-ui-handler
             {:url "/api/swagger.json"
              :config {:validator-url nil}})}]]
  (movie-routes)
   ["/ping"
    {:get (constantly (ok {:message "updated pong"}))}]
   

   ["/math"
    {:swagger {:tags ["math"]}}

    ["/plus"
     {:get {:summary "plus with spec query parameters"
            :parameters {:query {:x int?, :y int?}}
            :responses {200 {:body {:total pos-int?}}}
            :handler (fn [{{{:keys [x y]} :query} :parameters}]
                       {:status 200
                        :body {:total (+ x y)}})}
      :post {:summary "plus with spec body parameters"
             :parameters {:body {:x int?, :y int?}}
             :responses {200 {:body {:total pos-int?}}}
             :handler (fn [{{{:keys [x y]} :body} :parameters}]
                        {:status 200
                         :body {:total (+ x y)}})}}]]

   ["/files"
    {:swagger {:tags ["files"]}}

    ["/upload"
     {:post {:summary "upload a file"
             :parameters {:multipart {:file multipart/temp-file-part}}
             :responses {200 {:body {:name string?, :size int?}}}
             :handler (fn [{{{:keys [file]} :multipart} :parameters}]
                        {:status 200
                         :body {:name (:filename file)
                                :size (:size file)}})}}]

    ["/download"
     {:get {:summary "downloads a file"
            :swagger {:produces ["image/png"]}
            :handler (fn [_]
                       {:status 200
                        :headers {"Content-Type" "image/png"}
                        :body (-> "public/img/warning_clojure.png"
                                  (io/resource)
                                  (io/input-stream))})}}]]])
