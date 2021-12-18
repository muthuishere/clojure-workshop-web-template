(ns movieui.movies.list-movie-page
  (:require [re-frame.core :as rf])
  )


(defn movie-card [movie]
[:div.col.mt-5
  [:div.card {:style
              {
               :width "18rem"
               }
              }
   [:img.card-img-top {:src "https://upload.wikimedia.org/wikipedia/en/1/19/Titanic_%28Official_Film_Poster%29.png" :alt "Titanic"}]
   [:div.card-body
    [:h5.card-title "Titanic"]
    [:p.card-text "Plot" (get movie :plot)]
    [:a.btn.btn-primary {:href "#"} "View More"]]]
 ]
  )


(defn list-movies-component []

  (rf/dispatch [:movies/load])
  [:div.container.mt-3
  [:h2
   "List Movies"
   ]
   [:div.row.row-cols-3

    (for [movie @(rf/subscribe [:movies])]
        [movie-card movie]
        ;(movie-card movie)
    )

    ;[movie-card][movie-card][movie-card][movie-card]
    ]



   ]

  )