(ns movieui.movies.list-movie-events
  (:require
    [re-frame.core :as rf]
    [ajax.core :as ajax]

    )
  )


(rf/reg-event-fx
  :movies/load
  (fn [input1 input2]
    {:http-xhrio {
                  :method :get
                  :uri "http://localhost:4200/api/movies"
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success [:movies/set]
                  :on-failure [:movies/seterror]

                  }

     }

    )
  )
;
;(rf/reg-event-fx
;  :movies/set
;  (fn [input1 [_ movies]]
;
;    (println "movies set")
;    (println (count input1) )
;    (println (keys input1) )
;
;    (get input1 :db)
;
;    ()
;    (println "\n 2" movies )
;
;
;    )
;  )
(rf/reg-event-db
  :movies/set
  (fn [db [_ movies]]

    (assoc db :movies movies)


    )
  )
(rf/reg-event-db
  :movies/seterror
  (fn [db [_ error]]
    (assoc db :error error)

    )
  )

(rf/reg-sub
  :movies
  (fn [db input]
    (get db :movies)
    )
  )
