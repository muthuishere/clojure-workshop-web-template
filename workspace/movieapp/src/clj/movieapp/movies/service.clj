(ns movieapp.movies.service
  (:require [movieapp.db.core :as db])
  )

(def movies (atom [{:plot
                    "A couple of recently deceased ghosts contract the services of a \"bio-exorcist\" in order to remove the obnoxious new owners of their house.",
                    :director "Tim Burton",
                    :genres   ["Comedy" "Fantasy"],
                    :title    "Beetlejuice",
                    :year     "1988",
                    :actors   "Alec Baldwin, Geena Davis, Annie McEnroe, Maurice Page",
                    :id       1,
                    :runtime  "92",
                    :posterUrl
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BMTUwODE3MDE0MV5BMl5BanBnXkFtZTgwNTk1MjI4MzE@._V1_SX300.jpg"}
                   {:plot
                    "The Cotton Club was a famous night club in Harlem. The story follows the people that visited the club, those that ran it, and is peppered with the Jazz music that made it so famous.",
                    :director "Francis Ford Coppola",
                    :genres   ["Crime" "Drama" "Music"],
                    :title    "The Cotton Club",
                    :year     "1984",
                    :actors   "Richard Gere, Gregory Hines, Diane Lane, Lonette McKee",
                    :id       2,
                    :runtime  "127",
                    :posterUrl
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BMTU5ODAyNzA4OV5BMl5BanBnXkFtZTcwNzYwNTIzNA@@._V1_SX300.jpg"}
                   {:plot
                    "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                    :director "Frank Darabont",
                    :genres   ["Crime" "Drama"],
                    :title    "The Shawshank Redemption",
                    :year     "1994",
                    :actors   "Tim Robbins, Morgan Freeman, Bob Gunton, William Sadler",
                    :id       3,
                    :runtime  "142",
                    :posterUrl
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BODU4MjU4NjIwNl5BMl5BanBnXkFtZTgwMDU2MjEyMDE@._V1_SX300.jpg"}
                   {:plot
                    "An American reporter goes to the Australian outback to meet an eccentric crocodile poacher and invites him to New York City.",
                    :director "Peter Faiman",
                    :genres   ["Adventure" "Comedy"],
                    :title    "Crocodile Dundee",
                    :year     "1986",
                    :actors   "Paul Hogan, Linda Kozlowski, John Meillon, David Gulpilil",
                    :id       4,
                    :runtime  "97",
                    :posterUrl
                    "https://images-na.ssl-images-amazon.com/images/M/MV5BMTg0MTU1MTg4NF5BMl5BanBnXkFtZTgwMDgzNzYxMTE@._V1_SX300.jpg"}]
                  ))


(comment
  (count @movies)
  (all-movies)
  )

(defn update-genre-with [genres movie]
  (assoc movie :genres genres)
  )
(defn convert-genres-to-array [movie]

  (-> (movie :genres)
      (clojure.string/split #",")
      (update-genre-with movie)
      )

  )

(defn convert-genres-to-string [movie]

  (->> (movie :genres)
       (clojure.string/join ",")
       (assoc movie :genres)
       )

  )


;Exercise convert Genres to array and send back as part of response
(defn all-movies []
  (map convert-genres-to-array (db/get-all-movies))
  )




(comment

  (->> @movies
       (filter #(= 1 (get % :id)))
       first
       )

  (first @movies)

  (movie-by-id 7872832)
  )

(comment

  (movie-by-id 1)
  )
;Exercise
(defn movie-by-id [id]

  (-> (db/get-movie-by-id {:id id})
      (convert-genres-to-array)
      )

  )

;Exercise What if Iam going to give existing ID
(comment

  (insert-movie {:plot
                 "#9 Modified Plot again",
                 :director "Tim Burton",
                 :genres   ["Comedy" "Fantasy"],
                 :title    "Beetlejuice",
                 :year     "1988",
                 :actors   "Alec Baldwin, Geena Davis, Annie McEnroe, Maurice Page",
                 :id       1,
                 :runtime  "92",
                 :posterUrl
                 "https://images-na.ssl-images-amazon.com/images/M/MV5BMTUwODE3MDE0MV5BMl5BanBnXkFtZTgwNTk1MjI4MzE@._V1_SX300.jpg"
                 })
  )

(defn insert-movie [movie]

  (let [
        movie-to-be-inserted (convert-genres-to-string movie)
        ]
    (db/insert-movie! movie-to-be-inserted)
    )

  )


(comment

  (update-movie 1 {:plot
                   "#9 Modified Plot again",
                   :director "Tim Burton",
                   :genres   ["Comedy" "Fantasy"],
                   :title    "Beetlejuice",
                   :year     "1988",
                   :actors   "Alec Baldwin, Geena Davis, Annie McEnroe, Maurice Page",
                   :id       1,
                   :runtime  "92",
                   :posterUrl
                   "https://images-na.ssl-images-amazon.com/images/M/MV5BMTUwODE3MDE0MV5BMl5BanBnXkFtZTgwNTk1MjI4MzE@._V1_SX300.jpg"
                   }
                )


  (println (:plot (movie-by-id 1)))
  )

(defn is-same-movie-id [id movie]
  (= id (get movie :id))
  )

(defn get-updated-movie-if-same-id [id movie-to-be-updated movie]

  (if (is-same-movie-id id movie)
    (merge movie movie-to-be-updated)
    movie)
  )
(defn update-movies-in [all-movies id movie-to-be-updated]

  (let [partial-update-function (partial get-updated-movie-if-same-id id movie-to-be-updated)]

    (->> all-movies
         (map partial-update-function)
         )

    )

  )
(defn update-movie [id movie-to-be-updated]
  (swap! movies update-movies-in id movie-to-be-updated)
  )

(comment
  (delete-movie 1)
  (println (:plot (movie-by-id 1)))
  )
(defn delete-movie [id]
  (->> @movies
       (remove #(= (:id %) id))
       (reset! movies)
       )

  )



(comment

  (convert-genres-to-string {:plot
                             "#9 Modified Plot again",
                             :director "Tim Burton",
                             :genres   ["Comedy" "Fantasy"],
                             :title    "Beetlejuice",
                             :year     "1988",
                             :actors   "Alec Baldwin, Geena Davis, Annie McEnroe, Maurice Page",
                             :id       1,
                             :runtime  "92",
                             :posterUrl
                             "https://images-na.ssl-images-amazon.com/images/M/MV5BMTUwODE3MDE0MV5BMl5BanBnXkFtZTgwNTk1MjI4MzE@._V1_SX300.jpg"
                             })

  )